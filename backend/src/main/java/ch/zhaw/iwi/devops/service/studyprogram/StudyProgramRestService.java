package ch.zhaw.iwi.devops.service.studyprogram;

import com.google.inject.Inject;
import com.google.inject.Injector;

import ch.zhaw.iwi.devops.model.user.User;
import ch.zhaw.iwi.devops.service.AbstractCrudRestService;

public class StudyProgramRestService extends AbstractCrudRestService<User, Long, StudyProgramDatabaseService> {

	@Inject
	public StudyProgramRestService(Injector injector) {
		super(injector, StudyProgramDatabaseService.class);
	}

}