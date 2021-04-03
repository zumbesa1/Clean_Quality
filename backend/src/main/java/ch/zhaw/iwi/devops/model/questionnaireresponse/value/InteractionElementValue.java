package ch.zhaw.iwi.devops.model.questionnaireresponse.value;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ch.zhaw.iwi.devops.model.AbstractEntity;
import ch.zhaw.iwi.devops.model.interactionelement.InteractionElement;
import ch.zhaw.iwi.devops.model.questionnaireresponse.QuestionnaireResponse;

@Entity
public class InteractionElementValue extends AbstractEntity {

	@ManyToOne
	private QuestionnaireResponse questionnaireResponse;

	@ManyToOne
	private InteractionElement<?> interactionElement;

	public InteractionElement<?> getInteractionElement() {
		return interactionElement;
	}

	public void setInteractionElement(InteractionElement<?> interactionElement) {
		this.interactionElement = interactionElement;
	}

	public QuestionnaireResponse getQuestionnaireResponse() {
		return questionnaireResponse;
	}

	public void setQuestionnaireResponse(QuestionnaireResponse questionnaireResponse) {
		this.questionnaireResponse = questionnaireResponse;
	}

	public String getDisplayValue() {
		return new String();
	}
	
}
