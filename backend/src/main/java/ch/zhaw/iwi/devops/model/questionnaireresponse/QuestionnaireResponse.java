package ch.zhaw.iwi.devops.model.questionnaireresponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ch.zhaw.iwi.devops.model.AbstractEntity;
import ch.zhaw.iwi.devops.model.interactionelement.button.ButtonSelectionElementValue;
import ch.zhaw.iwi.devops.model.patient.Patient;
import ch.zhaw.iwi.devops.model.questionnaire.Questionnaire;
import ch.zhaw.iwi.devops.model.questionnaireresponse.value.InteractionElementValue;

@Entity
public class QuestionnaireResponse extends AbstractEntity implements Comparable<QuestionnaireResponse> {

	@ManyToOne
	private Patient patient;
	
	@ManyToOne
	private Questionnaire questionnaire;
	
	@OneToMany(mappedBy = "questionnaireResponse")
	private List<InteractionElementValue> values = new ArrayList<>();
	
	@ManyToMany
	private Set<ButtonSelectionElementValue> selectedButtonSelectionElementValues = new HashSet<>();
	
	private Date date;
		
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}
	
	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}
		
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
				
	public List<InteractionElementValue> getValues() {
		return values;
	}
	
	public void setValues(List<InteractionElementValue> values) {
		this.values = values;
	}
	
	public Set<ButtonSelectionElementValue> getSelectedButtonSelectionElementValues() {
		return selectedButtonSelectionElementValues;
	}
	
	public void setSelectedButtonSelectionElementValues(Set<ButtonSelectionElementValue> selectedButtonSelectionElementValues) {
		this.selectedButtonSelectionElementValues = selectedButtonSelectionElementValues;
	}
		
	@Override
	public int compareTo(QuestionnaireResponse o) {
		return this.getKey().compareTo(o.getKey());
	}

}
