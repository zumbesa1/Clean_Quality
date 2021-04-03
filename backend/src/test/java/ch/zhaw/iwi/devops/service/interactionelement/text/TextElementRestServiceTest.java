package ch.zhaw.iwi.devops.service.interactionelement.text;

import ch.zhaw.iwi.devops.model.interactionelement.text.TextElement;
import ch.zhaw.iwi.devops.service.AbstractCrudRestServiceTest;
import ch.zhaw.iwi.devops.service.interactionelement.text.TextElementRestService;

public class TextElementRestServiceTest extends AbstractCrudRestServiceTest<TextElement, Long> {

	@Override
	protected Class<?> getService() {
		return TextElementRestService.class;
	}

	@Override
	protected Class<TextElement> getEntityClass() {
		return TextElement.class;
	}

}
