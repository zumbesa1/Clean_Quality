package ch.zhaw.iwi.devops.service.interactionelement.button;

import ch.zhaw.iwi.devops.model.interactionelement.button.BackButtonElement;
import ch.zhaw.iwi.devops.service.AbstractCrudDatabaseService;
import ch.zhaw.iwi.devops.service.PathListEntry;

public class BackButtonElementDatabaseService extends AbstractCrudDatabaseService<BackButtonElement, Long> {

	@Override
	public Class<BackButtonElement> getEntityClass() {
		return BackButtonElement.class;
	}

	@Override
	public void createPathListEntry(BackButtonElement entity, PathListEntry<Long> entry) {
		entry.setKey(entity.getKey(), getKeyName());
		entry.setName(entity.getAdministrationDisplayName());
	}

}