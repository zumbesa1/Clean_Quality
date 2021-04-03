package ch.zhaw.iwi.devops.service.interactionelement.button;

import ch.zhaw.iwi.devops.model.interactionelement.button.ButtonSelectionElementValue;
import ch.zhaw.iwi.devops.service.AbstractCrudDatabaseService;
import ch.zhaw.iwi.devops.service.PathListEntry;

public class ButtonSelectionElementValueDatabaseService extends AbstractCrudDatabaseService<ButtonSelectionElementValue, Long> {

	@Override
	public Class<ButtonSelectionElementValue> getEntityClass() {
		return ButtonSelectionElementValue.class;
	}

	@Override
	public void createPathListEntry(ButtonSelectionElementValue entity, PathListEntry<Long> entry) {
		entry.setKey(entity.getKey(), getKeyName());
		entry.setName(entity.getValue());
		entry.getDetails().addAll(entity.getDetails());
	}

}