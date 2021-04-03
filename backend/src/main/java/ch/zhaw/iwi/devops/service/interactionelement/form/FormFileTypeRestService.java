package ch.zhaw.iwi.devops.service.interactionelement.form;

import static spark.Spark.get;

import java.util.List;
import java.util.TreeSet;

import com.google.common.primitives.Longs;
import com.google.inject.Inject;
import com.google.inject.Injector;

import ch.zhaw.iwi.devops.model.interactionelement.form.FormFileType;
import ch.zhaw.iwi.devops.service.AbstractCrudRestService;
import ch.zhaw.iwi.devops.service.PathListEntry;

public class FormFileTypeRestService extends AbstractCrudRestService<FormFileType, Long, FormFileTypeDatabaseService> {

	@Inject
	public FormFileTypeRestService(Injector injector) {
		super(injector, FormFileTypeDatabaseService.class);
	}
	
	@Inject
	FormFileUploadElementDatabaseService formFileUploadElementDatabaseService;
	
	@Override
	protected void initGet() {
		super.initGet();
		
		get("services/formFileUploadElement/:interactionElementKey/formFileType", (req, res) -> {
			Long interactionElementKey = Longs.tryParse(req.params("interactionElementKey"));
			List<PathListEntry<Long>> resultList = getCrudDatabaseService().createPathList(new TreeSet<>(formFileUploadElementDatabaseService.read(interactionElementKey).getAcceptedFileTypes()));
			return resultList;
		}, getJsonTransformer());
	}

}
