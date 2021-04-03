package ch.zhaw.iwi.devops.model.interactionelement.button;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ch.zhaw.iwi.devops.model.AbstractEntity;

@Entity
public class ButtonSelectionElementValue extends AbstractEntity implements Comparable<ButtonSelectionElementValue> {

	@ManyToOne
	private ButtonSelectionElement buttonSelectionElement;

	private String value;
	private String name;
	private ArrayList<String> details = new ArrayList<>();
	private String icon;

	public ButtonSelectionElement getButtonSelectionElement() {
		return buttonSelectionElement;
	}
	
	public void setButtonSelectionElement(ButtonSelectionElement buttonSelectionElement) {
		this.buttonSelectionElement = buttonSelectionElement;
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
	
	public ArrayList<String> getDetails() {
		return details;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	@Override
	public int compareTo(ButtonSelectionElementValue o) {
		return this.getName().compareTo(o.getName());
	}

}
