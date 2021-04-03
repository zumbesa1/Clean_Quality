package ch.zhaw.iwi.devops.model.interactionelement.list;

import javax.persistence.Entity;

import ch.zhaw.iwi.devops.service.interactionelement.gui.ListElementGuiModel;
import ch.zhaw.iwi.devops.service.interactionstep.InteractionStepKey;
import ch.zhaw.iwi.devops.service.interactionstep.UiModelKeysEnum;

@Entity
public class ButtonListElement extends TileListElement {

	private String valueDescription;
	private String exportKey;

	public String getValueDescription() {
		return valueDescription;
	}
	
	public void setValueDescription(String valueDescription) {
		this.valueDescription = valueDescription;
	}
	
	@Override
	public String getAdministrationDisplayName() {
		return valueDescription;
	}
	
	@Override
	public String getExportKey() {
		return this.exportKey;
	}
	
	public void setExportKey(String exportKey) {
		this.exportKey = exportKey;
	}
		
	@Override
	public ListElementGuiModel toGuiModel(ListElementGuiModel model, InteractionStepKey currentKey) {
		ListElementGuiModel listElementGuiModel = super.toGuiModel(model, currentKey);
		listElementGuiModel.setPage(UiModelKeysEnum.nextInteractionStepPage.name());
		return listElementGuiModel;
	}
	
}
