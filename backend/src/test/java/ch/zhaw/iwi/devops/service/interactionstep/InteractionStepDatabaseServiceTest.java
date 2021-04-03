package ch.zhaw.iwi.devops.service.interactionstep;

import ch.zhaw.iwi.devops.model.interactionstep.InteractionStep;
import ch.zhaw.iwi.devops.service.AbstractCrudDatabaseServiceTest;
import ch.zhaw.iwi.devops.service.interactionstep.InteractionStepDatabaseService;

public class InteractionStepDatabaseServiceTest extends AbstractCrudDatabaseServiceTest<InteractionStep, Long> {

	@Override
	protected Class<?> getService() {
		return InteractionStepDatabaseService.class;
	}

	@Override
	protected Class<InteractionStep> getEntity() {
		return InteractionStep.class;
	}
	

}