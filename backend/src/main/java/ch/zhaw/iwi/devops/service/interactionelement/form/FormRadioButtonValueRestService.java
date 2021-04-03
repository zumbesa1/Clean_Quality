package ch.zhaw.iwi.devops.service.interactionelement.form;

import static spark.Spark.get;

import java.util.List;
import java.util.TreeSet;

import com.google.common.primitives.Longs;
import com.google.inject.Inject;
import com.google.inject.Injector;

import ch.zhaw.iwi.devops.model.interactionelement.form.FormRadioButtonValue;
import ch.zhaw.iwi.devops.service.AbstractCrudRestService;
import ch.zhaw.iwi.devops.service.PathListEntry;

public class FormRadioButtonValueRestService extends AbstractCrudRestService<FormRadioButtonValue, Long, FormRadioButtonValueDatabaseService> {

	@Inject
	public FormRadioButtonValueRestService(Injector injector) {
		super(injector, FormRadioButtonValueDatabaseService.class);
	}
	
	@Inject
	FormRadioButtonListElementDatabaseService formRadioButtonListElementDatabaseService;
	
	@Override
	protected void initGet() {
		super.initGet();
		
		get("services/formRadioButtonListElement/:interactionElementKey/formRadioButtonValue", (req, res) -> {
			Long interactionElementKey = Longs.tryParse(req.params("interactionElementKey"));
			List<PathListEntry<Long>> resultList = getCrudDatabaseService().createPathList(new TreeSet<>(formRadioButtonListElementDatabaseService.read(interactionElementKey).getRadios()));
			return resultList;
		}, getJsonTransformer());
	}

}
