package ch.zhaw.iwi.devops.service.questionnaireresponse.value;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import com.google.inject.Inject;

import ch.zhaw.iwi.devops.model.interactionelement.InteractionElement;
import ch.zhaw.iwi.devops.model.interactionelement.InteractionElement_;
import ch.zhaw.iwi.devops.model.questionnaireresponse.value.InteractionElementValue;
import ch.zhaw.iwi.devops.model.questionnaireresponse.value.InteractionElementValue_;
import ch.zhaw.iwi.devops.service.AbstractCrudDatabaseService;
import ch.zhaw.iwi.devops.service.PathListEntry;
import ch.zhaw.iwi.devops.service.questionnaireresponse.QuestionnaireResponseDatabaseService;

public class InteractionElementValueDatabaseService extends AbstractCrudDatabaseService<InteractionElementValue, Long> {

	@Inject
	QuestionnaireResponseDatabaseService orderDatabaseService;
	
	@Override
	public Class<InteractionElementValue> getEntityClass() {
		return InteractionElementValue.class;
	}

	@Override
	public void createPathListEntry(InteractionElementValue entity, PathListEntry<Long> entry) {
		entry.setKey(entity.getKey(), getKeyName());
		entry.setName(entity.getInteractionElement().getAdministrationDisplayName());
	}

	@Override
	protected void orderBy(Root<InteractionElementValue> root, List<Order> orderList) {
		orderList.add(getCriteriaBuilder().asc(root.get(InteractionElementValue_.key)));
	}
	
	@Override
	protected void afterCreate(InteractionElementValue entity) {
		super.afterCreate(entity);
		if (!entity.getQuestionnaireResponse().getValues().contains(entity)) {
			entity.getQuestionnaireResponse().getValues().add(entity);
			orderDatabaseService.create(entity.getQuestionnaireResponse());
		}
	}
	
	public class OrderInteractionElementFilter extends AbstractCrudDatabaseService<InteractionElementValue, Long>.AbstractListFilter {
		private Long questionnaireResponseKey;
		private Long interactionStepKey;

		public OrderInteractionElementFilter(Long questionnaireAnswerKey, Long interactionStepKey) {
			super();
			this.questionnaireResponseKey = questionnaireAnswerKey;
			this.interactionStepKey = interactionStepKey;
		}

		@Override
		public void appendFilter(CriteriaQuery<InteractionElementValue> criteriaQuery, Root<InteractionElementValue> root) {
			CriteriaBuilder cb = getCriteriaBuilder();
			Join<InteractionElementValue, InteractionElement> interactionElement = root.join(InteractionElementValue_.interactionElement);
			addPredicate(criteriaQuery, cb.equal(root.get(InteractionElementValue_.questionnaireResponse), questionnaireResponseKey));
			addPredicate(criteriaQuery, cb.equal(interactionElement.get(InteractionElement_.interactionStep), interactionStepKey));
		}
	}
	
}