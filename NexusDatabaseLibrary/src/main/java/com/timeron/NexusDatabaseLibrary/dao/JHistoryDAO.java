package com.timeron.NexusDatabaseLibrary.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.timeron.NexusDatabaseLibrary.Entity.JHistory;
import com.timeron.NexusDatabaseLibrary.Entity.JTask;
import com.timeron.NexusDatabaseLibrary.helper.JpaHelper;

@Repository
public class JHistoryDAO extends DaoImp<JHistory> {

	public JHistoryDAO(){
		super(JHistory.class);
	}
	
	public JHistoryDAO(Class<JHistory> persistantClass) {
		super(persistantClass);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<JHistory> getAllFromTaskId(JTask task) {
		List<JHistory> jHistories;
		Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);
		criteria.add(Restrictions.eq("task", task));
		criteria.addOrder(Order.desc("created"));
		
		if(criteria.list().size() > 0){
			jHistories = (List<JHistory>) criteria.list();
		}else{
			jHistories = Collections.emptyList();
		}
		return jHistories;
	}

}
