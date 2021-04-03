package ch.zhaw.iwi.devops.service.interactionelement.button;

import ch.zhaw.iwi.devops.model.interactionelement.button.BackButtonElement;
import ch.zhaw.iwi.devops.service.AbstractCrudDatabaseServiceTest;
import ch.zhaw.iwi.devops.service.interactionelement.button.BackButtonElementDatabaseService;

public class BackButtonElementDatabaseServiceTest extends AbstractCrudDatabaseServiceTest<BackButtonElement, Long> {

	@Override
	protected Class<?> getService() {
		return BackButtonElementDatabaseService.class;
	}

	@Override
	protected Class<BackButtonElement> getEntity() {
		return BackButtonElement.class;
	}
	

}