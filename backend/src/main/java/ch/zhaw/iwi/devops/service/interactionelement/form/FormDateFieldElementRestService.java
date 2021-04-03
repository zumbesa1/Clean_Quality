package ch.zhaw.iwi.devops.service.interactionelement.form;

import com.google.inject.Inject;
import com.google.inject.Injector;

import ch.zhaw.iwi.devops.model.interactionelement.form.FormDateFieldElement;
import ch.zhaw.iwi.devops.service.AbstractCrudRestService;

public class FormDateFieldElementRestService extends AbstractCrudRestService<FormDateFieldElement, Long, FormDateFieldElementDatabaseService> {

	@Inject
	public FormDateFieldElementRestService(Injector injector) {
		super(injector, FormDateFieldElementDatabaseService.class);
	}

}
