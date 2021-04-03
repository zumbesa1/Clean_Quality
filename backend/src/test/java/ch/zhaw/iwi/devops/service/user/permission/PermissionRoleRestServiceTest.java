package ch.zhaw.iwi.devops.service.user.permission;

import ch.zhaw.iwi.devops.model.user.permission.PermissionRole;
import ch.zhaw.iwi.devops.service.AbstractCrudRestServiceTest;
import ch.zhaw.iwi.devops.service.user.permission.PermissionRoleRestService;

public class PermissionRoleRestServiceTest extends AbstractCrudRestServiceTest<PermissionRole, Long> {

	@Override
	protected Class<?> getService() {
		return PermissionRoleRestService.class;
	}

	@Override
	protected Class<PermissionRole> getEntityClass() {
		return PermissionRole.class;
	}

}
