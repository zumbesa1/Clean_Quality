package ch.zhaw.iwi.devops.service.patient;

import static spark.Spark.get;
import static spark.Spark.put;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Injector;

import ch.zhaw.iwi.devops.model.patient.Patient;
import ch.zhaw.iwi.devops.model.user.User;
import ch.zhaw.iwi.devops.service.AbstractCrudRestService;
import ch.zhaw.iwi.devops.service.PathListEntry;

public class PatientRestService extends AbstractCrudRestService<User, Long, PatientDatabaseService> {

	@Inject
	public PatientRestService(Injector injector) {
		super(injector, PatientDatabaseService.class);
	}

	@Override
	protected void initPut() {
		super.initPut();

		get("services/newpatient/1", (req, res) -> {
			return "[{ \"key\": 1, \"name\": \"situationKey\" }]";
		});

		get("services/patientstaticurl", (req, res) -> {
			List<PathListEntry<Long>> results = getCrudDatabaseService().createPathList(getCrudDatabaseService().list());
			for (PathListEntry<Long> result : results) {
				result.setUrl("https://www.20min.ch");
				result.setType("linkButton");
			}
			return results;
		}, getJsonTransformer());

		put("services/newpatient/1", (req, res) -> {
			Patient patient = fromJson(req.body(), Patient.class);
			return getCrudDatabaseService().create(patient);
		}, getJsonTransformer());
	}

}