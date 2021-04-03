package ch.zhaw.iwi.devops.service.interactionelement.form;

import ch.zhaw.iwi.devops.model.interactionelement.form.FormRadioButtonValue;
import ch.zhaw.iwi.devops.service.AbstractCrudRestServiceTest;
import ch.zhaw.iwi.devops.service.interactionelement.form.FormRadioButtonValueRestService;

public class FormRadioButtonValueRestServiceTest extends AbstractCrudRestServiceTest<FormRadioButtonValue, Long> {
	
	@Override
	protected Class<?> getService() {
		return FormRadioButtonValueRestService.class;
	}

	@Override
	protected Class<FormRadioButtonValue> getEntityClass() {
		return FormRadioButtonValue.class;
	}
	

}
