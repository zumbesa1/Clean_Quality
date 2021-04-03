package ch.zhaw.iwi.devops.service.interactionelement;

import static spark.Spark.get;
import static spark.Spark.put;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import com.google.common.primitives.Longs;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.inject.Inject;
import com.google.inject.Injector;

import ch.zhaw.iwi.devops.model.file.File;
import ch.zhaw.iwi.devops.model.interactionelement.InteractionElement;
import ch.zhaw.iwi.devops.model.interactionelement.form.FormDateFieldElement;
import ch.zhaw.iwi.devops.model.interactionelement.form.FormFileUploadElement;
import ch.zhaw.iwi.devops.model.interactionelement.form.FormNumberFieldElement;
import ch.zhaw.iwi.devops.model.interactionelement.form.FormValueFieldElement;
import ch.zhaw.iwi.devops.model.interactionstep.InteractionStep;
import ch.zhaw.iwi.devops.model.patient.Patient;
import ch.zhaw.iwi.devops.model.questionnaireresponse.QuestionnaireResponse;
import ch.zhaw.iwi.devops.model.questionnaireresponse.value.InteractionElementDoubleValue;
import ch.zhaw.iwi.devops.model.questionnaireresponse.value.InteractionElementFileValue;
import ch.zhaw.iwi.devops.model.questionnaireresponse.value.InteractionElementLongValue;
import ch.zhaw.iwi.devops.model.questionnaireresponse.value.InteractionElementTextValue;
import ch.zhaw.iwi.devops.model.questionnaireresponse.value.InteractionElementValue;
import ch.zhaw.iwi.devops.service.AbstractCrudRestService;
import ch.zhaw.iwi.devops.service.DateUtility;
import ch.zhaw.iwi.devops.service.PathListEntry;
import ch.zhaw.iwi.devops.service.exception.BackendValidationException;
import ch.zhaw.iwi.devops.service.file.FileDatabaseService;
import ch.zhaw.iwi.devops.service.interactionelement.gui.FormValueFieldElementGuiModel;
import ch.zhaw.iwi.devops.service.interactionstep.InteractionStepDatabaseService;
import ch.zhaw.iwi.devops.service.interactionstep.InteractionStepKey;
import ch.zhaw.iwi.devops.service.patient.PatientDatabaseService;
import ch.zhaw.iwi.devops.service.questionnaireresponse.QuestionnaireResponseDatabaseService;
import ch.zhaw.iwi.devops.service.questionnaireresponse.value.InteractionElementValueDatabaseService;

@SuppressWarnings("rawtypes")
public class InteractionElementRestService extends AbstractCrudRestService<InteractionElement, Long, InteractionElementDatabaseService> {

	@Inject
	public InteractionElementRestService(Injector injector) {
		super(injector, InteractionElementDatabaseService.class);
	}

	@Inject
	InteractionStepDatabaseService interactionStepDatabaseService;

	@Inject
	QuestionnaireResponseDatabaseService questionnaireResponseDatabaseService;

	@Inject
	InteractionElementValueDatabaseService interactionElementValueDatabaseService;

	@Inject
	FileDatabaseService fileDatabaseService;

	@Inject
	PatientDatabaseService patientDatabaseService;

	@Override
	protected void initGet() {
		super.initGet();

		get("services/interactionStep/:interactionStepKey/interactionElement", (req, res) -> {
			Long interactionStepKey = Longs.tryParse(req.params("interactionStepKey"));
			List<InteractionElement> result = new ArrayList<>();
			if (interactionStepKey != null) {
				result.addAll(interactionStepDatabaseService.read(interactionStepKey).getInteractionElements());
			}
			return getCrudDatabaseService().createPathList(new TreeSet<>(result));
		}, getJsonTransformer());

		// get key for dynamic form
		get("services/interactionStep/:interactionStepKey/form", (req, res) -> {
			InteractionStepKey interactionStepKey = InteractionStepKey.fromEncodedKey(req.params("interactionStepKey"));

			boolean elementValueExists = false;
			/*
			 * QuestionnaireResponse questionnaireResponse = questionnaireResponseDatabaseService.read(interactionStepKey.getQuestionnaireResponseKey()); for (InteractionElementValue value : questionnaireResponse.getValues()) { if (value.getInteractionElement().getKey().equals(interactionStepKey.getInteractionElementKey())) { elementValueExists = true; break; } }
			 */

			List<PathListEntry<String>> result = new ArrayList<>();
			if (!elementValueExists) {
				PathListEntry<String> entry = new PathListEntry<>();
				entry.setKey(interactionStepKey.toEncodedKey(), "interactionStepKey");
				result.add(entry);
			}
			return result;
		}, getJsonTransformer());

		// load form elements for dynamic form
		get("services/interactionStep/:interactionStepKey/form/element", (req, res) -> {
			InteractionStepKey key = InteractionStepKey.fromEncodedKey(req.params("interactionStepKey"));
			InteractionElement currentElement = getCrudDatabaseService().read(key.getInteractionElementKey());
			List<FormValueFieldElementGuiModel> result = new ArrayList<>();

			InteractionStep step = currentElement.getInteractionStep();
			for (InteractionElement element : new TreeSet<>(step.getInteractionElements())) {
				boolean sameNextInteractionStep = element.getNextInteractionStep() == null ? currentElement.getNextInteractionStep() == null : element.getNextInteractionStep().equals(currentElement.getNextInteractionStep());
				if (element instanceof FormValueFieldElement<?> && sameNextInteractionStep) {
					FormValueFieldElement<?> valueElement = (FormValueFieldElement<?>) element;
					result.add(valueElement.toGuiModel(null, null));
				}
			}

			if (result.isEmpty()) {
				throw new RuntimeException("No form elements found");
			}

			return result;
		}, getJsonTransformer());

		// load dynamic form data
		get("services/interactionInlineForm/:interactionStepKey", (req, res) -> {
			List<PathListEntry<Long>> result = new ArrayList<>();
			return result;
		}, getJsonTransformer());
	}

	@Override
	protected void initPut() {
		super.initPut();

		// save dynamic form data
		put("services/interactionInlineForm/:interactionStepKey", (req, res) -> {
			InteractionStepKey interactionStepKey = InteractionStepKey.fromEncodedKey(req.params("interactionStepKey"));
			QuestionnaireResponse questionnaireResponse = questionnaireResponseDatabaseService.read(interactionStepKey.getQuestionnaireResponseKey());
			InteractionElement<?> firstInteractionElement = getCrudDatabaseService().read(interactionStepKey.getInteractionElementKey());
			InteractionStep firstElementNextInteractionStep = firstInteractionElement.getNextInteractionStep();
			HashMap<Integer, FormValueFieldElement<?>> lookup = new HashMap<>();
			int j = 1;
			for (InteractionElement<?> element : new TreeSet<>(firstInteractionElement.getInteractionStep().getInteractionElements())) {
				if (element instanceof FormValueFieldElement<?>) {
					FormValueFieldElement<?> formValueFieldElement = (FormValueFieldElement<?>) element;
					if (element.getNextInteractionStep() == null && firstElementNextInteractionStep == null || element.getNextInteractionStep().equals(firstElementNextInteractionStep)) {
						lookup.put(j, formValueFieldElement);
						j++;
					}
				}
			}

			JsonObject jsonObject = JsonParser.parseString(req.body()).getAsJsonObject();
			for (int k = 1; k <= lookup.size(); k++) {
				JsonElement currentElement = jsonObject.get("interactionFormField" + k);
				if (currentElement == null && lookup.get(k).isRequired()) {
					throw new BackendValidationException("FormRequiredFieldsErrorMessage");
				}
				if (currentElement != null) {
					InteractionElementValue value = null;
					if (lookup.get(k) instanceof FormFileUploadElement) {
						InteractionElementFileValue fileValue = new InteractionElementFileValue();
						JsonArray fileList = currentElement.getAsJsonArray();
						fileList.forEach((element) -> {
							boolean active = element.getAsJsonObject().get("active").getAsBoolean();
							boolean uploadFinished = element.getAsJsonObject().get("uploadFinished").getAsBoolean();
							if (active && uploadFinished) {
								JsonElement keyObject = element.getAsJsonObject().get("key"); // get key object
								JsonElement key = keyObject.getAsJsonObject().get("key"); // get key number
								Long fileKey = key.getAsLong();
								File file = fileDatabaseService.read(fileKey);
								if (file != null) {
									((InteractionElementFileValue) fileValue).getFiles().add(file);
									// file.setInteractionElementFileValue(fileValue);
								}
							}
						});
						value = fileValue;
					} else if (lookup.get(k) instanceof FormDateFieldElement) {
						value = new InteractionElementTextValue();
						((InteractionElementTextValue) value).setTextValue(DateUtility.formatDate(DateUtility.parseDate(currentElement.getAsString(), "yyyy-MM-dd")));
					} else if (lookup.get(k) instanceof FormNumberFieldElement) {
						FormNumberFieldElement formNumberFieldElement = (FormNumberFieldElement) lookup.get(k);
						if (formNumberFieldElement.getDigits() > 0) {
							value = new InteractionElementDoubleValue();
							((InteractionElementDoubleValue) value).setDoubleValue(currentElement.getAsDouble());
						} else {
							value = new InteractionElementLongValue();
							((InteractionElementLongValue) value).setLongValue(currentElement.getAsLong());
						}
					} else {
						value = new InteractionElementTextValue();
						((InteractionElementTextValue) value).setTextValue(currentElement.getAsString());
					}

					value.setQuestionnaireResponse(questionnaireResponse);
					value.setInteractionElement(lookup.get(k));
					interactionElementValueDatabaseService.create(value);

					// special case for new patient questionnaire
					if (!questionnaireResponse.getQuestionnaire().isPatientRequired()) {
						Patient patient = questionnaireResponse.getPatient();
						if (patient == null) {
							patient = new Patient();
							patientDatabaseService.create(patient);
							questionnaireResponse.setPatient(patient);
							questionnaireResponseDatabaseService.update(questionnaireResponse);
						}

						if (value.getInteractionElement().getAdministrationDisplayName().equals("Nachname")) {
							patient.setLastName(value.getDisplayValue());
							patientDatabaseService.update(patient);
						} else if (value.getInteractionElement().getAdministrationDisplayName().equals("Vorname")) {
							patient.setFirstName(value.getDisplayValue());
							patientDatabaseService.update(patient);
						}
					}
				}
			}

			List<PathListEntry<Long>> result = new ArrayList<>();
			return result;
		}, getJsonTransformer());
	}

}
