package ch.zhaw.iwi.devops.service.user;

import ch.zhaw.iwi.devops.model.user.User;
import ch.zhaw.iwi.devops.service.AbstractCrudDatabaseServiceTest;
import ch.zhaw.iwi.devops.service.user.UserDatabaseService;

public class UserDatabaseServiceTest extends AbstractCrudDatabaseServiceTest<User, Long> {
	
	@Override
	protected Class<?> getService() {
		return UserDatabaseService.class;
	}

	@Override
	protected Class<User> getEntity() {
		return User.class;
	}
	
	@Override
	protected void beforeCreate(User entity) {
		super.beforeCreate(entity);
		entity.setPassword("1234qwer.");
		entity.setRepeatPassword("1234qwer.");
	}
	

}