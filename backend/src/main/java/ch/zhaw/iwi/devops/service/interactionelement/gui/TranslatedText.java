package ch.zhaw.iwi.devops.service.interactionelement.gui;

public class TranslatedText {

	private String defaultText; // TODO should be default
	private String de;

	public String getDefaultText() {
		return defaultText;
	}

	public void setDefaultText(String defaultText) {
		this.defaultText = defaultText;
	}

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}
	
	public void setAll(String text) {
		this.de = text;
		this.defaultText = text;
	}

}
