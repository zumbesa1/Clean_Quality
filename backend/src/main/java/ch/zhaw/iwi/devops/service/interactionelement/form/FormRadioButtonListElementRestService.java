package ch.zhaw.iwi.devops.service.interactionelement.form;

import com.google.inject.Inject;
import com.google.inject.Injector;

import ch.zhaw.iwi.devops.model.interactionelement.form.FormRadioButtonListElement;
import ch.zhaw.iwi.devops.service.AbstractCrudRestService;

public class FormRadioButtonListElementRestService extends AbstractCrudRestService<FormRadioButtonListElement, Long, FormRadioButtonListElementDatabaseService> {

	@Inject
	public FormRadioButtonListElementRestService(Injector injector) {
		super(injector, FormRadioButtonListElementDatabaseService.class);
	}

}
