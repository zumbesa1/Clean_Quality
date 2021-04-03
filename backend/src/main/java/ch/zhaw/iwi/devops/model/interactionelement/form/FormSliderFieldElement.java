package ch.zhaw.iwi.devops.model.interactionelement.form;

import javax.persistence.Entity;

import ch.zhaw.iwi.devops.service.interactionelement.gui.FormSliderFieldElementGuiModel;
import ch.zhaw.iwi.devops.service.interactionstep.InteractionStepKey;

@Entity
public class FormSliderFieldElement extends FormValueFieldElement<FormSliderFieldElementGuiModel> {

	public long min = 0;
	public long max = 999999999;
	public long digits = 0;
	
	public void setMin(long min) {
		this.min = min;
	}
	
	public void setMax(long max) {
		this.max = max;
	}
			
	@Override
	public FormSliderFieldElementGuiModel toGuiModel(FormSliderFieldElementGuiModel guiModel, InteractionStepKey currentKey) {
		guiModel = new FormSliderFieldElementGuiModel();
		guiModel.setName(getName());
		guiModel.setMin(min);
		guiModel.setMax(max);
		return super.toGuiModel(guiModel, currentKey);
	}
	
}
