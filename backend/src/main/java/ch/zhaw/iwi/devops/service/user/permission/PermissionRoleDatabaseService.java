package ch.zhaw.iwi.devops.service.user.permission;

import ch.zhaw.iwi.devops.model.user.permission.PermissionRole;
import ch.zhaw.iwi.devops.service.AbstractCrudDatabaseService;
import ch.zhaw.iwi.devops.service.PathListEntry;

public class PermissionRoleDatabaseService extends AbstractCrudDatabaseService<PermissionRole, Long> {

	@Override
	public Class<PermissionRole> getEntityClass() {
		return PermissionRole.class;
	}
	
	@Override
	public void createPathListEntry(PermissionRole entity, PathListEntry<Long> entry) {
		entry.setKey(entity.getKey(), getKeyName());
		entry.setName(entity.getName());
		if (entity.getPermissionFunctions().size() == 1) {
			entry.getDetails().add(entity.getPermissionFunctions().size() + " Function");
		} else {
			entry.getDetails().add(entity.getPermissionFunctions().size() + " Functions");
		}
	}

}