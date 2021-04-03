package ch.zhaw.iwi.devops.service.interactionstep;

import ch.zhaw.iwi.devops.model.interactionstep.InteractionStep;
import ch.zhaw.iwi.devops.service.AbstractCrudRestServiceTest;
import ch.zhaw.iwi.devops.service.interactionstep.InteractionStepRestService;

public class InteractionStepRestServiceTest extends AbstractCrudRestServiceTest<InteractionStep, Long> {

	@Override
	protected Class<?> getService() {
		return InteractionStepRestService.class;
	}

	@Override
	protected Class<InteractionStep> getEntityClass() {
		return InteractionStep.class;
	}

}
