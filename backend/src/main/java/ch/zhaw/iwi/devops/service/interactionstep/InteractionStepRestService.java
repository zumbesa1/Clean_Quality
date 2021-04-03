package ch.zhaw.iwi.devops.service.interactionstep;

import static spark.Spark.get;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.primitives.Longs;
import com.google.inject.Inject;
import com.google.inject.Injector;

import ch.zhaw.iwi.devops.model.interactionelement.InteractionElement;
import ch.zhaw.iwi.devops.model.interactionelement.form.FormValueFieldElement;
import ch.zhaw.iwi.devops.model.interactionelement.text.TextElement;
import ch.zhaw.iwi.devops.model.interactionstep.InteractionStep;
import ch.zhaw.iwi.devops.model.patient.Patient;
import ch.zhaw.iwi.devops.model.questionnaire.Questionnaire;
import ch.zhaw.iwi.devops.model.questionnaireresponse.QuestionnaireResponse;
import ch.zhaw.iwi.devops.model.questionnaireresponse.value.InteractionElementTextValue;
import ch.zhaw.iwi.devops.model.questionnaireresponse.value.InteractionElementValue;
import ch.zhaw.iwi.devops.service.AbstractCrudRestService;
import ch.zhaw.iwi.devops.service.interactionelement.InteractionElementDatabaseService;
import ch.zhaw.iwi.devops.service.interactionelement.gui.InteractionElementGuiModel;
import ch.zhaw.iwi.devops.service.interactionelement.gui.OrderSummaryComponentGuiModel;
import ch.zhaw.iwi.devops.service.interactionelement.gui.TextElementGuiModel;
import ch.zhaw.iwi.devops.service.patient.PatientDatabaseService;
import ch.zhaw.iwi.devops.service.questionnaire.QuestionnaireDatabaseService;
import ch.zhaw.iwi.devops.service.questionnaireresponse.QuestionnaireResponseDatabaseService;
import ch.zhaw.iwi.devops.service.questionnaireresponse.value.InteractionElementValueDatabaseService;

public class InteractionStepRestService extends AbstractCrudRestService<InteractionStep, Long, InteractionStepDatabaseService> {

	@Inject
	public InteractionStepRestService(Injector injector) {
		super(injector, InteractionStepDatabaseService.class);
	}

	@Inject
	QuestionnaireDatabaseService questionnaireDatabaseService;
	
	@Inject
	PatientDatabaseService patientDatabaseService;

	@Inject
	QuestionnaireResponseDatabaseService questionnaireResponseDatabaseService;
	
	@Inject
	InteractionElementDatabaseService interactionElementDatabaseService;
	
	@Inject
	InteractionElementValueDatabaseService interactionElementValueDatabaseService;
	
	@Override
	protected void initGet() {
		super.initGet();
		
		get("services/questionnaire/:questionnaireKey/interactionStep", (req, res) -> {
			Long questionnaireKey = Longs.tryParse(req.params("questionnaireKey"));
			Set<InteractionStep> resultList = new HashSet<>();
			Questionnaire questionnaire = questionnaireDatabaseService.read(questionnaireKey);
			addInteractionStepRecursive(resultList, questionnaire.getFirstInteractionStep());
			return getCrudDatabaseService().createPathList(resultList);
		}, getJsonTransformer());
		
		get("services/patient/:patientKey/questionnaire/:questionnaireKey/nextInteractionStep/:nextInteractionStepKey/interactionStepElements", (req, res) -> {
			Long questionnaireKey = Longs.tryParse(req.params("questionnaireKey"));
			Long patientKey = Longs.tryParse(req.params("patientKey"));
			InteractionStepKey nextInteractionStepKey = InteractionStepKey.fromEncodedKey(req.params(InteractionStepKey.NAME));
			List<InteractionElementGuiModel> result = new ArrayList<>();

			Questionnaire questionnaire = questionnaireDatabaseService.read(questionnaireKey);
			InteractionStep nextInteractionStep = null;
			if (nextInteractionStepKey == null) {
				// create response
				QuestionnaireResponse questionnaireResponse = new QuestionnaireResponse();
				questionnaireResponse.setDate(new Date());
				questionnaireResponse.setQuestionnaire(questionnaire);
				if (patientKey != null) {
					Patient patient = patientDatabaseService.read(patientKey);
					questionnaireResponse.setPatient(patient);
				}
				questionnaireResponseDatabaseService.create(questionnaireResponse);

				// return first step
				if (questionnaire.getFirstInteractionStep() != null) {
					nextInteractionStepKey = new InteractionStepKey(null, questionnaireResponse.getKey(), null, null);
					result = questionnaire.getFirstInteractionStep().getInteractionElementsGuiModels(nextInteractionStepKey);
				} else {
					result.add(createMessage("Fehler: Für den Fragebogen " + questionnaire.getName() + " ist kein erster Interaktions-Schritt definiert. Bitte kontaktieren Sie den Administrator."));
				}
			} else {
				// save result from previous step
				InteractionElement<?> interactionElement = interactionElementDatabaseService.read(nextInteractionStepKey.getInteractionElementKey());
				// form fields have their own post service where data is saved, see InteractionElementRestService
				if (!(interactionElement instanceof FormValueFieldElement<?>)) {
					// try to find existing data
					List<InteractionElementValue> existingValues = interactionElementValueDatabaseService.list(interactionElementValueDatabaseService.new OrderInteractionElementFilter(nextInteractionStepKey.getQuestionnaireResponseKey(), interactionElement.getInteractionStep().getKey()));
					if (existingValues.isEmpty()) {
						InteractionElementTextValue value = new InteractionElementTextValue();
						value.setInteractionElement(interactionElement);
						value.setTextValue(nextInteractionStepKey.getInteractionElementDisplayValue());
						value.setExportValue(nextInteractionStepKey.getInteractionElementExportValue());
						value.setQuestionnaireResponse(questionnaireResponseDatabaseService.read(nextInteractionStepKey.getQuestionnaireResponseKey()));
						interactionElementValueDatabaseService.create(value);
					} else {
						InteractionElementTextValue value = (InteractionElementTextValue) existingValues.get(0);
						value.setInteractionElement(interactionElement);
						value.setTextValue(nextInteractionStepKey.getInteractionElementDisplayValue());
						value.setExportValue(nextInteractionStepKey.getInteractionElementExportValue());
						value.setQuestionnaireResponse(questionnaireResponseDatabaseService.read(nextInteractionStepKey.getQuestionnaireResponseKey()));
						interactionElementValueDatabaseService.update(value);
					}
					
					// special case for new patient questionnaire
					if (!questionnaire.isPatientRequired()) {
						QuestionnaireResponse questionnaireResponse = questionnaireResponseDatabaseService.read(nextInteractionStepKey.getQuestionnaireResponseKey());
						Patient patient = questionnaireResponse.getPatient();
						if (patient == null) {
							patient = new Patient();
							patientDatabaseService.create(patient);
							questionnaireResponse.setPatient(patient);
							questionnaireResponseDatabaseService.update(questionnaireResponse);
						}
						if (interactionElement.getInteractionStep().getName().equals("Patient Personen in Haushalt")) { // TODO
							patient.setNumberOfPersonsInHousehold(Longs.tryParse(nextInteractionStepKey.getInteractionElementDisplayValue()));
							patientDatabaseService.update(patient);
						}
					}
				}

				// return chosen step
				if (nextInteractionStepKey.getNextInteractionStepKey() == null) {
					result.addAll(createFinalSummary(nextInteractionStepKey));
				} else {
					nextInteractionStep = getCrudDatabaseService().read(nextInteractionStepKey.getNextInteractionStepKey());
					result = nextInteractionStep.getInteractionElementsGuiModels(nextInteractionStepKey);
				}
			}

			if (result.isEmpty()) {
				result.add(createMessage("Fehler: Für den Fragebogen " + questionnaire.getName() + " sind im Schritt \"" + nextInteractionStep.getName() + "\" keine Interaktionen definiert. Bitte kontaktieren Sie den Administrator."));
			}

			return result;
		}, getJsonTransformer());
	}
		
	private TextElementGuiModel createMessage(String text) {
		TextElement message = new TextElement();
		message.setText(text);
		return message.toGuiModel(null, null);
	}
	
	private void addInteractionStepRecursive(Set<InteractionStep> resultList, InteractionStep nextStep) {
		if (nextStep == null || resultList.contains(nextStep)) {
			return;
		}
		resultList.add(nextStep);
		for (InteractionElement<?> element : nextStep.getInteractionElements()) {
			addInteractionStepRecursive(resultList, element.getNextInteractionStep());
		}
	}
	
	private List<InteractionElementGuiModel> createFinalSummary(InteractionStepKey key) {
		List<InteractionElementGuiModel> result = new ArrayList<>();
		QuestionnaireResponse questionnaireResponse = questionnaireResponseDatabaseService.read(key.getQuestionnaireResponseKey());
		result.add(createMessage("Ihr Fragebogen " + questionnaireResponse.getKey() + " ist nun komplett ausgefüllt."));

		// Order summary
		result.add(createMessage("Übersicht:"));
		result.add(new OrderSummaryComponentGuiModel(questionnaireResponseDatabaseService.getSummary(questionnaireResponse)));

		return result;
	}
	
}
