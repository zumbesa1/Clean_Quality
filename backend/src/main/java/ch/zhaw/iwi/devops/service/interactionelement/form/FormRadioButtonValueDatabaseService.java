package ch.zhaw.iwi.devops.service.interactionelement.form;

import ch.zhaw.iwi.devops.model.interactionelement.form.FormRadioButtonValue;
import ch.zhaw.iwi.devops.service.AbstractCrudDatabaseService;
import ch.zhaw.iwi.devops.service.PathListEntry;

public class FormRadioButtonValueDatabaseService extends AbstractCrudDatabaseService<FormRadioButtonValue, Long> {

	@Override
	public Class<FormRadioButtonValue> getEntityClass() {
		return FormRadioButtonValue.class;
	}

	@Override
	public void createPathListEntry(FormRadioButtonValue entity, PathListEntry<Long> entry) {
		entry.setKey(entity.getKey(), getKeyName());
		entry.setName(entity.getName());
	}

}