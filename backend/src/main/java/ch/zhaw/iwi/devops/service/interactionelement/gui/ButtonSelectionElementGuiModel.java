package ch.zhaw.iwi.devops.service.interactionelement.gui;

public class ButtonSelectionElementGuiModel extends InteractionElementGuiModel {

	public ButtonSelectionElementGuiModel() {
		super();
		setType("list");
	}
	
	private String url;
	private TranslatedText name = new TranslatedText();
	private TranslatedText tooltip = new TranslatedText();
	private String icon;
	private String color;
	private String page;
	private int width;
	private ElementKey key = new ElementKey();

	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public TranslatedText getName() {
		return name;
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
