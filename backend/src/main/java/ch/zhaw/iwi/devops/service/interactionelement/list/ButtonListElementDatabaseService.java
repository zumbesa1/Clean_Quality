package ch.zhaw.iwi.devops.service.interactionelement.list;

import ch.zhaw.iwi.devops.model.interactionelement.list.ButtonListElement;
import ch.zhaw.iwi.devops.service.AbstractCrudDatabaseService;
import ch.zhaw.iwi.devops.service.PathListEntry;

public class ButtonListElementDatabaseService extends AbstractCrudDatabaseService<ButtonListElement, Long> {

	@Override
	public Class<ButtonListElement> getEntityClass() {
		return ButtonListElement.class;
	}

	@Override
	public void createPathListEntry(ButtonListElement entity, PathListEntry<Long> entry) {
		entry.setKey(entity.getKey(), getKeyName());
		entry.setName(entity.getAdministrationDisplayName());
	}

}