package ch.zhaw.iwi.devops.service.questionnaireresponse;

import static spark.Spark.get;

import com.google.common.primitives.Longs;
import com.google.inject.Inject;
import com.google.inject.Injector;

import ch.zhaw.iwi.devops.model.questionnaireresponse.QuestionnaireResponse;
import ch.zhaw.iwi.devops.service.AbstractCrudRestService;
import ch.zhaw.iwi.devops.service.user.UserDatabaseService;

public class QuestionnaireResponseRestService extends AbstractCrudRestService<QuestionnaireResponse, Long, QuestionnaireResponseDatabaseService> {

	@Inject
	public QuestionnaireResponseRestService(Injector injector) {
		super(injector, QuestionnaireResponseDatabaseService.class);
	}

	@Inject
	UserDatabaseService userDatabaseService;

	@Override
	protected void initGet() {
		super.initGet();

		get("services/questionnaireResponse/:questionnaireResponseKey/summary", (req, res) -> {
			Long questionnaireResponseKey = Longs.tryParse(req.params("questionnaireResponseKey"));
			QuestionnaireResponse questionnaireResponse = getCrudDatabaseService().read(questionnaireResponseKey);
			return getCrudDatabaseService().getSummary(questionnaireResponse);
		}, getJsonTransformer());
	}
	
}
