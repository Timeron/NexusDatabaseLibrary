package com.timeron.NexusDatabaseLibrary.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.timeron.NexusDatabaseLibrary.Entity.JProject;

public class JProjectDAO extends DaoImp<JProject> {

	public JProjectDAO(Class<JProject> persistantClass) {
		super(persistantClass);
	}

	public JProjectDAO() {
		super(JProject.class);
	}
	
	public JProject getByDescription(String description){
		JProject jProject = new JProject();
		session = sessionFactory.openSession();
		session.beginTransaction();
		criteria = session.createCriteria(JProject.class);
		criteria.add(Restrictions.eq("description", description));
		if(criteria.list().size() > 0){
			jProject = (JProject) criteria.list().get(0);
		}else{
			jProject = null;
		}
		session.close();
		return jProject;
	}
	
	public JProject getByName(String name){
		JProject jProject = new JProject();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		criteria = session.createCriteria(JProject.class);
		criteria.add(Restrictions.eq("name", name));
		if(criteria.list().size() > 0){
			jProject = (JProject) criteria.list().get(0);
		}else{
			jProject = null;
		}
		session.close();
		return jProject;
	}
	
}
