package ch.zhaw.iwi.devops.model.interactionelement.form;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ch.zhaw.iwi.devops.model.AbstractEntity;

@Entity
public class FormRadioButtonValue extends AbstractEntity implements Comparable<FormRadioButtonValue> {

	@ManyToOne
	private FormRadioButtonListElement radioButtonList;

	private String value;

	private String name;

	public FormRadioButtonListElement getRadioButtonList() {
		return radioButtonList;
	}

	public void setRadioButtonList(FormRadioButtonListElement radioButtonList) {
		this.radioButtonList = radioButtonList;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int compareTo(FormRadioButtonValue o) {
		return this.getName().compareTo(o.getName());
	}

}
