package ch.zhaw.iwi.devops.model.interactionelement.form;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import ch.zhaw.iwi.devops.service.interactionelement.gui.FormFileUploadElementGuiModel;
import ch.zhaw.iwi.devops.service.interactionstep.InteractionStepKey;

@Entity
public class FormFileUploadElement extends FormValueFieldElement<FormFileUploadElementGuiModel> {

	@OneToMany(mappedBy = "formFileUploadElement")
	private Set<FormFileType> acceptedFileTypes = new HashSet<>();

	public Set<FormFileType> getAcceptedFileTypes() {
		return acceptedFileTypes;
	}

	@Override
	public FormFileUploadElementGuiModel toGuiModel(FormFileUploadElementGuiModel guiModel, InteractionStepKey currentKey) {
		guiModel = new FormFileUploadElementGuiModel();
		for (FormFileType fileType : acceptedFileTypes) {
			if (fileType.getFileType().startsWith(".")) {
				guiModel.getAcceptedFileTypes().add(fileType.getFileType());
			} else {
				guiModel.getAcceptedFileTypes().add("." + fileType.getFileType());
			}
		}
		return super.toGuiModel(guiModel, currentKey);
	}

}
