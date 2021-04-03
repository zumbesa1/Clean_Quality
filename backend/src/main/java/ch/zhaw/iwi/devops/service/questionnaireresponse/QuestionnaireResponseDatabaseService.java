package ch.zhaw.iwi.devops.service.questionnaireresponse;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Root;

import com.google.inject.Inject;

import ch.zhaw.iwi.devops.model.file.File;
import ch.zhaw.iwi.devops.model.interactionelement.button.ButtonSelectionElementValue;
import ch.zhaw.iwi.devops.model.questionnaireresponse.QuestionnaireResponse;
import ch.zhaw.iwi.devops.model.questionnaireresponse.QuestionnaireResponse_;
import ch.zhaw.iwi.devops.model.questionnaireresponse.value.InteractionElementDoubleValue;
import ch.zhaw.iwi.devops.model.questionnaireresponse.value.InteractionElementFileValue;
import ch.zhaw.iwi.devops.model.questionnaireresponse.value.InteractionElementLongValue;
import ch.zhaw.iwi.devops.model.questionnaireresponse.value.InteractionElementTextValue;
import ch.zhaw.iwi.devops.model.questionnaireresponse.value.InteractionElementValue;
import ch.zhaw.iwi.devops.service.AbstractCrudDatabaseService;
import ch.zhaw.iwi.devops.service.DateUtility;
import ch.zhaw.iwi.devops.service.PathListEntry;
import ch.zhaw.iwi.devops.service.questionnaireresponse.value.InteractionElementValueDatabaseService;

public class QuestionnaireResponseDatabaseService extends AbstractCrudDatabaseService<QuestionnaireResponse, Long> {

	@Inject
	InteractionElementValueDatabaseService interactionElementValueDatabaseService;

	@Override
	public Class<QuestionnaireResponse> getEntityClass() {
		return QuestionnaireResponse.class;
	}

	@Override
	public void createPathListEntry(QuestionnaireResponse entity, PathListEntry<Long> entry) {
		entry.setKey(entity.getKey(), getKeyName());
		if (entity.getPatient() != null) {
			entry.setName(entity.getPatient().getName());
		}
		entry.getDetails().add(entity.getQuestionnaire().getName());
		entry.setTooltip(DateUtility.formatDate(entity.getDate()));
	}

	@Override
	protected void afterRead(QuestionnaireResponse entity) {
		super.afterRead(entity);
	}

	@Override
	protected void beforeDelete(QuestionnaireResponse entity) {
		super.beforeDelete(entity);

		for (InteractionElementValue value : entity.getValues()) {
			interactionElementValueDatabaseService.delete(value);
		}
	}

	@Override
	protected void orderBy(Root<QuestionnaireResponse> root, List<javax.persistence.criteria.Order> orderList) {
		orderList.add(getCriteriaBuilder().asc(root.get(QuestionnaireResponse_.date)));
	}

	public List<OrderSummary> getSummary(QuestionnaireResponse questionnaireResponse) {
		List<OrderSummary> result = new ArrayList<>();

		result.add(new OrderSummary("Nummer", String.valueOf(questionnaireResponse.getKey())));
		result.add(new OrderSummary("Datum", questionnaireResponse.getDate() == null ? "Kein Datum" : DateUtility.formatDate(questionnaireResponse.getDate())));
		result.add(new OrderSummary("", " "));

		if (questionnaireResponse.getQuestionnaire() != null && questionnaireResponse.getQuestionnaire().getName().equalsIgnoreCase("Fragebogen Typisierung")) {
			double weight = 0;
			double size = 0;
			double bmi = 0;
			for (InteractionElementValue value : questionnaireResponse.getValues()) {
				if (value instanceof InteractionElementLongValue) {
					if (value.getInteractionElement().getAdministrationDisplayName().equalsIgnoreCase("Gewicht")) {
						weight = ((InteractionElementLongValue) value).getLongValue();
					} else if (value.getInteractionElement().getAdministrationDisplayName().equalsIgnoreCase("Körpergrösse")) {
						size = ((InteractionElementLongValue) value).getLongValue();
					}
				}
			}
			if (size > 0) {
				bmi = weight / (size / 100 * size / 100);
			}
			result.add(new OrderSummary("BMI", String.format("%.2f", bmi)));
		}

		for (InteractionElementValue value : questionnaireResponse.getValues()) {
			if (!value.getInteractionElement().getAdministrationDisplayName().equalsIgnoreCase("Weiter") &&
					!value.getInteractionElement().getAdministrationDisplayName().equalsIgnoreCase("Weitere Medikamente")) {
				result.add(new OrderSummary(value.getInteractionElement().getAdministrationDisplayName(), value));
			}
		}
		for (ButtonSelectionElementValue value : questionnaireResponse.getSelectedButtonSelectionElementValues()) {
			result.add(new OrderSummary(value.getName(), value.getValue()));
		}

		return result;
	}

	public class OrderSummary {

		private String key;
		private String value;
		private List<OrderSummaryFile> files = new ArrayList<>();

		public OrderSummary(String key, String value) {
			super();
			this.key = key;
			this.value = value;
		}

		public OrderSummary(String key, InteractionElementValue value) {
			super();
			this.key = key;
			if (value instanceof InteractionElementTextValue) {
				this.value = ((InteractionElementTextValue) value).getDisplayValue();
			} else if (value instanceof InteractionElementDoubleValue) {
				this.value = ((InteractionElementDoubleValue) value).getDisplayValue();
			} else if (value instanceof InteractionElementLongValue) {
				this.value = ((InteractionElementLongValue) value).getDisplayValue();
			} else if (value instanceof InteractionElementFileValue) {
				for (File file : ((InteractionElementFileValue) value).getFiles()) {
					OrderSummaryFile orderSummaryFile = new OrderSummaryFile(file.getKey(), file.getName());
					this.files.add(orderSummaryFile);
				}
			} else {
				throw new UnsupportedOperationException("Unsupported Interaction Element Value Type: " + value.getClass().getName());
			}
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

	}

	private class OrderSummaryFile {

		public OrderSummaryFile(Long key, String name) {
			super();
			this.key = key;
			this.name = name;
		}

		@SuppressWarnings("unused")
		private Long key;

		@SuppressWarnings("unused")
		private String name;

	}

}