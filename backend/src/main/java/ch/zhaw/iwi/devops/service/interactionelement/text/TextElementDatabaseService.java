package ch.zhaw.iwi.devops.service.interactionelement.text;

import ch.zhaw.iwi.devops.model.interactionelement.text.TextElement;
import ch.zhaw.iwi.devops.service.AbstractCrudDatabaseService;
import ch.zhaw.iwi.devops.service.PathListEntry;

public class TextElementDatabaseService extends AbstractCrudDatabaseService<TextElement, Long> {

	@Override
	public Class<TextElement> getEntityClass() {
		return TextElement.class;
	}

	@Override
	public void createPathListEntry(TextElement entity, PathListEntry<Long> entry) {
		entry.setKey(entity.getKey(), getKeyName());
		entry.setName(entity.getAdministrationDisplayName());
	}

}