package ch.zhaw.iwi.devops.service.interactionelement.gui;

import java.util.ArrayList;
import java.util.List;

public class FormFileUploadElementGuiModel extends FormValueFieldElementGuiModel {

	private boolean multiple = true;
	private String url = "/upload";
	private List<String> acceptedFileTypes = new ArrayList<>();
	
	public boolean isMultiple() {
		return multiple;
	}
	
	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public List<String> getAcceptedFileTypes() {
		return acceptedFileTypes;
	}
		
	public FormFileUploadElementGuiModel() {
		super();
		this.setType("fileUpload");
	}
	
}
