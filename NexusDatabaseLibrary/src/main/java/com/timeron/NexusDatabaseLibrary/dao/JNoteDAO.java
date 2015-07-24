package com.timeron.NexusDatabaseLibrary.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.timeron.NexusDatabaseLibrary.Entity.JNote;
import com.timeron.NexusDatabaseLibrary.Entity.JTask;
import com.timeron.NexusDatabaseLibrary.helper.JpaHelper;

@Repository
public class JNoteDAO extends DaoImp<JNote> {

	public JNoteDAO() {
		super(JNote.class);
	}

	public JNoteDAO(Class<JNote> persistantClass) {
		super(persistantClass);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<JNote> getAllFromTaskId(JTask task) {
		List<JNote> result;
		Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);
		criteria.add(Restrictions.eq("task", task));
		criteria.addOrder(Order.desc("created"));

		if (criteria.list().size() > 0) {
			result = (List<JNote>) criteria.list();
		} else {
			result = Collections.emptyList();
		}
		return result;
	}

}
