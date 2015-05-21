package com.timeron.NexusDatabaseLibrary.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.timeron.NexusDatabaseLibrary.Entity.JProject;
import com.timeron.NexusDatabaseLibrary.Entity.JTask;

public class JTaskDAO extends DaoImp<JTask> {

	public JTaskDAO(Class<JTask> persistantClass) {
		super(persistantClass);
	}
	
	public JTaskDAO() {
		super(JTask.class);
	}

	@SuppressWarnings("unchecked")
	public List<JTask> getByProject(JProject jProject) {
		List<JTask> jTasks;
		session = sessionFactory.openSession();
		session.beginTransaction();
		criteria = session.createCriteria(JTask.class);
		criteria.add(Restrictions.eq("project", jProject));
		jTasks = (List<JTask>) criteria.list();
		session.close();
		return jTasks;
	}
	
	@SuppressWarnings("unchecked")
	public List<JTask> getByProjectId(int projectId) {
		List<JTask> jTasks;
		session = sessionFactory.openSession();
		session.beginTransaction();
		criteria = session.createCriteria(JTask.class);
		criteria.add(Restrictions.eq("project", projectId));
		jTasks = (List<JTask>) criteria.list();
		session.close();
		return jTasks;
	}

}
