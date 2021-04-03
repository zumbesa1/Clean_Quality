package ch.zhaw.iwi.devops.service.interactionelement.gui;

public class ListElementGuiModel extends InteractionElementGuiModel {

	private String icon;
	private String color;
	private String url;
	private String page;
	private int limit;
	private boolean search;
	private boolean searchRequired;
	private boolean searchRequest;
	private ElementKey key = new ElementKey();

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

	public ElementKey getKey() {
		return key;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public boolean isSearch() {
		return search;
	}

	public void setSearch(boolean search) {
		this.search = search;
	}

	public void setKey(ElementKey key) {
		this.key = key;
	}
	
	public boolean isSearchRequired() {
		return searchRequired;
	}
	
	public void setSearchRequired(boolean searchRequired) {
		this.searchRequired = searchRequired;
	}
	
	public boolean isSearchRequest() {
		return searchRequest;
	}
	
	public void setSearchRequest(boolean searchRequest) {
		this.searchRequest = searchRequest;
	}

}
