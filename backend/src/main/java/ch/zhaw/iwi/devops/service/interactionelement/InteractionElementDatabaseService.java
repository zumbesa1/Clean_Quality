package ch.zhaw.iwi.devops.service.interactionelement;

import java.util.List;

import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import ch.zhaw.iwi.devops.model.interactionelement.InteractionElement;
import ch.zhaw.iwi.devops.model.interactionelement.InteractionElement_;
import ch.zhaw.iwi.devops.model.interactionelement.button.BackButtonElement;
import ch.zhaw.iwi.devops.model.interactionelement.button.ButtonElement;
import ch.zhaw.iwi.devops.model.interactionelement.form.FormDateFieldElement;
import ch.zhaw.iwi.devops.model.interactionelement.form.FormFileUploadElement;
import ch.zhaw.iwi.devops.model.interactionelement.form.FormNumberFieldElement;
import ch.zhaw.iwi.devops.model.interactionelement.form.FormRadioButtonListElement;
import ch.zhaw.iwi.devops.model.interactionelement.form.FormRadioButtonValue;
import ch.zhaw.iwi.devops.model.interactionelement.form.FormTextFieldElement;
import ch.zhaw.iwi.devops.model.interactionelement.list.ButtonListElement;
import ch.zhaw.iwi.devops.model.interactionelement.list.TileListElement;
import ch.zhaw.iwi.devops.model.interactionelement.text.TextElement;
import ch.zhaw.iwi.devops.service.AbstractCrudDatabaseService;
import ch.zhaw.iwi.devops.service.PathListEntry;

@SuppressWarnings("rawtypes")
public class InteractionElementDatabaseService extends AbstractCrudDatabaseService<InteractionElement, Long> {

	@Override
	public Class<InteractionElement> getEntityClass() {
		return InteractionElement.class;
	}

	@Override
	public void createPathListEntry(InteractionElement entity, PathListEntry<Long> entry) {
		entry.setKey(entity.getKey(), getKeyName());
		if (entity instanceof BackButtonElement) {
			entry.setName(entity.getSortOrder() + ": Zurück-Button");
			entry.getDetails().add("Zurück-Button");
			entry.setIcon("fa-backward");
			entry.setTooltip("Zurück-Button");
			entry.setForm("BackButtonElementForm");
		} else if (entity instanceof ButtonElement) {
			ButtonElement buttonElement = (ButtonElement) entity;
			entry.setName(entity.getSortOrder() + ": Button");
			entry.getDetails().add("Button " + buttonElement.getName());
			entry.setIcon("fa-hand-o-up");
			entry.setTooltip("Button");
			entry.setForm("ButtonElementForm");
		} else if (entity instanceof FormDateFieldElement) {
			FormDateFieldElement formDateFieldElement = (FormDateFieldElement) entity;
			entry.setName(entity.getSortOrder() + ": Datumsfeld");
			entry.getDetails().add(formDateFieldElement.getName());
			entry.setIcon("fa-calendar");
			entry.setTooltip("Datumsfeld");
			entry.setForm("FormDateFieldElementForm");
		} else if (entity instanceof FormNumberFieldElement) {
			FormNumberFieldElement formNumberFieldElement = (FormNumberFieldElement) entity;
			entry.setName(entity.getSortOrder() + ": Zahlenfeld");
			entry.getDetails().add(formNumberFieldElement.getName());
			entry.setIcon("fa-sort-numeric-asc");
			entry.setTooltip("Zahlenfeld");
			entry.setForm("FormNumberFieldElementForm");
		} else if (entity instanceof FormRadioButtonListElement) {
			FormRadioButtonListElement formRadioButtonListElement = (FormRadioButtonListElement) entity;
			entry.setName(entity.getSortOrder() + ": Radio-Buttons");
			StringBuilder radioText = new StringBuilder();
			for (FormRadioButtonValue radio : formRadioButtonListElement.getRadios()) {
				if (radioText.length() > 0) {
					radioText.append(", ");
				}
				radioText.append(radio.getName());
			}
			entry.getDetails().add(formRadioButtonListElement.getName());
			entry.getDetails().add(radioText.toString());
			entry.setIcon("fa-times-circle-o");
			entry.setTooltip("Radio-Buttons mit Werten " + radioText.toString());
			entry.setPage("formradiobuttonvaluespage"); // needs page due to radio button values page
		} else if (entity instanceof FormTextFieldElement) {
			FormTextFieldElement formTextFieldElement = (FormTextFieldElement) entity;
			entry.setName(entity.getSortOrder() + ": Textfeld");
			entry.getDetails().add(formTextFieldElement.getName());
			entry.setIcon("fa-align-left");
			entry.setTooltip("Button");
			entry.setForm("FormTextFieldElementForm");
		} else if (entity instanceof FormFileUploadElement) {
			FormFileUploadElement formFileUploadElement = (FormFileUploadElement) entity;
			entry.setName(entity.getSortOrder() + ": Datei-Upload");
			entry.getDetails().add(formFileUploadElement.getName());
			entry.setIcon("fa-file");
			entry.setTooltip("Button");
			entry.setPage("formfiletypespage"); // needs page due to file types
		} else if (entity instanceof ButtonListElement) {
			ButtonListElement listElement = (ButtonListElement) entity;
			entry.setName(entity.getSortOrder() + ": Button-Liste");
			entry.setIcon("fa-th-large");
			entry.setForm("ButtonListElementForm");
		} else if (entity instanceof TileListElement) {
			TileListElement listElement = (TileListElement) entity;
			entry.setName(entity.getSortOrder() + ": Kachel-Liste");
			entry.setIcon("fa-th-large");
			entry.setForm("TileListElementForm");
		} else if (entity instanceof TextElement) {
			TextElement textElement = (TextElement) entity;
			entry.setName(entity.getSortOrder() + ": Text");
			entry.getDetails().add(textElement.getText());
			entry.setIcon("fa-font");
			entry.setTooltip("Text: " + textElement.getText());
			entry.setForm("TextElementForm");
		} else {
			entry.setName("Unknown Type");
		}
	}

	@Override
	protected void orderBy(Root<InteractionElement> root, List<Order> orderList) {
		orderList.add(getCriteriaBuilder().asc(root.get(InteractionElement_.sortOrder)));
	}

}