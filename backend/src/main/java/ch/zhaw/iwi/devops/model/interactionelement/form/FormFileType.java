package ch.zhaw.iwi.devops.model.interactionelement.form;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import ch.zhaw.iwi.devops.model.AbstractEntity;

@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = { "formFileUploadElement_key", "fileType" })
})
public class FormFileType extends AbstractEntity implements Comparable<FormFileType> {

	@ManyToOne
	private FormFileUploadElement formFileUploadElement;

	private String fileType;

	public FormFileUploadElement getFormFileUploadElement() {
		return formFileUploadElement;
	}

	public void setFormFileUploadElement(FormFileUploadElement formFileUploadElement) {
		this.formFileUploadElement = formFileUploadElement;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Override
	public int compareTo(FormFileType o) {
		return this.getFileType().compareTo(o.getFileType());
	}

}
