package ch.zhaw.iwi.devops.service.interactionelement.gui;

import java.util.List;

import ch.zhaw.iwi.devops.service.questionnaireresponse.QuestionnaireResponseDatabaseService.OrderSummary;

public class OrderSummaryComponentGuiModel extends InteractionElementGuiModel {

	private List<OrderSummary> summary;
	
	public OrderSummaryComponentGuiModel(List<OrderSummary> summary) {
		super();
		setType("OrderSummaryComponent");
		this.summary = summary;
	}
	
	public List<OrderSummary> getSummary() {
		return summary;
	}
	
	public void setSummary(List<OrderSummary> summary) {
		this.summary = summary;
	}

}
