package ch.zhaw.iwi.devops.service.interactionelement.form;

import ch.zhaw.iwi.devops.model.interactionelement.form.FormNumberFieldElement;
import ch.zhaw.iwi.devops.service.AbstractCrudDatabaseService;
import ch.zhaw.iwi.devops.service.PathListEntry;

public class FormNumberFieldElementDatabaseService extends AbstractCrudDatabaseService<FormNumberFieldElement, Long> {

	@Override
	public Class<FormNumberFieldElement> getEntityClass() {
		return FormNumberFieldElement.class;
	}

	@Override
	public void createPathListEntry(FormNumberFieldElement entity, PathListEntry<Long> entry) {
		entry.setKey(entity.getKey(), getKeyName());
		entry.setName(entity.getName());
	}

}