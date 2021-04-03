package ch.zhaw.iwi.devops.model.file;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import ch.zhaw.iwi.devops.model.AbstractEntity;
import ch.zhaw.iwi.devops.model.questionnaireresponse.value.InteractionElementFileValue;

@Entity
public class File extends AbstractEntity implements Comparable<File> {

	private String name;

	private long size;
	
	private String mimetype;
	
	@ManyToOne
	private InteractionElementFileValue interactionElementFileValue;
	
	@Lob
	private byte[] content;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public long getSize() {
		return size;
	}
	
	public void setSize(long size) {
		this.size = size;
	}
	
	public String getMimetype() {
		return mimetype;
	}
	
	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}
	
	public byte[] getContent() {
		return content;
	}
	
	public void setContent(byte[] content) {
		this.content = content;
	}
	
	public InteractionElementFileValue getInteractionElementFileValue() {
		return interactionElementFileValue;
	}
	
	public void setInteractionElementFileValue(InteractionElementFileValue interactionElementFileValue) {
		this.interactionElementFileValue = interactionElementFileValue;
	}

	@Override
	public int compareTo(File o) {
		return this.getName().compareTo(o.getName());
	}

}
