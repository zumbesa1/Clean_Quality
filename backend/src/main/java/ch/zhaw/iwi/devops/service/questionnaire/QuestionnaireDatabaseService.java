package ch.zhaw.iwi.devops.service.questionnaire;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import com.google.common.base.Strings;

import ch.zhaw.iwi.devops.model.questionnaire.Questionnaire;
import ch.zhaw.iwi.devops.model.questionnaire.Questionnaire_;
import ch.zhaw.iwi.devops.service.AbstractCrudDatabaseService;
import ch.zhaw.iwi.devops.service.PathListEntry;

public class QuestionnaireDatabaseService extends AbstractCrudDatabaseService<Questionnaire, Long> {

	@Override
	public Class<Questionnaire> getEntityClass() {
		return Questionnaire.class;
	}

	@Override
	protected void orderBy(Root<Questionnaire> root, List<Order> orderList) {
		orderList.add(getCriteriaBuilder().asc(root.get(Questionnaire_.name)));
	}

	@Override
	public void createPathListEntry(Questionnaire entity, PathListEntry<Long> entry) {
		entry.setKey(entity.getKey(), getKeyName());
		entry.setName(entity.getName());
		if (!Strings.isNullOrEmpty(entity.getIcon())) {
			entry.setIcon(entity.getIcon());
		}
	}

	@Override
	protected void beforeDefaultCreate(Questionnaire entity) {
		super.beforeDefaultCreate(entity);

		entity.setPatientRequired(true);
	}

	public class PatientRequiredFilter extends AbstractCrudDatabaseService<Questionnaire, Long>.AbstractListFilter {
		private boolean patientRequired;

		public PatientRequiredFilter(boolean patientRequired) {
			super();
			this.patientRequired = patientRequired;
		}

		@Override
		public void appendFilter(CriteriaQuery<Questionnaire> criteriaQuery, Root<Questionnaire> root) {
			CriteriaBuilder cb = getCriteriaBuilder();
			addPredicate(criteriaQuery, cb.equal(root.get(Questionnaire_.patientRequired), patientRequired));
		}
	}
	
	public class ActiveFilter extends AbstractCrudDatabaseService<Questionnaire, Long>.AbstractListFilter {
		private boolean active;

		public ActiveFilter(boolean active) {
			super();
			this.active = active;
		}

		@Override
		public void appendFilter(CriteriaQuery<Questionnaire> criteriaQuery, Root<Questionnaire> root) {
			CriteriaBuilder cb = getCriteriaBuilder();
			addPredicate(criteriaQuery, cb.equal(root.get(Questionnaire_.active), active));
		}
	}

}
