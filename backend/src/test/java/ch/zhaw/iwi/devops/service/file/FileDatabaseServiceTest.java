package ch.zhaw.iwi.devops.service.file;

import ch.zhaw.iwi.devops.model.file.File;
import ch.zhaw.iwi.devops.service.AbstractCrudDatabaseServiceTest;
import ch.zhaw.iwi.devops.service.file.FileDatabaseService;

public class FileDatabaseServiceTest extends AbstractCrudDatabaseServiceTest<File, Long> {

	@Override
	protected Class<?> getService() {
		return FileDatabaseService.class;
	}

	@Override
	protected Class<File> getEntity() {
		return File.class;
	}
	

}