package ch.zhaw.iwi.devops.service.interactionstep;

import java.util.List;

import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import ch.zhaw.iwi.devops.model.interactionstep.InteractionStep;
import ch.zhaw.iwi.devops.model.interactionstep.InteractionStep_;
import ch.zhaw.iwi.devops.service.AbstractCrudDatabaseService;
import ch.zhaw.iwi.devops.service.PathListEntry;

public class InteractionStepDatabaseService extends AbstractCrudDatabaseService<InteractionStep, Long> {

	@Override
	public Class<InteractionStep> getEntityClass() {
		return InteractionStep.class;
	}

	@Override
	public void createPathListEntry(InteractionStep entity, PathListEntry<Long> entry) {
		entry.setKey(entity.getKey(), getKeyName());
		entry.setName(entity.getName());
		if (entity.getInteractionElements().size() == 1) {
			entry.getDetails().add("1 Element");
		} else {
			entry.getDetails().add(entity.getInteractionElements().size() + " Elemente");
		}
	}

	@Override
	protected void orderBy(Root<InteractionStep> root, List<Order> orderList) {
		orderList.add(getCriteriaBuilder().asc(root.get(InteractionStep_.name)));
	}

}