package ch.zhaw.iwi.devops.service.interactionelement.form;

import ch.zhaw.iwi.devops.model.interactionelement.form.FormNumberFieldElement;
import ch.zhaw.iwi.devops.service.AbstractCrudRestServiceTest;
import ch.zhaw.iwi.devops.service.interactionelement.form.FormNumberFieldElementRestService;

public class FormNumberFieldElementRestServiceTest extends AbstractCrudRestServiceTest<FormNumberFieldElement, Long> {
	
	@Override
	protected Class<?> getService() {
		return FormNumberFieldElementRestService.class;
	}

	@Override
	protected Class<FormNumberFieldElement> getEntityClass() {
		return FormNumberFieldElement.class;
	}
	

}
