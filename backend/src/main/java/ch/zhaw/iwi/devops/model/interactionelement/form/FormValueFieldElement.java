package ch.zhaw.iwi.devops.model.interactionelement.form;

import javax.persistence.Entity;

import ch.zhaw.iwi.devops.model.interactionelement.InteractionElement;
import ch.zhaw.iwi.devops.service.interactionelement.gui.FormValueFieldElementGuiModel;
import ch.zhaw.iwi.devops.service.interactionstep.InteractionStepKey;

@Entity
public class FormValueFieldElement<T extends FormValueFieldElementGuiModel> extends InteractionElement<T> {

	private String name;
	private String exportKey;
	private int width = 2;
	private boolean visible = true;
	private boolean labelVisible = true;
	private boolean required = true;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String getAdministrationDisplayName() {
		return getName();
	}
	
	@Override
	public String getExportKey() {
		return this.exportKey;
	}
	
	public void setExportKey(String exportKey) {
		this.exportKey = exportKey;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isLabelVisible() {
		return labelVisible;
	}

	public void setLabelVisible(boolean labelVisible) {
		this.labelVisible = labelVisible;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public T toGuiModel(T guiModel, InteractionStepKey currentKey) {
		guiModel.setId("field" + getSortOrder());
		guiModel.setName(name);
		guiModel.setVisible(visible);
		guiModel.setLabelVisible(labelVisible);
		guiModel.setRequired(required);
		guiModel.setWidth(width);
		return super.toGuiModel(guiModel, currentKey);
	}

}
