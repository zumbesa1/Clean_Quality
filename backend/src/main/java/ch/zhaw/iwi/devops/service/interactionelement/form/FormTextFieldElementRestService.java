package ch.zhaw.iwi.devops.service.interactionelement.form;

import com.google.inject.Inject;
import com.google.inject.Injector;

import ch.zhaw.iwi.devops.model.interactionelement.form.FormTextFieldElement;
import ch.zhaw.iwi.devops.service.AbstractCrudRestService;

public class FormTextFieldElementRestService extends AbstractCrudRestService<FormTextFieldElement, Long, FormTextFieldElementDatabaseService> {

	@Inject
	public FormTextFieldElementRestService(Injector injector) {
		super(injector, FormTextFieldElementDatabaseService.class);
	}

}
