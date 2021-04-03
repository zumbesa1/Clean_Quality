package ch.zhaw.iwi.devops.service.interactionelement.form;

import ch.zhaw.iwi.devops.model.interactionelement.form.FormRadioButtonListElement;
import ch.zhaw.iwi.devops.service.AbstractCrudDatabaseServiceTest;
import ch.zhaw.iwi.devops.service.interactionelement.form.FormRadioButtonListElementDatabaseService;

public class FormRadioButtonListElementDatabaseServiceTest extends AbstractCrudDatabaseServiceTest<FormRadioButtonListElement, Long> {

	@Override
	protected Class<?> getService() {
		return FormRadioButtonListElementDatabaseService.class;
	}

	@Override
	protected Class<FormRadioButtonListElement> getEntity() {
		return FormRadioButtonListElement.class;
	}
	

}