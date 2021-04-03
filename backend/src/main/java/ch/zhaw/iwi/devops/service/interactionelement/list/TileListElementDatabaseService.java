package ch.zhaw.iwi.devops.service.interactionelement.list;

import ch.zhaw.iwi.devops.model.interactionelement.list.TileListElement;
import ch.zhaw.iwi.devops.service.AbstractCrudDatabaseService;
import ch.zhaw.iwi.devops.service.PathListEntry;

public class TileListElementDatabaseService extends AbstractCrudDatabaseService<TileListElement, Long> {

	@Override
	public Class<TileListElement> getEntityClass() {
		return TileListElement.class;
	}

	@Override
	public void createPathListEntry(TileListElement entity, PathListEntry<Long> entry) {
		entry.setKey(entity.getKey(), getKeyName());
		entry.setName(entity.getAdministrationDisplayName());
	}

}