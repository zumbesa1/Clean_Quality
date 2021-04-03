package ch.zhaw.iwi.devops.service.questionnaireresponse.value;

import static spark.Spark.get;

import com.google.common.primitives.Longs;
import com.google.inject.Inject;
import com.google.inject.Injector;

import ch.zhaw.iwi.devops.model.questionnaireresponse.value.InteractionElementValue;
import ch.zhaw.iwi.devops.service.AbstractCrudRestService;
import ch.zhaw.iwi.devops.service.questionnaireresponse.QuestionnaireResponseDatabaseService;

public class InteractionElementValueRestService extends AbstractCrudRestService<InteractionElementValue, Long, InteractionElementValueDatabaseService> {

	@Inject
	public InteractionElementValueRestService(Injector injector) {
		super(injector, InteractionElementValueDatabaseService.class);
	}

	@Inject
	QuestionnaireResponseDatabaseService orderDatabaseService;

	@Override
	protected void initGet() {
		super.initGet();

		get("services/order/:orderKey/interactionElementValue", (req, res) -> {
			Long orderKey = Longs.tryParse(req.params("orderKey"));
			return getCrudDatabaseService().createPathList(orderDatabaseService.read(orderKey).getValues());
		}, getJsonTransformer());
	}

}