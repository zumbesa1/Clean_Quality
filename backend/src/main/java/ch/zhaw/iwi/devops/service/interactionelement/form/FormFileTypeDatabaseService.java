package ch.zhaw.iwi.devops.service.interactionelement.form;

import ch.zhaw.iwi.devops.model.interactionelement.form.FormFileType;
import ch.zhaw.iwi.devops.service.AbstractCrudDatabaseService;
import ch.zhaw.iwi.devops.service.PathListEntry;

public class FormFileTypeDatabaseService extends AbstractCrudDatabaseService<FormFileType, Long> {

	@Override
	public Class<FormFileType> getEntityClass() {
		return FormFileType.class;
	}

	@Override
	public void createPathListEntry(FormFileType entity, PathListEntry<Long> entry) {
		entry.setKey(entity.getKey(), getKeyName());
		entry.setName(entity.getFileType());
	}

}