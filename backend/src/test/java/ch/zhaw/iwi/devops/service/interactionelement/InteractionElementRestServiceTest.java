package ch.zhaw.iwi.devops.service.interactionelement;

import ch.zhaw.iwi.devops.model.interactionelement.InteractionElement;
import ch.zhaw.iwi.devops.service.AbstractCrudRestServiceTest;
import ch.zhaw.iwi.devops.service.interactionelement.InteractionElementRestService;

@SuppressWarnings("rawtypes")
public class InteractionElementRestServiceTest extends AbstractCrudRestServiceTest<InteractionElement, Long> {

	@Override
	protected Class<?> getService() {
		return InteractionElementRestService.class;
	}

	@Override
	protected Class<InteractionElement> getEntityClass() {
		return InteractionElement.class;
	}

}
