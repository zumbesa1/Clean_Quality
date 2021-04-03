package ch.zhaw.iwi.devops.service.file;

import ch.zhaw.iwi.devops.model.file.File;
import ch.zhaw.iwi.devops.service.AbstractCrudRestServiceTest;
import ch.zhaw.iwi.devops.service.file.FileRestService;

public class FileRestServiceTest extends AbstractCrudRestServiceTest<File, Long> {

	@Override
	protected Class<?> getService() {
		return FileRestService.class;
	}

	@Override
	protected Class<File> getEntityClass() {
		return File.class;
	}

}
