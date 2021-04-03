package ch.zhaw.iwi.devops.service.sport;

import java.util.List;

import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import ch.zhaw.iwi.devops.model.sport.Sport;
import ch.zhaw.iwi.devops.model.sport.Sport_;
import ch.zhaw.iwi.devops.service.AbstractCrudDatabaseService;
import ch.zhaw.iwi.devops.service.PathListEntry;

public class SportDatabaseService extends AbstractCrudDatabaseService<Sport, Long> {

	@Override
	public Class<Sport> getEntityClass() {
		return Sport.class;
	}

	@Override
	protected void orderBy(Root<Sport> root, List<Order> orderList) {
		orderList.add(getCriteriaBuilder().asc(root.get(Sport_.name)));
	}

	@Override
	public void createPathListEntry(Sport entity, PathListEntry<Long> entry) {
		entry.setKey(entity.getKey(), getKeyName());
		entry.setName(entity.getName());
		entry.getDetails().add(entity.getDescription());
		entry.setIcon(entity.getIcon());
	}

}
