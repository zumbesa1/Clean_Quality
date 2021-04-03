package ch.zhaw.iwi.devops.model.interactionelement.button;

import java.util.ArrayList;

import javax.persistence.Entity;

import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;

import ch.zhaw.iwi.devops.model.interactionelement.InteractionElement;
import ch.zhaw.iwi.devops.service.interactionelement.gui.ButtonElementGuiModel;
import ch.zhaw.iwi.devops.service.interactionstep.InteractionStepKey;
import ch.zhaw.iwi.devops.service.interactionstep.UiModelKeysEnum;

@Entity
public class ButtonElement extends InteractionElement<ButtonElementGuiModel> {

	private String name;
	private ArrayList<String> details = new ArrayList<>();
	private String exportKey;
	private String valueDescription;
	private String tooltip;
	private String icon;
	private String color;
	private String value;
	private int width = 2;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<String> getDetails() {
		return details;
	}
	
	@Override
	public String getAdministrationDisplayName() {
		if (Strings.isNullOrEmpty(valueDescription)) {
			return name;
		}
		return valueDescription;
	}
	
	@Override
	public String getExportKey() {
		return this.exportKey;
	}
	
	public void setExportKey(String exportKey) {
		this.exportKey = exportKey;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public String getValueDescription() {
		return valueDescription;
	}
	
	public void setValueDescription(String valueDescription) {
		this.valueDescription = valueDescription;
	}
	
	@Override
	public ButtonElementGuiModel toGuiModel(ButtonElementGuiModel guiModel, InteractionStepKey currentKey) {
		guiModel = new ButtonElementGuiModel();
		guiModel.setColor(color);
		guiModel.setIcon(icon);
		guiModel.getName().setAll(name);
		guiModel.getDetails().addAll(getDetails());
		guiModel.getTooltip().setAll(MoreObjects.firstNonNull(tooltip, name));
		guiModel.setPage(UiModelKeysEnum.nextInteractionStepPage.name());
		guiModel.setWidth(width);
		currentKey.setNextInteractionStepKey(getNextInteractionStep() != null ? getNextInteractionStep().getKey() : null);
		currentKey.setInteractionElementKey(getKey());
		currentKey.setInteractionElementDisplayValue(MoreObjects.firstNonNull(value, "Ausgewählt"));
		guiModel.getKey().setKey(currentKey.toEncodedKey());
		guiModel.getKey().setName(InteractionStepKey.NAME);
		return super.toGuiModel(guiModel, currentKey);
	}

}
