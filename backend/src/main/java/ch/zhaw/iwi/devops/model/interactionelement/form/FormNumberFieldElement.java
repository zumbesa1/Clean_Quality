package ch.zhaw.iwi.devops.model.interactionelement.form;

import javax.persistence.Entity;

import ch.zhaw.iwi.devops.service.interactionelement.gui.FormNumberFieldElementGuiModel;
import ch.zhaw.iwi.devops.service.interactionstep.InteractionStepKey;

@Entity
public class FormNumberFieldElement extends FormValueFieldElement<FormNumberFieldElementGuiModel> {

	public long min = 0;
	public long max = 999999999;
	public long digits = 0;
	
	public void setMin(long min) {
		this.min = min;
	}
	
	public void setMax(long max) {
		this.max = max;
	}
	
	public void setDigits(long digits) {
		this.digits = digits;
	}
	
	public long getDigits() {
		return digits;
	}
		
	@Override
	public FormNumberFieldElementGuiModel toGuiModel(FormNumberFieldElementGuiModel guiModel, InteractionStepKey currentKey) {
		guiModel = new FormNumberFieldElementGuiModel();
		guiModel.setName(getName());
		guiModel.setMin(min);
		guiModel.setMax(max);
		guiModel.setDigits(digits);
		return super.toGuiModel(guiModel, currentKey);
	}
	
}
