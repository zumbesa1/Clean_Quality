package ch.zhaw.iwi.devops.service.interactionelement.gui;

import java.util.ArrayList;
import java.util.List;

public class FormRadioButtonListElementGuiModel extends FormValueFieldElementGuiModel {

	private List<Radio> radios = new ArrayList<>();

	public FormRadioButtonListElementGuiModel() {
		super();
		this.setType("RadioGroupField");
	}

	public void addRadio(String name, String key) {
		Radio radio = new Radio();
		radio.setName(name);
		radio.setKey(key);
		radios.add(radio);
	}

	public List<Radio> getRadios() {
		return radios;
	}

	public class Radio {

		private String type = "radio";
		private String name;
		private String key;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

	}

}
