package ch.zhaw.iwi.devops.service.interactionelement;

import ch.zhaw.iwi.devops.model.interactionelement.InteractionElement;
import ch.zhaw.iwi.devops.service.AbstractCrudDatabaseServiceTest;
import ch.zhaw.iwi.devops.service.interactionelement.InteractionElementDatabaseService;

@SuppressWarnings("rawtypes")
public class InteractionElementDatabaseServiceTest extends AbstractCrudDatabaseServiceTest<InteractionElement, Long> {

	@Override
	protected Class<?> getService() {
		return InteractionElementDatabaseService.class;
	}

	@Override
	protected Class<InteractionElement> getEntity() {
		return InteractionElement.class;
	}
	

}