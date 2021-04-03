package ch.zhaw.iwi.devops.service.interactionelement.gui;

import java.util.ArrayList;

public class ButtonElementGuiModel extends InteractionElementGuiModel {

	public ButtonElementGuiModel() {
		super();
		setType("button");
	}
	
	private TranslatedText name = new TranslatedText();
	private ArrayList<String> details = new ArrayList<>();
	private TranslatedText tooltip = new TranslatedText();
	private String icon;
	private String color;
	private String page;
	private int width;
	private ElementKey key = new ElementKey();

	public TranslatedText getName() {
		return name;
	}
	
	public ArrayList<String> getDetails() {
		return details;
	}
		
	public TranslatedText getTooltip() {
		return tooltip;
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

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public ElementKey getKey() {
		return key;
	}

}
