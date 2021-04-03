package ch.zhaw.iwi.devops.service.interactionelement.form;

import ch.zhaw.iwi.devops.model.interactionelement.form.FormRadioButtonValue;
import ch.zhaw.iwi.devops.service.AbstractCrudDatabaseServiceTest;
import ch.zhaw.iwi.devops.service.interactionelement.form.FormRadioButtonValueDatabaseService;

public class FormRadioButtonValueDatabaseServiceTest extends AbstractCrudDatabaseServiceTest<FormRadioButtonValue, Long> {

	@Override
	protected Class<?> getService() {
		return FormRadioButtonValueDatabaseService.class;
	}

	@Override
	protected Class<FormRadioButtonValue> getEntity() {
		return FormRadioButtonValue.class;
	}
	

}