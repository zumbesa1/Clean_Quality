package ch.zhaw.iwi.devops.model.interactionelement.form;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import ch.zhaw.iwi.devops.service.interactionelement.gui.FormRadioButtonListElementGuiModel;
import ch.zhaw.iwi.devops.service.interactionstep.InteractionStepKey;

@Entity
public class FormRadioButtonListElement extends FormValueFieldElement<FormRadioButtonListElementGuiModel> {

	@OneToMany(mappedBy = "radioButtonList")
	private Set<FormRadioButtonValue> radios = new HashSet<>();

	public Set<FormRadioButtonValue> getRadios() {
		return radios;
	}

	@Override
	public FormRadioButtonListElementGuiModel toGuiModel(FormRadioButtonListElementGuiModel guiModel, InteractionStepKey currentKey) {
		guiModel = new FormRadioButtonListElementGuiModel();
		guiModel.setName(getName());
		for (FormRadioButtonValue radio : radios) {
			guiModel.addRadio(radio.getName(), radio.getValue());
		}
		if (guiModel.getRadios().isEmpty()) {
			guiModel.addRadio("Kein Radio Button konfiguriert", "0");
		}
		return super.toGuiModel(guiModel, currentKey);
	}

}
