package ch.zhaw.iwi.devops.service.interactionelement.form;

import com.google.inject.Inject;
import com.google.inject.Injector;

import ch.zhaw.iwi.devops.model.interactionelement.form.FormNumberFieldElement;
import ch.zhaw.iwi.devops.service.AbstractCrudRestService;

public class FormNumberFieldElementRestService extends AbstractCrudRestService<FormNumberFieldElement, Long, FormNumberFieldElementDatabaseService> {

	@Inject
	public FormNumberFieldElementRestService(Injector injector) {
		super(injector, FormNumberFieldElementDatabaseService.class);
	}

}
