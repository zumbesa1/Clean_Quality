package ch.zhaw.iwi.devops.model.questionnaire;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ch.zhaw.iwi.devops.model.AbstractEntity;
import ch.zhaw.iwi.devops.model.interactionstep.InteractionStep;

@Entity
public class Questionnaire extends AbstractEntity implements Comparable<Questionnaire> {

	private String name;
	
	private String icon;
	
	private boolean patientRequired;
	
	private boolean active;
	
	@ManyToOne
	private InteractionStep firstInteractionStep;

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}

	public boolean isPatientRequired() {
		return patientRequired;
	}
	
	public void setPatientRequired(boolean patientRequired) {
		this.patientRequired = patientRequired;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public InteractionStep getFirstInteractionStep() {
		return firstInteractionStep;
	}
	
	public void setFirstInteractionStep(InteractionStep firstInteractionStep) {
		this.firstInteractionStep = firstInteractionStep;
	}
		
	@Override
	public int compareTo(Questionnaire o) {
		return this.getName().compareTo(o.getName());
	}

}
