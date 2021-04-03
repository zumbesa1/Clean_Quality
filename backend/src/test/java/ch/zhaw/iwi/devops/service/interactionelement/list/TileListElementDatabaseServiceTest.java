package ch.zhaw.iwi.devops.service.interactionelement.list;

import ch.zhaw.iwi.devops.model.interactionelement.list.TileListElement;
import ch.zhaw.iwi.devops.service.AbstractCrudDatabaseServiceTest;
import ch.zhaw.iwi.devops.service.interactionelement.list.TileListElementDatabaseService;

public class TileListElementDatabaseServiceTest extends AbstractCrudDatabaseServiceTest<TileListElement, Long> {

	@Override
	protected Class<?> getService() {
		return TileListElementDatabaseService.class;
	}

	@Override
	protected Class<TileListElement> getEntity() {
		return TileListElement.class;
	}
	

}