package ch.zhaw.iwi.devops.service.interactionelement.form;

import ch.zhaw.iwi.devops.model.interactionelement.form.FormDateFieldElement;
import ch.zhaw.iwi.devops.service.AbstractCrudDatabaseService;
import ch.zhaw.iwi.devops.service.PathListEntry;

public class FormDateFieldElementDatabaseService extends AbstractCrudDatabaseService<FormDateFieldElement, Long> {

	@Override
	public Class<FormDateFieldElement> getEntityClass() {
		return FormDateFieldElement.class;
	}

	@Override
	public void createPathListEntry(FormDateFieldElement entity, PathListEntry<Long> entry) {
		entry.setKey(entity.getKey(), getKeyName());
		entry.setName(entity.getName());
	}

}