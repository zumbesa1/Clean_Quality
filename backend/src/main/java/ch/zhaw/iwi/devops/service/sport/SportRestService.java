package ch.zhaw.iwi.devops.service.sport;

import static spark.Spark.get;

import java.util.ArrayList;
import java.util.List;

import com.google.common.primitives.Longs;
import com.google.inject.Inject;
import com.google.inject.Injector;

import ch.zhaw.iwi.devops.model.patient.Patient;
import ch.zhaw.iwi.devops.model.sport.Sport;
import ch.zhaw.iwi.devops.model.user.User;
import ch.zhaw.iwi.devops.service.AbstractCrudRestService;
import ch.zhaw.iwi.devops.service.PathListEntry;
import ch.zhaw.iwi.devops.service.patient.PatientDatabaseService;

public class SportRestService extends AbstractCrudRestService<User, Long, SportDatabaseService> {

	@Inject
	public SportRestService(Injector injector) {
		super(injector, SportDatabaseService.class);
	}

	@Inject
	PatientDatabaseService patientDatabaseService;
	
	@Override
	protected void initGet() {
		super.initGet();

		// Sport list for patient
		get("services/patient/:patientKey/sport", (req, res) -> {
			Long patientKey = Longs.tryParse(req.params("patientKey"));
			Patient patient = patientDatabaseService.read(patientKey);
			List<PathListEntry<Long>> result = new ArrayList<>();
			result.addAll(getCrudDatabaseService().createPathList(getCrudDatabaseService().list()));
			for (PathListEntry<Long> entry : result) {
				Sport sport = getCrudDatabaseService().read(entry.getKey().getKey());
				if (patient.getSports().contains(sport)) {
					entry.setColor("devops-green");
				}
				entry.setUrl("/patient/" + patientKey + "/sport/" + entry.getKey().getKey() + "/toggle");
			}
			return result;
		}, getJsonTransformer());

		// Toggle
		get("services/patient/:patientKey/sport/:sportKey/toggle", (req, res) -> {
			Long patientKey = Longs.tryParse(req.params("patientKey"));
			Long sportKey = Longs.tryParse(req.params("sportKey"));
			Patient patient = patientDatabaseService.read(patientKey);
			Sport sport = getCrudDatabaseService().read(sportKey);
			if (patient.getSports().contains(sport)) {
				patient.getSports().remove(sport);
			} else {
				patient.getSports().add(sport);
			}
			return patientDatabaseService.update(patient);
		}, getJsonTransformer());
	}

}