package ch.zhaw.iwi.devops.service.interactionelement.gui;

public class FormNumberFieldElementGuiModel extends FormValueFieldElementGuiModel {

	public long min = 0;
	public long max = 999999999;
	public long digits = 0;

	public FormNumberFieldElementGuiModel() {
		super();
		this.setType("number");
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

	public long getDigits() {
		return digits;
	}

	public void setDigits(long digits) {
		this.digits = digits;
	}

}
