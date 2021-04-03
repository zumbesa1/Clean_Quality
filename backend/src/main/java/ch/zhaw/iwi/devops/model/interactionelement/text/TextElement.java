package ch.zhaw.iwi.devops.model.interactionelement.text;

import javax.persistence.Entity;

import ch.zhaw.iwi.devops.model.interactionelement.InteractionElement;
import ch.zhaw.iwi.devops.service.interactionelement.gui.TextElementGuiModel;
import ch.zhaw.iwi.devops.service.interactionstep.InteractionStepKey;

@Entity
public class TextElement extends InteractionElement<TextElementGuiModel> {

	public TextElement() {
		this.setNewRow(true);
	}
	
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public String getAdministrationDisplayName() {
		return text;
	}

	@Override
	public TextElementGuiModel toGuiModel(TextElementGuiModel guiModel, InteractionStepKey currentKey) {
		guiModel = new TextElementGuiModel();
		guiModel.setType("pageLabel");
		guiModel.setValue("<h4>" + text + "</h4>");
		return super.toGuiModel(guiModel, currentKey);
	}

}
