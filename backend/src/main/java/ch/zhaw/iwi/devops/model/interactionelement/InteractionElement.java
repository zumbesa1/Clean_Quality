package ch.zhaw.iwi.devops.model.interactionelement;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import ch.zhaw.iwi.devops.model.AbstractEntity;
import ch.zhaw.iwi.devops.model.interactionstep.InteractionStep;
import ch.zhaw.iwi.devops.service.interactionelement.gui.InteractionElementGuiModel;
import ch.zhaw.iwi.devops.service.interactionstep.InteractionStepKey;

@Entity
public class InteractionElement<T extends InteractionElementGuiModel> extends AbstractEntity implements Comparable<InteractionElement<?>> {

	private long sortOrder;
	private boolean newRow = false;

	@ManyToOne
	private InteractionStep interactionStep;

	@ManyToOne
	private InteractionStep nextInteractionStep;
	
	public long getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(long sortOrder) {
		this.sortOrder = sortOrder;
	}

	public InteractionStep getInteractionStep() {
		return interactionStep;
	}

	public void setInteractionStep(InteractionStep interactionStep) {
		this.interactionStep = interactionStep;
	}
	
	public void setNewRow(boolean newRow) {
		this.newRow = newRow;
	}
	
	public boolean isNewRow() {
		return newRow;
	}
	
	public InteractionStep getNextInteractionStep() {
		return nextInteractionStep;
	}
	
	public void setNextInteractionStep(InteractionStep nextInteractionStep) {
		this.nextInteractionStep = nextInteractionStep;
	}

	@Transient
	public String getAdministrationDisplayName() {
		return interactionStep.getName();
	}

	@Transient
	public String getExportKey() {
		return null;
	}
	
	@Transient
	public T toGuiModel(T guiModel, InteractionStepKey currentKey) {
		guiModel.setNewRow(newRow);
		return guiModel;
	}

	@Override
	public int compareTo(InteractionElement<?> o) {
		int sortOrderComparison = new Long(this.sortOrder).compareTo(o.sortOrder);
		if (sortOrderComparison != 0) {
			return sortOrderComparison;
		}
		return this.getKey().compareTo(o.getKey());
	}

}
