package ch.zhaw.iwi.devops.service.interactionelement.list;

import ch.zhaw.iwi.devops.model.interactionelement.list.ButtonListElement;
import ch.zhaw.iwi.devops.service.AbstractCrudDatabaseServiceTest;
import ch.zhaw.iwi.devops.service.interactionelement.list.ButtonListElementDatabaseService;

public class ButtonListElementDatabaseServiceTest extends AbstractCrudDatabaseServiceTest<ButtonListElement, Long> {

	@Override
	protected Class<?> getService() {
		return ButtonListElementDatabaseService.class;
	}

	@Override
	protected Class<ButtonListElement> getEntity() {
		return ButtonListElement.class;
	}
	

}