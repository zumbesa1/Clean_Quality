package ch.zhaw.iwi.devops.service.interactionelement.button;

import com.google.inject.Inject;
import com.google.inject.Injector;

import ch.zhaw.iwi.devops.model.interactionelement.button.ButtonElement;
import ch.zhaw.iwi.devops.service.AbstractCrudRestService;

public class ButtonSelectionElementValueRestService extends AbstractCrudRestService<ButtonElement, Long, ButtonSelectionElementValueDatabaseService> {

	@Inject
	public ButtonSelectionElementValueRestService(Injector injector) {
		super(injector, ButtonSelectionElementValueDatabaseService.class);
	}

}
