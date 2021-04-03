package ch.zhaw.iwi.devops.service.interactionelement.button;

import com.google.inject.Inject;
import com.google.inject.Injector;

import ch.zhaw.iwi.devops.model.interactionelement.button.ButtonElement;
import ch.zhaw.iwi.devops.service.AbstractCrudRestService;

public class ButtonElementRestService extends AbstractCrudRestService<ButtonElement, Long, ButtonElementDatabaseService> {

	@Inject
	public ButtonElementRestService(Injector injector) {
		super(injector, ButtonElementDatabaseService.class);
	}

}
