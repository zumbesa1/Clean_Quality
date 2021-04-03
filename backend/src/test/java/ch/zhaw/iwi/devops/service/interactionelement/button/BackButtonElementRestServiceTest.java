package ch.zhaw.iwi.devops.service.interactionelement.button;

import ch.zhaw.iwi.devops.model.interactionelement.button.BackButtonElement;
import ch.zhaw.iwi.devops.service.AbstractCrudRestServiceTest;
import ch.zhaw.iwi.devops.service.interactionelement.button.BackButtonElementRestService;

public class BackButtonElementRestServiceTest extends AbstractCrudRestServiceTest<BackButtonElement, Long> {

	@Override
	protected Class<?> getService() {
		return BackButtonElementRestService.class;
	}

	@Override
	protected Class<BackButtonElement> getEntityClass() {
		return BackButtonElement.class;
	}

}
