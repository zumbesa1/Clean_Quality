package ch.zhaw.iwi.devops.service.interactionelement.form;

import ch.zhaw.iwi.devops.model.interactionelement.form.FormDateFieldElement;
import ch.zhaw.iwi.devops.service.AbstractCrudDatabaseServiceTest;
import ch.zhaw.iwi.devops.service.interactionelement.form.FormDateFieldElementDatabaseService;

public class FormDateFieldElementDatabaseServiceTest extends AbstractCrudDatabaseServiceTest<FormDateFieldElement, Long> {

	@Override
	protected Class<?> getService() {
		return FormDateFieldElementDatabaseService.class;
	}

	@Override
	protected Class<FormDateFieldElement> getEntity() {
		return FormDateFieldElement.class;
	}
	

}