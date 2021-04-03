package ch.zhaw.iwi.devops.model.questionnaireresponse.value;

import javax.persistence.Entity;

@Entity
public class InteractionElementLongValue extends InteractionElementValue {

	private Long longValue;

	public Long getLongValue() {
		return longValue;
	}
	
	public void setLongValue(Long longValue) {
		this.longValue = longValue;
	}

	@Override
	public String getDisplayValue() {
		return String.valueOf(longValue);
	}

}
