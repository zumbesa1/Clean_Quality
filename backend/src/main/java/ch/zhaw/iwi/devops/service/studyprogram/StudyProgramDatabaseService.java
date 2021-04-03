package ch.zhaw.iwi.devops.service.studyprogram;

import java.util.List;

import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import ch.zhaw.iwi.devops.model.studyprogram.StudyProgram;
import ch.zhaw.iwi.devops.model.studyprogram.StudyProgram_;
import ch.zhaw.iwi.devops.service.AbstractCrudDatabaseService;
import ch.zhaw.iwi.devops.service.PathListEntry;

public class StudyProgramDatabaseService extends AbstractCrudDatabaseService<StudyProgram, Long> {

	@Override
	public Class<StudyProgram> getEntityClass() {
		return StudyProgram.class;
	}

	@Override
	protected void orderBy(Root<StudyProgram> root, List<Order> orderList) {
		orderList.add(getCriteriaBuilder().asc(root.get(StudyProgram_.name)));
	}

	@Override
	public void createPathListEntry(StudyProgram entity, PathListEntry<Long> entry) {
		entry.setKey(entity.getKey(), getKeyName());
		entry.setName(entity.getName());
		entry.getDetails().add(entity.getDescription());
	}

}
