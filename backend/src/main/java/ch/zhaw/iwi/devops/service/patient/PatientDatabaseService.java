package ch.zhaw.iwi.devops.service.patient;

import java.util.List;

import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import ch.zhaw.iwi.devops.model.patient.Patient;
import ch.zhaw.iwi.devops.model.patient.Patient_;
import ch.zhaw.iwi.devops.service.AbstractCrudDatabaseService;
import ch.zhaw.iwi.devops.service.PathListEntry;

public class PatientDatabaseService extends AbstractCrudDatabaseService<Patient, Long> {

	@Override
	public Class<Patient> getEntityClass() {
		return Patient.class;
	}

	@Override
	protected void orderBy(Root<Patient> root, List<Order> orderList) {
		orderList.add(getCriteriaBuilder().asc(root.get(Patient_.lastName)));
		orderList.add(getCriteriaBuilder().asc(root.get(Patient_.firstName)));
	}

	@Override
	public void createPathListEntry(Patient entity, PathListEntry<Long> entry) {
		entry.setKey(entity.getKey(), getKeyName());
		entry.setName(entity.getName());
		entry.getDetails().add(entity.getEmail());
	}

}
