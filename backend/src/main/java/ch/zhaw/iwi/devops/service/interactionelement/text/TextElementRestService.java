package ch.zhaw.iwi.devops.service.interactionelement.text;

import com.google.inject.Inject;
import com.google.inject.Injector;

import ch.zhaw.iwi.devops.model.interactionelement.text.TextElement;
import ch.zhaw.iwi.devops.service.AbstractCrudRestService;

public class TextElementRestService extends AbstractCrudRestService<TextElement, Long, TextElementDatabaseService> {

	@Inject
	public TextElementRestService(Injector injector) {
		super(injector, TextElementDatabaseService.class);
	}

}
