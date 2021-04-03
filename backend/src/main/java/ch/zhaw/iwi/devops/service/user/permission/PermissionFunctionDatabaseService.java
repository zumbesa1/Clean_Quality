package ch.zhaw.iwi.devops.service.user.permission;

import ch.zhaw.iwi.devops.model.user.permission.PermissionFunction;
import ch.zhaw.iwi.devops.service.AbstractCrudDatabaseService;
import ch.zhaw.iwi.devops.service.PathListEntry;

public class PermissionFunctionDatabaseService extends AbstractCrudDatabaseService<PermissionFunction, String> {

	@Override
	public Class<PermissionFunction> getEntityClass() {
		return PermissionFunction.class;
	}

	@Override
	public void createPathListEntry(PermissionFunction entity, PathListEntry<String> entry) {
		entry.setKey(entity.getKey(), getKeyName());
		entry.setName(entity.getName());
	}

}