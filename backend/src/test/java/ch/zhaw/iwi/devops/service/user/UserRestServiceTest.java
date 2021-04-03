package ch.zhaw.iwi.devops.service.user;

import ch.zhaw.iwi.devops.model.user.User;
import ch.zhaw.iwi.devops.service.AbstractCrudRestServiceTest;
import ch.zhaw.iwi.devops.service.user.UserRestService;

public class UserRestServiceTest extends AbstractCrudRestServiceTest<User, Long> {

	@Override
	protected Class<?> getService() {
		return UserRestService.class;
	}

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}
	
	@Override
	protected void beforeCreate(User entity) {
		super.beforeCreate(entity);
		entity.setPassword("1234qwer.");
		entity.setRepeatPassword("1234qwer.");
	}

}
