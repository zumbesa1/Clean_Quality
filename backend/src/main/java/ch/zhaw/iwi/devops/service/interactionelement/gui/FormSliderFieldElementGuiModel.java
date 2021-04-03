package ch.zhaw.iwi.devops.service.interactionelement.gui;

public class FormSliderFieldElementGuiModel extends FormValueFieldElementGuiModel {

	public long min = 0;
	public long max = 999999999;
	public long value = 5;

	public FormSliderFieldElementGuiModel() {
		super();
		this.setType("SliderField");
	}

	public long getMin() {
		return min;
	}

	public void setMin(long min) {
		this.min = min;
	}

	public long getMax() {
		return max;
	}

	public void setMax(long max) {
		this.max = max;
	}

}
