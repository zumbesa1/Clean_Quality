package ch.zhaw.iwi.devops.model.interactionelement.list;

import javax.persistence.Entity;

import ch.zhaw.iwi.devops.model.interactionelement.InteractionElement;
import ch.zhaw.iwi.devops.service.interactionelement.gui.ListElementGuiModel;
import ch.zhaw.iwi.devops.service.interactionstep.InteractionStepKey;

@Entity
public class TileListElement extends InteractionElement<ListElementGuiModel> {

	private boolean isSearchVisible;
	private boolean isSearchRequired;

	public boolean isSearchVisible() {
		return isSearchVisible;
	}

	public void setSearchVisible(boolean isSearchVisible) {
		this.isSearchVisible = isSearchVisible;
	}

	public boolean isSearchRequired() {
		return isSearchRequired;
	}

	public void setSearchRequired(boolean isSearchRequired) {
		this.isSearchRequired = isSearchRequired;
	}
	
	@Override
	public String getAdministrationDisplayName() {
		return new String();
	}
	
	@Override
	public ListElementGuiModel toGuiModel(ListElementGuiModel guiModel, InteractionStepKey currentKey) {
		Long nextInteractionStepKey = null;
		if (getNextInteractionStep() != null) {
			nextInteractionStepKey = getNextInteractionStep().getKey();
		}
		guiModel = new ListElementGuiModel();
		guiModel.setType("list");
		currentKey.setNextInteractionStepKey(nextInteractionStepKey);
		currentKey.setInteractionElementKey(getKey());
		currentKey.setInteractionElementDisplayValue(null);
		// guiModel.setUrl("/nextInteractionStep/" + currentKey.toEncodedKey() + customServiceUrl.getUrl());
		guiModel.setColor("red"); // default
		guiModel.setIcon("fa-user"); // default
		guiModel.setSearch(isSearchRequired ? true : isSearchVisible);
		guiModel.setSearchRequired(isSearchRequired);
		guiModel.setSearchRequest(isSearchRequired ? true : false);
		guiModel.setLimit(isSearchRequired ? 20 : 0);
		return super.toGuiModel(guiModel, currentKey);
	}

}
