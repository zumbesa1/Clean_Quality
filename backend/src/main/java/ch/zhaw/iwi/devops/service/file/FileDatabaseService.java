package ch.zhaw.iwi.devops.service.file;

import java.util.List;

import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import ch.zhaw.iwi.devops.model.file.File;
import ch.zhaw.iwi.devops.model.file.File_;
import ch.zhaw.iwi.devops.service.AbstractCrudDatabaseService;
import ch.zhaw.iwi.devops.service.PathListEntry;

public class FileDatabaseService extends AbstractCrudDatabaseService<File, Long> {

	@Override
	public Class<File> getEntityClass() {
		return File.class;
	}

	@Override
	protected void orderBy(Root<File> root, List<Order> orderList) {
		orderList.add(getCriteriaBuilder().asc(root.get(File_.name)));
	}

	@Override
	public void createPathListEntry(File entity, PathListEntry<Long> entry) {
		entry.setKey(entity.getKey(), getKeyName());
		entry.setName(entity.getName());
	}

}
