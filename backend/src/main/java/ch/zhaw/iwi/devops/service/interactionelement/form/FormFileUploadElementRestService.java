package ch.zhaw.iwi.devops.service.interactionelement.form;

import com.google.inject.Inject;
import com.google.inject.Injector;

import ch.zhaw.iwi.devops.model.interactionelement.form.FormFileUploadElement;
import ch.zhaw.iwi.devops.service.AbstractCrudRestService;

public class FormFileUploadElementRestService extends AbstractCrudRestService<FormFileUploadElement, Long, FormFileUploadElementDatabaseService> {

	@Inject
	public FormFileUploadElementRestService(Injector injector) {
		super(injector, FormFileUploadElementDatabaseService.class);
	}

}
