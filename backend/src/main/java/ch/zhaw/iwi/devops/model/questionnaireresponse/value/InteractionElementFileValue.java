package ch.zhaw.iwi.devops.model.questionnaireresponse.value;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import ch.zhaw.iwi.devops.model.file.File;

@Entity
public class InteractionElementFileValue extends InteractionElementValue {

	@OneToMany(mappedBy = "interactionElementFileValue")
	private List<File> files = new ArrayList<>();

	public List<File> getFiles() {
		return files;
	}

	@Override
	public String getDisplayValue() {
		return getFiles()
				.stream()
				.map(a -> String.valueOf(a.getName()))
				.collect(Collectors.joining(", "));
	}

}
