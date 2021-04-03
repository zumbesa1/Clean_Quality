package ch.zhaw.iwi.devops.server;

import java.util.Arrays;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import ch.zhaw.iwi.devops.model.interactionelement.button.ButtonElement;
import ch.zhaw.iwi.devops.model.interactionelement.button.ButtonSelectionElement;
import ch.zhaw.iwi.devops.model.interactionelement.button.ButtonSelectionElementValue;
import ch.zhaw.iwi.devops.model.interactionelement.form.FormDateFieldElement;
import ch.zhaw.iwi.devops.model.interactionelement.form.FormNumberFieldElement;
import ch.zhaw.iwi.devops.model.interactionelement.form.FormRadioButtonListElement;
import ch.zhaw.iwi.devops.model.interactionelement.form.FormRadioButtonValue;
import ch.zhaw.iwi.devops.model.interactionelement.form.FormSliderFieldElement;
import ch.zhaw.iwi.devops.model.interactionelement.form.FormTextFieldElement;
import ch.zhaw.iwi.devops.model.interactionelement.list.ButtonListElement;
import ch.zhaw.iwi.devops.model.interactionelement.list.TileListElement;
import ch.zhaw.iwi.devops.model.interactionelement.text.TextElement;
import ch.zhaw.iwi.devops.model.interactionstep.InteractionStep;
import ch.zhaw.iwi.devops.model.patient.Patient;
import ch.zhaw.iwi.devops.model.questionnaire.Questionnaire;
import ch.zhaw.iwi.devops.model.questionnaireresponse.QuestionnaireResponse;
import ch.zhaw.iwi.devops.model.sport.Sport;
import ch.zhaw.iwi.devops.model.user.User;
import ch.zhaw.iwi.devops.model.user.permission.PermissionFunction;
import ch.zhaw.iwi.devops.model.user.permission.PermissionFunctionEnum;
import ch.zhaw.iwi.devops.model.user.permission.PermissionRole;
import ch.zhaw.iwi.devops.service.AbstractDatabaseService;
import ch.zhaw.iwi.devops.service.DateUtility;
import ch.zhaw.iwi.devops.service.interactionelement.InteractionElementDatabaseService;
import ch.zhaw.iwi.devops.service.interactionelement.button.ButtonSelectionElementValueDatabaseService;
import ch.zhaw.iwi.devops.service.interactionelement.form.FormRadioButtonValueDatabaseService;
import ch.zhaw.iwi.devops.service.interactionstep.InteractionStepDatabaseService;
import ch.zhaw.iwi.devops.service.patient.PatientDatabaseService;
import ch.zhaw.iwi.devops.service.questionnaire.QuestionnaireDatabaseService;
import ch.zhaw.iwi.devops.service.questionnaireresponse.QuestionnaireResponseDatabaseService;
import ch.zhaw.iwi.devops.service.sport.SportDatabaseService;
import ch.zhaw.iwi.devops.service.user.UserDatabaseService;
import ch.zhaw.iwi.devops.service.user.permission.PermissionFunctionDatabaseService;
import ch.zhaw.iwi.devops.service.user.permission.PermissionRoleDatabaseService;

public class TestDataService extends AbstractDatabaseService {

	private static final Logger logger = LoggerFactory.getLogger(TestDataService.class);

	@Inject
	UserDatabaseService userDatabaseService;

	@Inject
	private PermissionRoleDatabaseService permissionRoleDatabaseService;

	@Inject
	private PermissionFunctionDatabaseService permissionFunctionDatabaseService;

	@Inject
	private PatientDatabaseService patientDatabaseService;

	@Inject
	private SportDatabaseService sportDatabaseService;
	
	@Inject
	private QuestionnaireDatabaseService questionnaireDatabaseService;
	
	@Inject
	private InteractionStepDatabaseService interactionStepDatabaseService;
	
	@Inject
	private InteractionElementDatabaseService interactionElementDatabaseService;
	
	@Inject
	private FormRadioButtonValueDatabaseService formRadioButtonDatabaseService;
	
	@Inject
	private QuestionnaireResponseDatabaseService questionnaireResponseDatabaseService;
	
	@Inject
	private ButtonSelectionElementValueDatabaseService buttonSelectionElementValueDatabaseService;
	
	@Transactional
	public void create() {
		logger.info("Test Data");

		// Patient Permissions
		PermissionFunction administration = createDemoPermissionFunction(PermissionFunctionEnum.readAdministration, "Administration");
		PermissionRole administratorRole = createDemoPermissionRole("Administrator", administration);
		PermissionRole userRole = createDemoPermissionRole("User");

		// Generic
		createTestUser("admin", "Admin", "Doctor", administratorRole);
		createTestUser("test", "Test", "Doctor", userRole);

		// ZHAW
		createTestUser("mosa@zhaw.ch", "Adrian", "Moser", administratorRole);
		createTestUser("desa@zhaw.ch", "Alexandre", "de Spindler", administratorRole);
		createTestUser("faer@zhaw.ch", "Andri", "Färber", administratorRole);

		// Sample Patients
		Patient patient1 = createTestPatient("Anna", "Amacker");
		createTestPatient("Beat", "Bruggisser");
		createTestPatient("Zarko", "Signer");
		
		// Interaction Step
		InteractionStep interactionStepTypeProblems = createTestInteractionStep("Krankheiten und Gesundheitsprobleme");
		InteractionStep interactionStepTypeProblemSelection = createTestInteractionStep("Krankheiten und Gesundheitsprobleme Auswahl");
		InteractionStep interactionStepTypeWeight = createTestInteractionStep("Gewicht");
		InteractionStep interactionStepTypeHeight = createTestInteractionStep("Körpergrösse");
		InteractionStep interactionStepTypeBloodPressure = createTestInteractionStep("Blutdruck");
		InteractionStep interactionStepTypeBloodPressureValues = createTestInteractionStep("Blutdruck Werte");
		InteractionStep interactionStepTypeFamilyWeight = createTestInteractionStep("Übergewicht Familie");
		InteractionStep interactionStepTypeBirthWeight = createTestInteractionStep("Geburtsgewicht");
		InteractionStep interactionStepTypeBirthWeightValue = createTestInteractionStep("Geburtsgewicht");
		InteractionStep interactionStepType20yearsWeight = createTestInteractionStep("Gewicht mit 20");
		InteractionStep interactionStepType20yearsWeightValue = createTestInteractionStep("Gewicht mit 20");
		InteractionStep interactionStepTypeWeightMax = createTestInteractionStep("Gewicht Maximal");
		InteractionStep interactionStepTypeWeightWish = createTestInteractionStep("Gewicht Wunsch");
		InteractionStep interactionStepTypeMedicationCount = createTestInteractionStep("Anzahl Medikamente");
		InteractionStep interactionStepTypeMedicationDetails = createTestInteractionStep("Details Medikament");
		InteractionStep interactionStepTypeMedicationMore = createTestInteractionStep("Weitere Medikamente");
		InteractionStep interactionStepTypeEatingProblems = createTestInteractionStep("Essstörungen");
		InteractionStep interactionStepTypeDrugs = createTestInteractionStep("Noxen");
		InteractionStep interactionStepTypeScaleWichtigkeit = createTestInteractionStep("Wichtigkeit");
		InteractionStep interactionStepTypeScaleBereitschaft = createTestInteractionStep("Bereitschaft");
		InteractionStep interactionStepTypeScaleSelbstwirksamkeit = createTestInteractionStep("Selbstwirksamkeit");
		InteractionStep interactionStepTypeScaleVerstehbarkeit = createTestInteractionStep("Verstehbarkeit");
		InteractionStep interactionStepTypeScaleHandhabbarkeit = createTestInteractionStep("Handhabbarkeit");
		InteractionStep interactionStepTypeScaleSinnhaftigkeit = createTestInteractionStep("Sinnhaftigkeit");
		
		InteractionStep interactionStepHealthSmoking = createTestInteractionStep("Rauchen");
		InteractionStep interactionStepHealthAlcohol = createTestInteractionStep("Alkohol");
		InteractionStep interactionStepHealthEating = createTestInteractionStep("Essverhalten");
		
		InteractionStep interactionStepPatientName = createTestInteractionStep("Patient Name");
		InteractionStep interactionStepPatientCivilStatus = createTestInteractionStep("Patient Zivilstand");
		InteractionStep interactionStepPatientPersonsHousehold = createTestInteractionStep("Patient Personen in Haushalt");
		InteractionStep interactionStepPatientEducation = createTestInteractionStep("Patient Ausbildung");
		InteractionStep interactionStepPatientWorking = createTestInteractionStep("Patient arbeitet");
		InteractionStep interactionStepPatientWorkingPercent = createTestInteractionStep("Patient arbeitet %");
		InteractionStep interactionStepPatientPension = createTestInteractionStep("Patient pensioniert");
		InteractionStep interactionStepPatientIV = createTestInteractionStep("Patient IV");
		InteractionStep interactionStepPatientIVPercent = createTestInteractionStep("Patient IV %");
		
		InteractionStep interactionStepExerciseSteps = createTestInteractionStep("Schritte");
		InteractionStep interactionStepExerciseStepsCounter = createTestInteractionStep("Schritte genau");
		InteractionStep interactionStepExerciseStepsEstimate = createTestInteractionStep("Schritte geschätzt");
		InteractionStep interactionStepExerciseLikeSport = createTestInteractionStep("mag Sport");
		InteractionStep interactionStepExerciseDoSport = createTestInteractionStep("macht Sport");
		InteractionStep interactionStepExerciseSportSelection = createTestInteractionStep("Sportarten");
		InteractionStep interactionStepExerciseSportSelectionOther = createTestInteractionStep("andere Sportarten");
		InteractionStep interactionStepExerciseSportIntensive = createTestInteractionStep("Spitzensport");
		InteractionStep interactionStepExerciseSportIntensiveFromTo = createTestInteractionStep("Spitzensport von/bis");
		InteractionStep interactionStepExerciseMedicalTherapy = createTestInteractionStep("Medizinische Trainingstherapie");
		
		// Questionnaires
		createTestQuestionnaire("Neuer Patient", interactionStepPatientName, "fa-user", false, true);
		Questionnaire questionnaireTypisierung = createTestQuestionnaire("Fragebogen Typisierung", interactionStepTypeProblems, "fa-th", true, true);
		createTestQuestionnaire("Gesundheitsverhalten", interactionStepHealthSmoking, "fa-heartbeat", true, false);
		Questionnaire questionnaireBewegung = createTestQuestionnaire("Bewegungsverhalten", interactionStepExerciseSteps, "fa-shoe-prints", true, false);
		
		// Interaction Elements
		// Fragebogen Typisierung
		createTestTextElement(interactionStepTypeProblems, 10L, "Leiden Sie an Krankheiten oder Gesundheitsproblemen?");
		createTestButtonElement(interactionStepTypeProblems, 20L, interactionStepTypeWeight, "Nein", "Hat Krankheiten und Gesundheitsprobleme", "Nein", null, "wet-asphalt", "fa-times", 2);
		createTestButtonElement(interactionStepTypeProblems, 30L, interactionStepTypeProblemSelection, "Ja", "Hat Krankheiten und Gesundheitsprobleme", "Ja", null, "wet-asphalt", "fa-check", 2);
				
		createTestTextElement(interactionStepTypeProblemSelection, 10L, "Leiden Sie an Krankheiten oder Gesundheitsproblemen?");
		ButtonSelectionElement problemSelection = createTestButtonSelectionElement(interactionStepTypeProblemSelection, 10L, interactionStepTypeWeight, "Familie", "Familie", "Familie", null, "wet-asphalt", "fa-user");
		addButtonSelectionElementValue(problemSelection, "Krankheiten oder Gesundheitsprobleme", "Endokrine", "fa-user", "E00-E90");
		addButtonSelectionElementValue(problemSelection, "Krankheiten oder Gesundheitsprobleme", "Nervensystem", "fa-user", "G00-G99");
		addButtonSelectionElementValue(problemSelection, "Krankheiten oder Gesundheitsprobleme", "Augen", "fa-user", "H00-H59");
		addButtonSelectionElementValue(problemSelection, "Krankheiten oder Gesundheitsprobleme", "Ohren", "fa-user", "H60-H95");
		addButtonSelectionElementValue(problemSelection, "Krankheiten oder Gesundheitsprobleme", "Kreislauf", "fa-user", "I00-I99");
		addButtonSelectionElementValue(problemSelection, "Krankheiten oder Gesundheitsprobleme", "Atmung", "fa-user", "J00-J99");
		addButtonSelectionElementValue(problemSelection, "Krankheiten oder Gesundheitsprobleme", "Verdauung", "fa-user", "K00-K93");
		addButtonSelectionElementValue(problemSelection, "Krankheiten oder Gesundheitsprobleme", "Haut", "fa-user", "L00-L99");
		createTestTextElement(interactionStepTypeProblemSelection, 20L, "Fahren Sie weiter, wenn Sie obenstehende Auswahl getroffen haben");
		createTestButtonElement(interactionStepTypeProblemSelection, 30L, interactionStepTypeWeight, "Weiter", "Weiter", "Weiter", null, "wet-asphalt", "fa-forward", 2);
		
		createTestTextElement(interactionStepTypeWeight, 10L, "Was ist Ihr Körpergewicht in kg?");
		createTestFormNumberFieldElement(interactionStepTypeWeight, 20L, interactionStepTypeHeight, "Gewicht", "Gewicht", 1, 999, 0, true);

		createTestTextElement(interactionStepTypeHeight, 10L, "Was ist Ihre Körpergrösse in cm?");
		createTestFormNumberFieldElement(interactionStepTypeHeight, 20L, interactionStepTypeBloodPressure, "Körpergrösse", "Körpergrösse", 1, 299, 0, true);

		createTestTextElement(interactionStepTypeBloodPressure, 10L, "Kennen Sie Ihren Blutdruck?");
		createTestButtonElement(interactionStepTypeBloodPressure, 20L, interactionStepTypeFamilyWeight, "Nein", "Kennt Blutdruck", "Nein", null, "wet-asphalt", "fa-times", 2);
		createTestButtonElement(interactionStepTypeBloodPressure, 30L, interactionStepTypeBloodPressureValues, "Ja", "Kennt Blutdruck", "Ja", null, "wet-asphalt", "fa-check", 2);
		
		createTestTextElement(interactionStepTypeBloodPressureValues, 10L, "Wieviel beträgt Ihr Blutdruck?");
		createTestFormNumberFieldElement(interactionStepTypeBloodPressureValues, 20L, interactionStepTypeBirthWeight, "Systolisch", "Systolisch", 1, 999, 0, true);
		createTestFormNumberFieldElement(interactionStepTypeBloodPressureValues, 30L, interactionStepTypeBirthWeight, "Diastolisch", "Diastolisch", 1, 999, 0, true);

		createTestTextElement(interactionStepTypeBirthWeight, 10L, "Kennen Sie Ihr Geburtsgewicht?");
		createTestButtonElement(interactionStepTypeBirthWeight, 20L, interactionStepType20yearsWeight, "Nein", "Kennt Geburtsgewicht", "Nein", null, "wet-asphalt", "fa-times", 2);
		createTestButtonElement(interactionStepTypeBirthWeight, 30L, interactionStepTypeBirthWeightValue, "Ja", "Kennt Geburtsgewicht", "Ja", null, "wet-asphalt", "fa-check", 2);

		createTestTextElement(interactionStepTypeBirthWeightValue, 10L, "Wieviel beträgt Ihr Geburtsgewicht?");
		createTestFormNumberFieldElement(interactionStepTypeBirthWeightValue, 20L, interactionStepType20yearsWeight, "Geburtsgewicht (g)", "Geburtsgewicht (g)", 1, 9999, 0, true);

		createTestTextElement(interactionStepType20yearsWeight, 10L, "Kennen Sie Ihr Gewicht mit 20 Jahren?");
		createTestButtonElement(interactionStepType20yearsWeight, 20L, interactionStepTypeWeightMax, "Nein", "Kennt Gewicht mit 20 Jahren", "Nein", null, "wet-asphalt", "fa-times", 2);
		createTestButtonElement(interactionStepType20yearsWeight, 30L, interactionStepType20yearsWeightValue, "Ja", "Kennt Gewicht mit 20 Jahren", "Ja", null, "wet-asphalt", "fa-check", 2);

		createTestTextElement(interactionStepType20yearsWeightValue, 10L, "Wieviel betrug Ihr Gewicht mit 20 Jahren?");
		createTestFormNumberFieldElement(interactionStepType20yearsWeightValue, 20L, interactionStepTypeWeightMax, "Gewicht mit 20 Jahren (kg)", "Gewicht mit 20 Jahren (kg)", 1, 999, 0, true);
		
		createTestTextElement(interactionStepTypeWeightMax, 10L, "In welchem Alter hatten Sie Ihr höchstes Gewicht?");
		createTestFormNumberFieldElement(interactionStepTypeWeightMax, 20L, interactionStepTypeWeightWish, "Alter (Jahre)", "Alter (Jahre)", 1, 120, 0, true);
		createTestFormNumberFieldElement(interactionStepTypeWeightMax, 30L, interactionStepTypeWeightWish, "Gewicht (kg)", "Gewicht (kg)", 1, 999, 0, true);

		createTestTextElement(interactionStepTypeWeightWish, 10L, "Mit welchem Gewicht wären Sie zufrieden?");
		createTestFormNumberFieldElement(interactionStepTypeWeightWish, 20L, interactionStepTypeFamilyWeight, "Zufriedenstellendes Gewicht (kg)", "Zufriedenstellendes Gewicht (kg)", 1, 999, 0, true);
		
		createTestTextElement(interactionStepTypeFamilyWeight, 10L, "Gibt es Übergewicht in der Familie?");
		ButtonSelectionElement family = createTestButtonSelectionElement(interactionStepTypeFamilyWeight, 10L, interactionStepTypeMedicationCount, "Familie", "Familie", "Familie", null, "wet-asphalt", "fa-user");
		addButtonSelectionElementValue(family, "Übergewicht in der Familie", "Mutter", "fa-user");
		addButtonSelectionElementValue(family, "Übergewicht in der Familie", "Vater", "fa-user");
		addButtonSelectionElementValue(family, "Übergewicht in der Familie", "Schwester", "fa-user");
		addButtonSelectionElementValue(family, "Übergewicht in der Familie", "Bruder", "fa-user");
		addButtonSelectionElementValue(family, "Übergewicht in der Familie", "Kinder", "fa-user");
		addButtonSelectionElementValue(family, "Übergewicht in der Familie", "Sonstige Verwandte", "fa-user");
		createTestTextElement(interactionStepTypeFamilyWeight, 20L, "Fahren Sie weiter, wenn Sie obenstehende Auswahl getroffen haben");
		createTestButtonElement(interactionStepTypeFamilyWeight, 30L, interactionStepTypeMedicationCount, "Weiter", "Weiter", "Weiter", null, "wet-asphalt", "fa-forward", 2);
		
		createTestTextElement(interactionStepTypeMedicationCount, 10L, "Nehmen Sie regelmässig Medikamente?");
		createTestButtonElement(interactionStepTypeMedicationCount, 20L, interactionStepTypeEatingProblems, "Nein", "Einnahme Medikamente", "Nein", null, "wet-asphalt", "fa-times", 2);
		createTestButtonElement(interactionStepTypeMedicationCount, 30L, interactionStepTypeMedicationDetails, "Ja", "Einnahme Medikamente", "Ja", null, "wet-asphalt", "fa-check", 2);
		
		createTestTextElement(interactionStepTypeMedicationDetails, 10L, "Bitte erfassen Sie ein Medikament und die Dosierung:");
		createTestFormTextFieldElement(interactionStepTypeMedicationDetails, 20L, interactionStepTypeMedicationMore, "Medikament", "Medikament", 2, 1, true);
		createTestFormTextFieldElement(interactionStepTypeMedicationDetails, 30L, interactionStepTypeMedicationMore, "Dosierung", "Dosierung", 2, 1, true);

		createTestTextElement(interactionStepTypeMedicationMore, 10L, "Haben Sie alle Medikamente erfasst?");
		createTestButtonElement(interactionStepTypeMedicationMore, 20L, interactionStepTypeMedicationDetails, "Weitere erfassen", "Weitere Medikamente", "Nein", null, "wet-asphalt", "fa-times", 2);
		createTestButtonElement(interactionStepTypeMedicationMore, 30L, interactionStepTypeEatingProblems, "Ja, alle erfasst", "Weitere Medikamente", "Ja", null, "wet-asphalt", "fa-check", 2);
		
		createTestTextElement(interactionStepTypeEatingProblems, 10L, "Leiden Sie an Esstörungen?");
		ButtonSelectionElement eatingProblemsSelection = createTestButtonSelectionElement(interactionStepTypeEatingProblems, 10L, interactionStepTypeDrugs, "Familie", "Familie", "Familie", null, "wet-asphalt", "fa-user");
		addButtonSelectionElementValue(eatingProblemsSelection, "Esstörungen", "Snacking", "fa-cookie-bite");
		addButtonSelectionElementValue(eatingProblemsSelection, "Esstörungen", "Sweet-Eating", "fa-cookie");
		addButtonSelectionElementValue(eatingProblemsSelection, "Esstörungen", "Fat-Eating", "fa-hamburger");
		addButtonSelectionElementValue(eatingProblemsSelection, "Esstörungen", "Night-Eating", "fa-moon");
		createTestTextElement(interactionStepTypeEatingProblems, 20L, "Fahren Sie weiter, wenn Sie obenstehende Auswahl getroffen haben");
		createTestButtonElement(interactionStepTypeEatingProblems, 30L, interactionStepTypeDrugs, "Weiter", "Weiter", "Weiter", null, "wet-asphalt", "fa-forward", 2);

		createTestTextElement(interactionStepTypeDrugs, 10L, "Nehmen Sie einer der folgenden Substanzen ein?");
		ButtonSelectionElement drugsSelection = createTestButtonSelectionElement(interactionStepTypeDrugs, 10L, interactionStepTypeScaleWichtigkeit, "Familie", "Familie", "Familie", null, "wet-asphalt", "fa-user");
		addButtonSelectionElementValue(drugsSelection, "Einnahme von Substanzen", "Kokain", "fa-syringe");
		addButtonSelectionElementValue(drugsSelection, "Einnahme von Substanzen", "Opiate", "fa-joint");
		addButtonSelectionElementValue(drugsSelection, "Einnahme von Substanzen", "Alkohol", "fa-wine-glass-alt");
		addButtonSelectionElementValue(drugsSelection, "Einnahme von Substanzen", "Rauchen", "fa-smoking");
		createTestTextElement(interactionStepTypeDrugs, 20L, "Fahren Sie weiter, wenn Sie obenstehende Auswahl getroffen haben");
		createTestButtonElement(interactionStepTypeDrugs, 30L, interactionStepTypeScaleWichtigkeit, "Weiter", "Weiter", "Weiter", null, "wet-asphalt", "fa-forward", 2);
		
		createTestTextElement(interactionStepTypeScaleWichtigkeit, 10L, "Auf einer Skala von 0-10, wie wichtig ist mir meine Gewichtsabnahme?<br>(0=überhaupt nicht wichtig; 10=sehr wichtig)");
		createTestFormSliderFieldElement(interactionStepTypeScaleWichtigkeit, 20L, interactionStepTypeScaleBereitschaft, "Wichtigkeit 0-10", "Wichtigkeit", 0, 10, true);
		
		createTestTextElement(interactionStepTypeScaleBereitschaft, 10L, "Auf einer Skala von 0-10, wie hoch schätze ich meine Bereitschaft ein, mein Gewicht zu reduzieren?<br>(0=keine Bereitschaft; 10=sehr grosse Bereitschaft)");
		createTestFormSliderFieldElement(interactionStepTypeScaleBereitschaft, 20L, interactionStepTypeScaleSelbstwirksamkeit, "Bereitschaft 0-10", "Bereitschaft", 0, 10, true);

		createTestTextElement(interactionStepTypeScaleSelbstwirksamkeit, 10L, "Auf einer Skale von 0-10, wie zuversichtlich bin ich, mein Gewicht zu reduzieren?<br>(0=keine Zuversicht; 10=sehr grosse Zuversicht)");
		createTestFormSliderFieldElement(interactionStepTypeScaleSelbstwirksamkeit, 20L, interactionStepTypeScaleVerstehbarkeit, "Selbstwirksamkeit 0-10", "Selbstwirksamkeit", 0, 10, true);

		createTestTextElement(interactionStepTypeScaleVerstehbarkeit, 10L, "Auf einer Skala von 0-10, wie geordnet, vorhersehbar und erklärbar verlief mein bisheriges Leben?<br>(0=nie; 10=immer)");
		createTestFormSliderFieldElement(interactionStepTypeScaleVerstehbarkeit, 20L, interactionStepTypeScaleHandhabbarkeit, "Verstehbarkeit 0-10", "Verstehbarkeit", 0, 10, true);

		createTestTextElement(interactionStepTypeScaleHandhabbarkeit, 10L, "Auf einer Skala von 0-10, wie ausgewogen habe ich bisher mein Leben empfunden<br>(0=überhaupt nicht ausgewogen; 10=sehr ausgewogen)");
		createTestFormSliderFieldElement(interactionStepTypeScaleHandhabbarkeit, 20L, interactionStepTypeScaleSinnhaftigkeit, "Handhabbarkeit 0-10", "Handhabbarkeit", 0, 10, true);

		createTestTextElement(interactionStepTypeScaleSinnhaftigkeit, 10L, "Auf einer Skala von 0-10, wie stark konnte ich Einfluss auf meine Lebenssituationen nehmen?<br>(0=überhaupt keinen Einfluss; 10=sehr grossen Einfluss)");
		createTestFormSliderFieldElement(interactionStepTypeScaleSinnhaftigkeit, 20L, null, "Sinnhaftigkeit 0-10", "Sinnhaftigkeit", 0, 10, true);
		
		// Gesundheitsverhalten
		createTestTextElement(interactionStepHealthSmoking, 10L, "Rauchen Sie?");
		createTestButtonElement(interactionStepHealthSmoking, 20L, interactionStepHealthAlcohol, "Nein", "Rauchen", "Nein", null, "wet-asphalt", "fa-times", 2);
		createTestButtonElement(interactionStepHealthSmoking, 30L, interactionStepHealthAlcohol, "Gelegentlich", "Rauchen", "Gelegentlich", null, "wet-asphalt", "fa-clock", 2);
		createTestButtonElement(interactionStepHealthSmoking, 40L, interactionStepHealthAlcohol, "Ja", "Rauchen", "Ja", null, "wet-asphalt", "fa-check", 2);

		createTestTextElement(interactionStepHealthAlcohol, 10L, "Wie ist ihr Alkoholkonsum?");
		createTestButtonElement(interactionStepHealthAlcohol, 20L, interactionStepHealthEating, "Nie", "Alkoholkonsum", "Nie", null, "wet-asphalt", "fa-times", 2);
		createTestButtonElement(interactionStepHealthAlcohol, 30L, interactionStepHealthEating, "Gelegentlich", "Alkoholkonsum", "Gelegentlich", null, "wet-asphalt", "fa-clock", 2);
		createTestButtonElement(interactionStepHealthAlcohol, 40L, interactionStepHealthEating, "Regelmässig", "Alkoholkonsum", "Regelmässig", null, "wet-asphalt", "fa-check", 2);
		createTestButtonElement(interactionStepHealthAlcohol, 50L, interactionStepHealthEating, "Problematisch", "Alkoholkonsum", "Problematisch", null, "wet-asphalt", "fa-exclamation-triangle", 2);

		createTestTextElement(interactionStepHealthEating, 10L, "Wie ist ihr Essverhalten?");
		createTestButtonElement(interactionStepHealthEating, 20L, null, "Ausgewogen", "Essverhalten", "Ausgewogen", null, "wet-asphalt", "fa-check", 2);
		createTestButtonElement(interactionStepHealthEating, 30L, null, "Unausgewogen", "Essverhalten", "Unausgewogen", null, "wet-asphalt", "fa-times", 2);
		createTestButtonElement(interactionStepHealthEating, 40L, null, "Problematisch", "Essverhalten", "Problematisch", null, "wet-asphalt", "fa-exclamation-triangle", 2);

		// Patient erfassen
		createTestTextElement(interactionStepPatientName, 10L, "Wie ist ihr Name?");
		createTestFormTextFieldElement(interactionStepPatientName, 20L, interactionStepPatientCivilStatus, "Vorname", "Vorname", 1, 1, true);
		createTestFormTextFieldElement(interactionStepPatientName, 30L, interactionStepPatientCivilStatus, "Nachname", "Nachname", 1, 1, true);
		
		createTestTextElement(interactionStepPatientCivilStatus, 10L, "Wie ist ihr Zivilstand?");
		createTestButtonElement(interactionStepPatientCivilStatus, 20L, interactionStepPatientPersonsHousehold, "Ledig", "Zivilstand", "Ledig", null, "wet-asphalt", "fa-user", 2);
		createTestButtonElement(interactionStepPatientCivilStatus, 30L, interactionStepPatientPersonsHousehold, "Verheiratet", "Zivilstand", "Verheiratet", null, "wet-asphalt", "fa-ring", 2);
		createTestButtonElement(interactionStepPatientCivilStatus, 40L, interactionStepPatientPersonsHousehold, "Partnerschaft", "Zivilstand", "Partnerschaft", null, "wet-asphalt", "fa-handshake", 2);
		createTestButtonElement(interactionStepPatientCivilStatus, 50L, interactionStepPatientPersonsHousehold, "Geschieden", "Zivilstand", "Geschieden", null, "wet-asphalt", "fa-exchange-alt", 2);
		
		createTestTextElement(interactionStepPatientPersonsHousehold, 10L, "Wieviele Personen leben in Ihrem Haushalt?");
		createTestButtonElement(interactionStepPatientPersonsHousehold, 20L, interactionStepPatientEducation, "1", "Haushaltgrösse", "1", null, "wet-asphalt", "fa-dice-one", 1);
		createTestButtonElement(interactionStepPatientPersonsHousehold, 30L, interactionStepPatientEducation, "2", "Haushaltgrösse", "2", null, "wet-asphalt", "fa-dice-two", 1);
		createTestButtonElement(interactionStepPatientPersonsHousehold, 40L, interactionStepPatientEducation, "3", "Haushaltgrösse", "3", null, "wet-asphalt", "fa-dice-three", 1);
		createTestButtonElement(interactionStepPatientPersonsHousehold, 50L, interactionStepPatientEducation, "4", "Haushaltgrösse", "4", null, "wet-asphalt", "fa-dice-four", 1);
		createTestButtonElement(interactionStepPatientPersonsHousehold, 60L, interactionStepPatientEducation, "5", "Haushaltgrösse", "5", null, "wet-asphalt", "fa-dice-five", 1);
		createTestButtonElement(interactionStepPatientPersonsHousehold, 70L, interactionStepPatientEducation, "6 und mehr", "Haushaltgrösse", "6 und mehr", null, "wet-asphalt", "fa-dice-six", 2);

		createTestTextElement(interactionStepPatientEducation, 10L, "Welches ist Ihre höchste Ausbildung?");
		createTestButtonElement(interactionStepPatientEducation, 20L, interactionStepPatientWorking, "Primarschule", "Ausbildung", "Primarschule", null, "wet-asphalt", "fa-school", 2);
		createTestButtonElement(interactionStepPatientEducation, 30L, interactionStepPatientWorking, "Sekundarschule", "Ausbildung", "Sekundarschule", null, "wet-asphalt", "fa-school", 2);
		createTestButtonElement(interactionStepPatientEducation, 40L, interactionStepPatientWorking, "Gymnasium", "Ausbildung", "Gymnasium", null, "wet-asphalt", "fa-school", 2);
		createTestButtonElement(interactionStepPatientEducation, 50L, interactionStepPatientWorking, "Berufslehre", "Ausbildung", "Berufslehre", null, "wet-asphalt", "fa-school", 2);
		createTestButtonElement(interactionStepPatientEducation, 60L, interactionStepPatientWorking, "Fachmittelschule", "Ausbildung", "Fachmittelschule", null, "wet-asphalt", "fa-school", 2);
		createTestButtonElement(interactionStepPatientEducation, 70L, interactionStepPatientWorking, "Berufsmittelschule", "Ausbildung", "Berufsmittelschule", null, "wet-asphalt", "fa-school", 2);
		createTestButtonElement(interactionStepPatientEducation, 80L, interactionStepPatientWorking, "Universität/Hochschule", "Ausbildung", "Universität/Hochschule", null, "wet-asphalt", "fa-university", 2);
		
		createTestTextElement(interactionStepPatientWorking, 10L, "Sind Sie derzeit arbeitstätig?");
		createTestButtonElement(interactionStepPatientWorking, 20L, interactionStepPatientWorkingPercent, "Ja", "Arbeitstätig", "Ja", null, "wet-asphalt", "fa-check", 2);
		createTestButtonElement(interactionStepPatientWorking, 30L, interactionStepPatientPension, "Nein", "Arbeitstätig", "Nein", null, "wet-asphalt", "fa-times", 2);

		createTestTextElement(interactionStepPatientPension, 10L, "Sind Sie pensioniert?");
		createTestButtonElement(interactionStepPatientPension, 20L, null, "Ja", "Pensioniert", "Ja", null, "wet-asphalt", "fa-check", 2);
		createTestButtonElement(interactionStepPatientPension, 30L, interactionStepPatientIV, "Nein", "Pensioniert", "Nein", null, "wet-asphalt", "fa-times", 2);
		
		createTestTextElement(interactionStepPatientWorkingPercent, 10L, "Ihr Beschäftigungsgrad in Prozent?");
		createTestButtonElement(interactionStepPatientWorkingPercent, 20L, interactionStepPatientIV, "unter 10", "Beschäftigungsgrad", "unter 10", null, "wet-asphalt", "fa-percent", 2);
		createTestButtonElement(interactionStepPatientWorkingPercent, 30L, interactionStepPatientIV, "11-24", "Beschäftigungsgrad", "11-24", null, "wet-asphalt", "fa-percent", 2);
		createTestButtonElement(interactionStepPatientWorkingPercent, 40L, interactionStepPatientIV, "25-50", "Beschäftigungsgrad", "25-50", null, "wet-asphalt", "fa-percent", 2);
		createTestButtonElement(interactionStepPatientWorkingPercent, 50L, interactionStepPatientIV, "51-79", "Beschäftigungsgrad", "51-79", null, "wet-asphalt", "fa-percent", 2);
		createTestButtonElement(interactionStepPatientWorkingPercent, 60L, interactionStepPatientIV, "80-99", "Beschäftigungsgrad", "80-99", null, "wet-asphalt", "fa-percent", 2);
		createTestButtonElement(interactionStepPatientWorkingPercent, 70L, interactionStepPatientIV, "100", "Beschäftigungsgrad", "100", null, "wet-asphalt", "fa-percent", 2);
		
		createTestTextElement(interactionStepPatientIV, 10L, "Erhalten Sie eine IV-Rente?");
		createTestButtonElement(interactionStepPatientIV, 20L, interactionStepPatientIVPercent, "Ja", "Arbeitstätig", "Ja", null, "wet-asphalt", "fa-check", 2);
		createTestButtonElement(interactionStepPatientIV, 30L, null, "Nein", "Arbeitstätig", "Nein", null, "wet-asphalt", "fa-times", 2);

		createTestTextElement(interactionStepPatientIVPercent, 10L, "Ihre Berentung in Prozent?");
		createTestButtonElement(interactionStepPatientIVPercent, 20L, null, "unter 10", "Berentung", "unter 10", null, "wet-asphalt", "fa-percent", 2);
		createTestButtonElement(interactionStepPatientIVPercent, 30L, null, "11-24", "Berentung", "11-24", null, "wet-asphalt", "fa-percent", 2);
		createTestButtonElement(interactionStepPatientIVPercent, 40L, null, "25-50", "Berentung", "25-50", null, "wet-asphalt", "fa-percent", 2);
		createTestButtonElement(interactionStepPatientIVPercent, 50L, null, "51-79", "Berentung", "51-79", null, "wet-asphalt", "fa-percent", 2);
		createTestButtonElement(interactionStepPatientIVPercent, 60L, null, "80-99", "Berentung", "80-99", null, "wet-asphalt", "fa-percent", 2);
		createTestButtonElement(interactionStepPatientIVPercent, 70L, null, "100", "Berentung", "100", null, "wet-asphalt", "fa-percent", 2);

		// Bewegungsverhalten
		createTestTextElement(interactionStepExerciseSteps, 10L, "Kennen Sie Ihre Anzahl zurückgelegten Schritte pro Tag?");
		createTestButtonElement(interactionStepExerciseSteps, 20L, interactionStepExerciseStepsCounter, "Ja, durch Schrittzähler", "Schritterfassung", "Ja", null, "wet-asphalt", "fa-check", 2);
		createTestButtonElement(interactionStepExerciseSteps, 30L, interactionStepExerciseStepsEstimate, "Ja, geschätzt", "Schritterfassung", "Ja", null, "wet-asphalt", "fa-check", 2);
		createTestButtonElement(interactionStepExerciseSteps, 40L, interactionStepExerciseLikeSport, "Nein", "Schritterfassung", "Nein", null, "wet-asphalt", "fa-times", 2);
		
		createTestTextElement(interactionStepExerciseStepsCounter, 10L, "Wieviele Schritte misst der Schrittzähler im Schnitt pro Tag?");		
		createTestFormNumberFieldElement(interactionStepExerciseStepsCounter, 20L, interactionStepExerciseLikeSport, "Anzahl Schritte pro Tag", "Anzahl Schritte pro Tag", 0, 100000, 0, true);
		
		createTestTextElement(interactionStepExerciseStepsEstimate, 10L, "Wieviele Schritte geschätzt pro Tag?");		
		createTestButtonElement(interactionStepExerciseStepsEstimate, 10L, interactionStepExerciseLikeSport, "bis 3000", "Anzahl Schritte pro Tag", "bis 3000", null, "wet-asphalt", "fa-shoe-prints", 2);
		createTestButtonElement(interactionStepExerciseStepsEstimate, 10L, interactionStepExerciseLikeSport, "3000-7000", "Anzahl Schritte pro Tag", "3000-7000", null, "wet-asphalt", "fa-shoe-prints", 2);
		createTestButtonElement(interactionStepExerciseStepsEstimate, 10L, interactionStepExerciseLikeSport, "7000-10000", "Anzahl Schritte pro Tag", "7000-10000", null, "wet-asphalt", "fa-shoe-prints", 2);
		createTestButtonElement(interactionStepExerciseStepsEstimate, 10L, interactionStepExerciseLikeSport, "10000 und mehr", "Anzahl Schritte pro Tag", "10000 und mehr", null, "wet-asphalt", "fa-shoe-prints", 2);
		
		createTestTextElement(interactionStepExerciseLikeSport, 10L, "Mögen Sie Sport?");		
		createTestButtonElement(interactionStepExerciseLikeSport, 20L, interactionStepExerciseDoSport, "Ja", "Sport", "Ja", null, "wet-asphalt", "fa-thumbs-up", 2);
		createTestButtonElement(interactionStepExerciseLikeSport, 30L, interactionStepExerciseDoSport, "Nein", "Sport", "Nein", null, "wet-asphalt", "fa-thumbs-down", 2);
		
		createTestTextElement(interactionStepExerciseDoSport, 10L, "Machen Sie aktuell Sport?");		
		createTestButtonElement(interactionStepExerciseDoSport, 20L, interactionStepExerciseSportSelection, "Ja", "Sport", "Ja", null, "wet-asphalt", "fa-check", 2);
		createTestButtonElement(interactionStepExerciseDoSport, 30L, interactionStepExerciseSportIntensive, "Nein", "Sport", "Nein", null, "wet-asphalt", "fa-times", 2);
		
		createTestTextElement(interactionStepExerciseSportSelection, 10L, "Welche Sportarten üben Sie aus?");	
		ButtonSelectionElement sportSelection = createTestButtonSelectionElement(interactionStepExerciseSportSelection, 20L, interactionStepExerciseSportIntensive, "Sport", "Sport", "Sport", null, "wet-asphalt", "fa-heartbeat");
		addButtonSelectionElementValue(sportSelection, "Sportarten", "Fitness", "fa-heartbeat");
		addButtonSelectionElementValue(sportSelection, "Sportarten", "Jogging", "fa-running");
		addButtonSelectionElementValue(sportSelection, "Sportarten", "Krafttraining", "fa-dumbbell");
		addButtonSelectionElementValue(sportSelection, "Sportarten", "Velo", "fa-bicycle");
		addButtonSelectionElementValue(sportSelection, "Sportarten", "Walking", "fa-walking");
		addButtonSelectionElementValue(sportSelection, "Sportarten", "Yoga", "fa-user");
		createTestTextElement(interactionStepExerciseSportSelection, 20L, "Fahren Sie weiter, wenn Sie obenstehende Auswahl getroffen haben");
		createTestButtonElement(interactionStepExerciseSportSelection, 30L, interactionStepExerciseSportIntensive, "Weiter", "Weiter", "Weiter", null, "wet-asphalt", "fa-forward", 2);
		
		createTestTextElement(interactionStepExerciseSportSelection, 1000L, "Üben Sie andere Sportarten aus?");		
		createTestButtonElement(interactionStepExerciseSportSelection, 1020L, interactionStepExerciseSportSelectionOther, "Ja", "andere Sportarten", "Ja", null, "wet-asphalt", "fa-check", 2);
		createTestButtonElement(interactionStepExerciseSportSelection, 1030L, interactionStepExerciseSportIntensive, "Nein", "andere Sportarten", "Nein", null, "wet-asphalt", "fa-times", 2);
		
		createTestTextElement(interactionStepExerciseSportSelectionOther, 10L, "Welche weiteren Sportarten üben Sie aus?");	
		createTestFormTextFieldElement(interactionStepExerciseSportSelectionOther, 20L, interactionStepExerciseSportIntensive, "weitere Sportarten", "weitere Sportarten", 2, 5, true);
		
		createTestTextElement(interactionStepExerciseSportIntensive, 10L, "Haben Sie früher intensiv Sport und/oder Spitzensport betrieben?");		
		createTestButtonElement(interactionStepExerciseSportIntensive, 20L, interactionStepExerciseSportIntensiveFromTo, "Ja", "Spitzensport", "Ja", null, "wet-asphalt", "fa-check", 2);
		createTestButtonElement(interactionStepExerciseSportIntensive, 30L, interactionStepExerciseMedicalTherapy, "Nein", "Spitzensport", "Nein", null, "wet-asphalt", "fa-times", 2);

		createTestTextElement(interactionStepExerciseSportIntensiveFromTo, 10L, "In welchem Alter haben Sie intensiv Sport und/oder Spitzensport betrieben?");	
		createTestFormNumberFieldElement(interactionStepExerciseSportIntensiveFromTo, 20L, interactionStepExerciseMedicalTherapy, "Alter von", "Alter von", 0, 110, 0, true);
		createTestFormNumberFieldElement(interactionStepExerciseSportIntensiveFromTo, 20L, interactionStepExerciseMedicalTherapy, "Alter bis", "Alter bis", 0, 110, 0, true);
		
		createTestTextElement(interactionStepExerciseMedicalTherapy, 10L, "Haben Sie schon einmal medizinische Trainingstherapie erhalten?");		
		createTestButtonElement(interactionStepExerciseMedicalTherapy, 20L, null, "Ja", "medizinische Trainingstherapie", "Ja", null, "wet-asphalt", "fa-check", 2);
		createTestButtonElement(interactionStepExerciseMedicalTherapy, 30L, null, "Nein", "medizinische Trainingstherapie", "Nein", null, "wet-asphalt", "fa-times", 2);
		
		// Sports
		createTestSport("Walking", null, "fa-walking");
		createTestSport("Jogging", null, "fa-running");
		createTestSport("Krafttraining", null, "fa-dumbbell");
		createTestSport("Fitness", "z.B. Zirkeltraining, Pump, Zumba", "fa-heartbeat");
		createTestSport("Velo", null, "fa-bicycle");
		createTestSport("Yoga", "bzw. Pilates oder ähnlich", "fa-user");
		
		// Questionnaire Response
		createTestQuestionnareResponse(patient1, questionnaireBewegung);
		createTestQuestionnareResponse(patient1, questionnaireTypisierung);
	}
	
	private Sport createTestSport(String name, String description, String icon) {
		Sport sport = new Sport();
		sport.setName(name);
		sport.setDescription(description);
		sport.setIcon(icon);
		sportDatabaseService.create(sport);
		return sport;
	}

	private Patient createTestPatient(String firstName, String lastName) {
		Patient patient = new Patient();
		patient.setFirstName(firstName);
		patient.setLastName(lastName);
		patientDatabaseService.create(patient);
		return patient;
	}

	private User createTestUser(String email, String firstName, String lastName, PermissionRole... permissionRoles) {
		User user = new User();
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(".");
		user.setRepeatPassword(".");
		user.setEvtCreationDate(DateUtility.truncateToDay(new Date()));
		user.getPermissionRoles().addAll(Arrays.asList(permissionRoles));
		userDatabaseService.create(user);
		return user;
	}
	
	private Questionnaire createTestQuestionnaire(String name, InteractionStep firstInteractionStep, String icon, boolean patientRequired, boolean active) {
		Questionnaire questionnaire = new Questionnaire();
		questionnaire.setName(name);
		questionnaire.setFirstInteractionStep(firstInteractionStep);
		questionnaire.setIcon(icon);
		questionnaire.setPatientRequired(patientRequired);
		questionnaire.setActive(active);
		questionnaireDatabaseService.create(questionnaire);
		return questionnaire;
	}
	
	private QuestionnaireResponse createTestQuestionnareResponse(Patient patient, Questionnaire questionnaire) {
		QuestionnaireResponse questionnaireResponse = new QuestionnaireResponse();
		questionnaireResponse.setPatient(patient);
		questionnaireResponse.setQuestionnaire(questionnaire);
		questionnaireResponse.setDate(new Date());
		questionnaireResponseDatabaseService.create(questionnaireResponse);
		return questionnaireResponse;
	}
	
	private InteractionStep createTestInteractionStep(String name) {
		InteractionStep interactionStep = new InteractionStep();
		interactionStep.setName(name);
		interactionStepDatabaseService.create(interactionStep);
		return interactionStep;
	}
	
	private TextElement createTestTextElement(InteractionStep interactionStep, Long sortOrder, String text) {
		TextElement textElement = new TextElement();
		textElement.setInteractionStep(interactionStep);
		textElement.setSortOrder(sortOrder);

		textElement.setText(text);
		interactionElementDatabaseService.create(textElement);
		return textElement;
	}

	private TileListElement createTestTileListElement(InteractionStep interactionStep, Long sortOrder, boolean isSearchRequired, boolean isSearchVisible) {
		TileListElement listElement = new TileListElement();
		setTestTileListElementAttributes(listElement, interactionStep, sortOrder, isSearchRequired, isSearchVisible);
		interactionElementDatabaseService.create(listElement);
		return listElement;
	}

	private ButtonListElement createTestButtonListElement(InteractionStep interactionStep, Long sortOrder, InteractionStep nextInteractionStep, String valueDescription, String exportKey, boolean isSearchRequired, boolean isSearchVisible) {
		ButtonListElement listElement = new ButtonListElement();
		setTestTileListElementAttributes(listElement, interactionStep, sortOrder, isSearchRequired, isSearchVisible);
		listElement.setNextInteractionStep(nextInteractionStep);
		listElement.setValueDescription(valueDescription);
		listElement.setExportKey(exportKey);
		interactionElementDatabaseService.create(listElement);
		return listElement;
	}

	private void setTestTileListElementAttributes(TileListElement listElement, InteractionStep interactionStep, Long sortOrder, boolean isSearchRequired, boolean isSearchVisible) {
		listElement.setInteractionStep(interactionStep);
		listElement.setSortOrder(sortOrder);

		listElement.setSearchRequired(isSearchRequired);
		listElement.setSearchVisible(isSearchVisible);
	}

	private ButtonElement createTestButtonElement(InteractionStep interactionStep, Long sortOrder, InteractionStep nextInteractionStep, String name, String valueDescription, String value, String tooltip, String color, String icon, int width, String... details) {
		ButtonElement buttonElement = new ButtonElement();
		buttonElement.setInteractionStep(interactionStep);
		buttonElement.setSortOrder(sortOrder);

		buttonElement.setNextInteractionStep(nextInteractionStep);
		buttonElement.setName(name);
		buttonElement.getDetails().addAll(Arrays.asList(details));
		buttonElement.setValueDescription(valueDescription);
		buttonElement.setValue(value);
		buttonElement.setTooltip(tooltip);
		buttonElement.setColor(color);
		buttonElement.setIcon(icon);
		buttonElement.setWidth(width);
		interactionElementDatabaseService.create(buttonElement);
		return buttonElement;
	}
	
	private ButtonSelectionElement createTestButtonSelectionElement(InteractionStep interactionStep, Long sortOrder, InteractionStep nextInteractionStep, String name, String valueDescription, String value, String tooltip, String color, String icon) {
		ButtonSelectionElement buttonSelectionElement = new ButtonSelectionElement();
		buttonSelectionElement.setInteractionStep(interactionStep);
		buttonSelectionElement.setSortOrder(sortOrder);

		buttonSelectionElement.setNextInteractionStep(nextInteractionStep);
		buttonSelectionElement.setName(name);
		buttonSelectionElement.setValueDescription(valueDescription);
		buttonSelectionElement.setValue(value);
		buttonSelectionElement.setTooltip(tooltip);
		buttonSelectionElement.setColor(color);
		buttonSelectionElement.setIcon(icon);
		interactionElementDatabaseService.create(buttonSelectionElement);
		return buttonSelectionElement;
	}
	
	private ButtonSelectionElementValue addButtonSelectionElementValue(ButtonSelectionElement buttonSelectionElement, String name, String value, String icon, String... details) {
		ButtonSelectionElementValue buttonSelectionElementValue = new ButtonSelectionElementValue();
		buttonSelectionElementValue.setName(name);
		buttonSelectionElementValue.getDetails().addAll(Arrays.asList(details));
		buttonSelectionElementValue.setValue(value);
		buttonSelectionElementValue.setIcon(icon);
		buttonSelectionElementValue.setButtonSelectionElement(buttonSelectionElement);
		buttonSelectionElementValueDatabaseService.create(buttonSelectionElementValue);
		
		buttonSelectionElement.getValues().add(buttonSelectionElementValue);
		interactionElementDatabaseService.update(buttonSelectionElement);
		return buttonSelectionElementValue;
	}

	private FormTextFieldElement createTestFormTextFieldElement(InteractionStep interactionStep, Long sortOrder, InteractionStep nextInteractionStep, String name, String exportKey, int width, int height, boolean required) {
		FormTextFieldElement formElement = new FormTextFieldElement();
		formElement.setInteractionStep(interactionStep);
		formElement.setSortOrder(sortOrder);
		formElement.setName(name);
		formElement.setExportKey(exportKey);
		formElement.setWidth(width);
		formElement.setHeight(height);
		formElement.setRequired(required);
		formElement.setNextInteractionStep(nextInteractionStep);
		interactionElementDatabaseService.create(formElement);
		return formElement;
	}

	private FormDateFieldElement createTestFormDateFieldElement(InteractionStep interactionStep, Long sortOrder, InteractionStep nextInteractionStep, String name, String exportKey, int width, boolean required) {
		FormDateFieldElement formElement = new FormDateFieldElement();
		formElement.setInteractionStep(interactionStep);
		formElement.setSortOrder(sortOrder);

		formElement.setNextInteractionStep(nextInteractionStep);
		formElement.setName(name);
		formElement.setExportKey(exportKey);
		formElement.setWidth(width);
		formElement.setRequired(required);
		interactionElementDatabaseService.create(formElement);
		return formElement;
	}

	private FormRadioButtonListElement createTestFormRadioButtonListElement(InteractionStep interactionStep, Long sortOrder, InteractionStep nextInteractionStep, String name, String exportKey, int width, boolean required) {
		FormRadioButtonListElement formElement = new FormRadioButtonListElement();
		formElement.setInteractionStep(interactionStep);
		formElement.setSortOrder(sortOrder);

		formElement.setNextInteractionStep(nextInteractionStep);
		formElement.setName(name);
		formElement.setExportKey(exportKey);
		formElement.setWidth(width);
		formElement.setRequired(required);
		interactionElementDatabaseService.create(formElement);
		return formElement;
	}
	
	private FormNumberFieldElement createTestFormNumberFieldElement(InteractionStep interactionStep, Long sortOrder, InteractionStep nextInteractionStep, String name, String exportKey, long min, long max, long digits, boolean required) {
		FormNumberFieldElement formElement = new FormNumberFieldElement();
		formElement.setInteractionStep(interactionStep);
		formElement.setSortOrder(sortOrder);

		formElement.setNextInteractionStep(nextInteractionStep);
		formElement.setName(name);
		formElement.setExportKey(exportKey);
		formElement.setMin(min);
		formElement.setMax(max);
		formElement.setDigits(digits);
		formElement.setRequired(required);
		interactionElementDatabaseService.create(formElement);
		return formElement;
	}

	private FormSliderFieldElement createTestFormSliderFieldElement(InteractionStep interactionStep, Long sortOrder, InteractionStep nextInteractionStep, String name, String exportKey, long min, long max, boolean required) {
		FormSliderFieldElement formElement = new FormSliderFieldElement();
		formElement.setInteractionStep(interactionStep);
		formElement.setSortOrder(sortOrder);

		formElement.setNextInteractionStep(nextInteractionStep);
		formElement.setName(name);
		formElement.setExportKey(exportKey);
		formElement.setMin(min);
		formElement.setMax(max);
		formElement.setRequired(required);
		interactionElementDatabaseService.create(formElement);
		return formElement;
	}
	
	private void addRadioButton(FormRadioButtonListElement list, String name, String value) {
		FormRadioButtonValue button = new FormRadioButtonValue();
		button.setRadioButtonList(list);

		button.setName(name);
		button.setValue(value);
		formRadioButtonDatabaseService.create(button);
		list.getRadios().add(button);
		interactionElementDatabaseService.update(list);
	}

	private PermissionFunction createDemoPermissionFunction(PermissionFunctionEnum key, String name) {
		PermissionFunction function = new PermissionFunction();
		function.setKey(key.name());
		function.setName(name);
		permissionFunctionDatabaseService.create(function);
		return function;
	}

	private PermissionRole createDemoPermissionRole(String name, PermissionFunction... functions) {
		PermissionRole role = new PermissionRole();
		role.setName(name);
		role.getPermissionFunctions().addAll(Arrays.asList(functions));
		permissionRoleDatabaseService.create(role);
		return role;
	}

}