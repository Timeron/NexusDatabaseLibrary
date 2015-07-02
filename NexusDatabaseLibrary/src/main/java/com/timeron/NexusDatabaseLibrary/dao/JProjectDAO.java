package com.timeron.NexusDatabaseLibrary.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.timeron.NexusDatabaseLibrary.Entity.JProject;
import com.timeron.NexusDatabaseLibrary.helper.JpaHelper;

@Repository
public class JProjectDAO extends DaoImp<JProject> {

	static Logger LOG = Logger.getLogger(JProjectDAO.class);

	public JProjectDAO() {
		super(JProject.class);
	}
	
	@Transactional
	public JProject getByDescription(String description){
		LOG.info("enter getByDescription");
		JProject jProject = new JProject();

		Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);
		criteria.add(Restrictions.eq("description", description));
		if(criteria.list().size() > 0){
			jProject = (JProject) criteria.list().get(0);
		}else{
			jProject = null;
		}

		LOG.info("exit getByDescription");
		return jProject;
	}
	
	@Transactional
	public JProject getByName(String name){
		LOG.info("enter getByName");
		JProject jProject = new JProject();
		Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);
		
		criteria.add(Restrictions.eq("name", name));
		if(criteria.list().size() > 0){
			jProject = (JProject) criteria.list().get(0);
		}else{
			jProject = null;
		}

		LOG.info("exit getByName");
		return jProject;
	}
	
}
