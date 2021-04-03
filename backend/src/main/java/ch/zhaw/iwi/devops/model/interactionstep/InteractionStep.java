package ch.zhaw.iwi.devops.model.interactionstep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import ch.zhaw.iwi.devops.model.AbstractEntity;
import ch.zhaw.iwi.devops.model.interactionelement.InteractionElement;
import ch.zhaw.iwi.devops.model.interactionelement.form.FormElement;
import ch.zhaw.iwi.devops.model.interactionelement.form.FormValueFieldElement;
import ch.zhaw.iwi.devops.service.interactionelement.gui.BackButtonElementGuiModel;
import ch.zhaw.iwi.devops.service.interactionelement.gui.FormElementGuiModel;
import ch.zhaw.iwi.devops.service.interactionelement.gui.InteractionElementGuiModel;
import ch.zhaw.iwi.devops.service.interactionelement.gui.TextElementGuiModel;
import ch.zhaw.iwi.devops.service.interactionstep.InteractionStepKey;

@Entity
public class InteractionStep extends AbstractEntity implements Comparable<InteractionStep> {

	private String name;

	@OneToMany(mappedBy = "interactionStep")
	private Set<InteractionElement<?>> interactionElements = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<InteractionElement<?>> getInteractionElements() {
		return interactionElements;
	}

	public List<InteractionElementGuiModel> getInteractionElementsGuiModels(InteractionStepKey currentKey) {
		List<InteractionElementGuiModel> result = new ArrayList<>();
		Map<InteractionStep, FormElement> nextFormInteractionStep = new HashMap<>();

		for (InteractionElement<?> element : new TreeSet<>(getInteractionElements())) {
			if (element instanceof FormValueFieldElement<?>) {
				if (!nextFormInteractionStep.containsKey(element.getNextInteractionStep())) {
					FormElement form = new FormElement();
					form.setKey(element.getKey());
					form.setNextInteractionStep(element.getNextInteractionStep());
					result.add(form.toGuiModel(null, currentKey));
					nextFormInteractionStep.put(element.getNextInteractionStep(), form);
				}
			} else {
				result.add(element.toGuiModel(null, currentKey));
			}
		}
		// add automatic back button
		BackButtonElementGuiModel backButton = new BackButtonElementGuiModel();
		if (!result.isEmpty() && result.get(0) instanceof TextElementGuiModel) {
			if (result.size() >= 2 && result.get(1) instanceof FormElementGuiModel) {
				result.add(0, backButton);
			} else {
				result.add(1, backButton);
			}
		} else {
			result.add(0, backButton);
		}
		
		return result;
	}

	@Override
	public int compareTo(InteractionStep o) {
		int nameComparison = this.getName().compareTo(o.getName());
		if (nameComparison != 0) {
			return nameComparison;
		}
		return this.getKey().compareTo(o.getKey());
	}

}
