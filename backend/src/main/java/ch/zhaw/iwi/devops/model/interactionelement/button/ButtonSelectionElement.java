package ch.zhaw.iwi.devops.model.interactionelement.button;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;

import ch.zhaw.iwi.devops.model.interactionelement.InteractionElement;
import ch.zhaw.iwi.devops.service.interactionelement.gui.ButtonSelectionElementGuiModel;
import ch.zhaw.iwi.devops.service.interactionstep.InteractionStepKey;

@Entity
public class ButtonSelectionElement extends InteractionElement<ButtonSelectionElementGuiModel> {

	private String name;
	private String exportKey;
	private String valueDescription;
	private String tooltip;
	private String icon;
	private String color;
	private String value;
	private int width = 2;

	@OneToMany(mappedBy = "buttonSelectionElement")
	private Set<ButtonSelectionElementValue> values = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Set<ButtonSelectionElementValue> getValues() {
		return values;
	}

	public void setValues(Set<ButtonSelectionElementValue> values) {
		this.values = values;
	}

	@Override
	public ButtonSelectionElementGuiModel toGuiModel(ButtonSelectionElementGuiModel guiModel, InteractionStepKey currentKey) {
		guiModel = new ButtonSelectionElementGuiModel();
		guiModel.setUrl("/questionnaireResponse/" + currentKey.getQuestionnaireResponseKey() + "/buttonSelectionElemement/" + getKey() + "/buttonSelectionElementValue");
		guiModel.setColor(color);
		guiModel.setIcon(icon);
		guiModel.getName().setAll(name);
		guiModel.getTooltip().setAll(MoreObjects.firstNonNull(tooltip, name));
		guiModel.setWidth(width);
		currentKey.setNextInteractionStepKey(getNextInteractionStep() != null ? getNextInteractionStep().getKey() : null);
		currentKey.setInteractionElementKey(getKey());
		currentKey.setInteractionElementDisplayValue(MoreObjects.firstNonNull(value, "Ausgewählt"));
		guiModel.getKey().setKey(currentKey.toEncodedKey());
		guiModel.getKey().setName(InteractionStepKey.NAME);
		return super.toGuiModel(guiModel, currentKey);
	}

}
