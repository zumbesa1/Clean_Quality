package ch.zhaw.iwi.devops.service.interactionelement.button;

import static spark.Spark.get;

import java.util.ArrayList;
import java.util.List;

import com.google.common.primitives.Longs;
import com.google.inject.Inject;
import com.google.inject.Injector;

import ch.zhaw.iwi.devops.model.interactionelement.button.ButtonSelectionElement;
import ch.zhaw.iwi.devops.model.interactionelement.button.ButtonSelectionElementValue;
import ch.zhaw.iwi.devops.model.questionnaireresponse.QuestionnaireResponse;
import ch.zhaw.iwi.devops.service.AbstractCrudRestService;
import ch.zhaw.iwi.devops.service.PathListEntry;
import ch.zhaw.iwi.devops.service.questionnaireresponse.QuestionnaireResponseDatabaseService;

public class ButtonSelectionElementRestService extends AbstractCrudRestService<ButtonSelectionElement, Long, ButtonSelectionElementDatabaseService> {

	@Inject
	public ButtonSelectionElementRestService(Injector injector) {
		super(injector, ButtonSelectionElementDatabaseService.class);
	}

	@Inject
	ButtonSelectionElementValueDatabaseService buttonSelectionElementValueDatabaseService;

	@Inject
	QuestionnaireResponseDatabaseService questionnaireResponseDatabaseService;
	
	@Override
	protected void initGet() {
		super.initGet();

		// Value list
		get("services/questionnaireResponse/:questionnaireResponseKey/buttonSelectionElemement/:buttonSelectionElementKey/buttonSelectionElementValue", (req, res) -> {
			Long questionnaireResponseKey = Longs.tryParse(req.params("questionnaireResponseKey"));
			Long buttonSelectionElementKey = Longs.tryParse(req.params("buttonSelectionElementKey"));
			ButtonSelectionElement buttonSelectionElement = getCrudDatabaseService().read(buttonSelectionElementKey);
			QuestionnaireResponse questionnaireResponse = questionnaireResponseDatabaseService.read(questionnaireResponseKey);
			
			List<PathListEntry<Long>> result = new ArrayList<>();
			result.addAll(buttonSelectionElementValueDatabaseService.createPathList(buttonSelectionElement.getValues()));
			for (PathListEntry<Long> entry : result) {
				ButtonSelectionElementValue value = buttonSelectionElementValueDatabaseService.read(entry.getKey().getKey());
				if (questionnaireResponse.getSelectedButtonSelectionElementValues().contains(value)) {
					entry.setColor("devops-green");
				}
				entry.setUrl("/questionnaireResponse/" + questionnaireResponseKey + "/buttonSelectionElemement/" + buttonSelectionElementKey + "/buttonSelectionElementValue/" + entry.getKey().getKey() + "/toggle");
			}
			return result;
		}, getJsonTransformer());

		// Toggle
		get("services/questionnaireResponse/:questionnaireResponseKey/buttonSelectionElemement/:buttonSelectionElementKey/buttonSelectionElementValue/:buttonSelectionElementValueKey/toggle", (req, res) -> {
			Long questionnaireResponseKey = Longs.tryParse(req.params("questionnaireResponseKey"));
			Long buttonSelectionElementValueKey = Longs.tryParse(req.params("buttonSelectionElementValueKey"));
			QuestionnaireResponse questionnaireResponse = questionnaireResponseDatabaseService.read(questionnaireResponseKey);
			
			ButtonSelectionElementValue buttonSelectionValue = buttonSelectionElementValueDatabaseService.read(buttonSelectionElementValueKey);
			if (questionnaireResponse.getSelectedButtonSelectionElementValues().contains(buttonSelectionValue)) {
				questionnaireResponse.getSelectedButtonSelectionElementValues().remove(buttonSelectionValue);
			} else {
				questionnaireResponse.getSelectedButtonSelectionElementValues().add(buttonSelectionValue);
			}
			return questionnaireResponseDatabaseService.update(questionnaireResponse);
		}, getJsonTransformer());
	}

}
