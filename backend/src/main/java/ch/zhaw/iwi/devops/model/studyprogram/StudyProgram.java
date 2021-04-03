package ch.zhaw.iwi.devops.model.studyprogram;

import javax.persistence.Entity;

import ch.zhaw.iwi.devops.model.AbstractEntity;

@Entity
public class StudyProgram extends AbstractEntity implements Comparable<StudyProgram> {

	private String name;
	
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public int compareTo(StudyProgram o) {
		return this.getName().compareTo(o.getName());
	}

}
