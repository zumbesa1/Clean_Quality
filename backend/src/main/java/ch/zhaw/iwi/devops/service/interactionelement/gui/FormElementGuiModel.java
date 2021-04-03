package ch.zhaw.iwi.devops.service.interactionelement.gui;

import ch.zhaw.iwi.devops.service.PathListEntry;

public class FormElementGuiModel extends InteractionElementGuiModel {

	private PathListEntry<String>.Key key;
	private String form;
	private String url;
	private String page;

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getPage() {
		return page;
	}
	
	public void setPage(String page) {
		this.page = page;
	}
	
	public void setKey(PathListEntry<String>.Key key) {
		this.key = key;
	}
	
	public PathListEntry<String>.Key getKey() {
		return key;
	}

}
