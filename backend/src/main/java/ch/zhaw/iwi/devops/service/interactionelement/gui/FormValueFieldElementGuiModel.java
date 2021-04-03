package ch.zhaw.iwi.devops.service.interactionelement.gui;

public class FormValueFieldElementGuiModel extends InteractionElementGuiModel {

	private String id;
	private String form;
	private String name;
	private boolean visible;
	private boolean labelVisible;
	private boolean required;
	private int width;

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

}
