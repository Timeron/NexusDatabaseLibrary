package com.timeron.NexusDatabaseLibrary.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.timeron.NexusDatabaseLibrary.Entity.JProject;
import com.timeron.NexusDatabaseLibrary.Entity.JTask;
import com.timeron.NexusDatabaseLibrary.helper.JpaHelper;

@Repository
public class JTaskDAO extends DaoImp<JTask> {

	static Logger LOG = Logger.getLogger(JTaskDAO.class);
	
	public JTaskDAO() {
		super(JTask.class);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<JTask> getByProject(JProject jProject) {
		LOG.info("enter getByProject");
		List<JTask> jTasks;
		Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);
		
		criteria.add(Restrictions.eq("project", jProject));
		jTasks = (List<JTask>) criteria.list();
		
		LOG.info("exit getByProject");
		return jTasks;
	}
	
	@SuppressWarnings("unchecked")
	public List<JTask> getByProjectId(int projectId) {
		LOG.info("enter getByProjectId");
		List<JTask> jTasks;
		Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);
		
		criteria.add(Restrictions.eq("project", projectId));
		jTasks = (List<JTask>) criteria.list();
		
		LOG.info("exit getByProjectId");
		return jTasks;
	}

}
