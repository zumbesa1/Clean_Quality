package ch.zhaw.iwi.devops.service.questionnaire;

import static spark.Spark.get;

import com.google.inject.Inject;
import com.google.inject.Injector;

import ch.zhaw.iwi.devops.model.questionnaire.Questionnaire;
import ch.zhaw.iwi.devops.service.AbstractCrudRestService;

public class QuestionnaireRestService extends AbstractCrudRestService<Questionnaire, Long, QuestionnaireDatabaseService> {

	@Inject
	public QuestionnaireRestService(Injector injector) {
		super(injector, QuestionnaireDatabaseService.class);
	}

	@Override
	protected void initGet() {
		super.initGet();

		get("services/questionnaire/patientRequired/true", (req, res) -> {
			return getCrudDatabaseService().createPathList(getCrudDatabaseService().list(
					getCrudDatabaseService().new PatientRequiredFilter(true), 
					getCrudDatabaseService().new ActiveFilter(true)));
		}, getJsonTransformer());

		get("services/questionnaire/patientRequired/false", (req, res) -> {
			return getCrudDatabaseService().createPathList(getCrudDatabaseService().list(
					getCrudDatabaseService().new PatientRequiredFilter(false), 
					getCrudDatabaseService().new ActiveFilter(true)));
		}, getJsonTransformer());
	}

}