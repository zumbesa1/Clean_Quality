package ch.zhaw.iwi.devops.model.sport;

import javax.persistence.Entity;

import ch.zhaw.iwi.devops.model.AbstractEntity;

@Entity
public class Sport extends AbstractEntity implements Comparable<Sport> {

	private String name;
	
	private String description;
	
	private String icon;

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
	
	public String getIcon() {
		return icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public int compareTo(Sport o) {
		return this.getName().compareTo(o.getName());
	}

}
