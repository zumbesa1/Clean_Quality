package ch.zhaw.iwi.devops.service.interactionelement.form;

import ch.zhaw.iwi.devops.model.interactionelement.form.FormNumberFieldElement;
import ch.zhaw.iwi.devops.service.AbstractCrudDatabaseServiceTest;
import ch.zhaw.iwi.devops.service.interactionelement.form.FormNumberFieldElementDatabaseService;

public class FormNumberFieldElementDatabaseServiceTest extends AbstractCrudDatabaseServiceTest<FormNumberFieldElement, Long> {

	@Override
	protected Class<?> getService() {
		return FormNumberFieldElementDatabaseService.class;
	}

	@Override
	protected Class<FormNumberFieldElement> getEntity() {
		return FormNumberFieldElement.class;
	}
	

}