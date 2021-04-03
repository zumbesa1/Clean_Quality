package ch.zhaw.iwi.devops.model.patient;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import ch.zhaw.iwi.devops.model.AbstractEntity;
import ch.zhaw.iwi.devops.model.sport.Sport;

@Entity
public class Patient extends AbstractEntity implements Comparable<Patient> {

	private String firstName;

	private String lastName;

	private String email;

	private CivilStatusEnum civilStatus;

	private Long numberOfPersonsInHousehold;

	private String highestEducation;

	private String originalJob;

	private String currentJob;
	
	@ManyToMany
	@JoinTable(name = "Patient_Sports", inverseJoinColumns = @JoinColumn(name = "sport_key"))
	private Set<Sport> sports = new HashSet<>();

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getNumberOfPersonsInHousehold() {
		return numberOfPersonsInHousehold;
	}

	public void setNumberOfPersonsInHousehold(Long numberOfPersonsInHousehold) {
		this.numberOfPersonsInHousehold = numberOfPersonsInHousehold;
	}

	public String getHighestEducation() {
		return highestEducation;
	}

	public void setHighestEducation(String highestEducation) {
		this.highestEducation = highestEducation;
	}

	public String getOriginalJob() {
		return originalJob;
	}

	public void setOriginalJob(String originalJob) {
		this.originalJob = originalJob;
	}

	public String getCurrentJob() {
		return currentJob;
	}

	public void setCurrentJob(String currentJob) {
		this.currentJob = currentJob;
	}
	
	public CivilStatusEnum getCivilStatus() {
		return civilStatus;
	}
	
	public void setCivilStatus(CivilStatusEnum civilStatus) {
		this.civilStatus = civilStatus;
	}
	
	public Set<Sport> getSports() {
		return sports;
	}
	
	@Transient
	public String getName() {
		return this.getLastName() + " " + this.getFirstName();
	}

	@Override
	public int compareTo(Patient o) {
		return this.getLastName().compareTo(o.getLastName());
	}

}
