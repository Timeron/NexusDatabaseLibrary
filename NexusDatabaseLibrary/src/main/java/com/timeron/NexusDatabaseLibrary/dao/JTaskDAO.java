package com.timeron.NexusDatabaseLibrary.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.timeron.NexusDatabaseLibrary.Entity.JProject;
import com.timeron.NexusDatabaseLibrary.Entity.JTask;
import com.timeron.NexusDatabaseLibrary.dao.Enum.Direction;
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
	
	@Transactional
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

	@SuppressWarnings("unchecked")
	@Transactional
	public List<JTask> getByProjectId(Integer projectId, String orderBy, String direction, int maxResults) {
		LOG.info("enter getByProjectId");
		List<JTask> jTasks;
		Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);
		
		criteria.add(Restrictions.eq("project", projectId));
		if(direction != Direction.NONE){
			if(direction == Direction.ASC){
				criteria.addOrder(Order.asc(orderBy));
			}else{
				criteria.addOrder(Order.desc(orderBy));
			}
		}
		if(maxResults > 0){
			criteria.setMaxResults(maxResults);
		}
		
		jTasks = (List<JTask>) criteria.list();
		
		LOG.info("exit getByProjectId");
		return jTasks;
		
		
	}
	
	@Transactional
	public String getLastName(JProject project){
		LOG.info("enter getLastId");
		JTask entity = null;
		String lastName = "";
		try {
			Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);
			criteria.add(Restrictions.eq("project", project));
			criteria.addOrder(Order.desc("id"));
			criteria.setMaxResults(1);
			if(criteria.list().size() > 0){
				entity = (JTask) criteria.list().get(0);
				lastName = entity.getName();
			}else{
				lastName = "";
			}
		} catch (HibernateException ex) {
			ex.printStackTrace();
		}finally {

		}
		LOG.info("exit getLastId");
		return lastName;
	}
	


}
