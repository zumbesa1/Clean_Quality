package ch.zhaw.iwi.devops.model.questionnaireresponse.value;

import javax.persistence.Entity;

@Entity
public class InteractionElementTextValue extends InteractionElementValue {
	
	private String textValue;
	private String exportValue;
	
	public String getTextValue() {
		return textValue;
	}
	
	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}
	
	public String getExportValue() {
		return exportValue;
	}
	
	public void setExportValue(String exportValue) {
		this.exportValue = exportValue;
	}
		
	@Override
	public String getDisplayValue() {
		return textValue;
	}
	
}
