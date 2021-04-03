package ch.zhaw.iwi.devops.service.interactionelement.form;

import ch.zhaw.iwi.devops.model.interactionelement.form.FormTextFieldElement;
import ch.zhaw.iwi.devops.service.AbstractCrudRestServiceTest;
import ch.zhaw.iwi.devops.service.interactionelement.form.FormTextFieldElementRestService;

public class FormTextFieldElementRestServiceTest extends AbstractCrudRestServiceTest<FormTextFieldElement, Long> {
	
	@Override
	protected Class<?> getService() {
		return FormTextFieldElementRestService.class;
	}

	@Override
	protected Class<FormTextFieldElement> getEntityClass() {
		return FormTextFieldElement.class;
	}
	

}
