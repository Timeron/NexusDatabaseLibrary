package com.timeron.NexusDatabaseLibrary.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.timeron.NexusDatabaseLibrary.Entity.ObservedObject;

@Repository
public class ObservedObjectDAO extends DaoImp<ObservedObject>{

	public ObservedObjectDAO() {
		super(ObservedObject.class);
	}
	
	public ObservedObjectDAO(Class<ObservedObject> persistantClass) {
		super(persistantClass);
	}

	static Logger log = Logger.getLogger(ObservedSiteDAO.class.getName());

	@SuppressWarnings("unchecked")
	public int getId(ObservedObject observedObject) {
		List<ObservedObject> observedObjects;
		session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "FROM observedObject WHERE productKay = '"+observedObject.getProductKay()+"'";
		Query query = session.createQuery(hql);
		observedObjects = (List<ObservedObject>) query.list();
		session.close();
		return observedObjects.get(0).getId();
	}



	@SuppressWarnings("unchecked")
	public List<ObservedObject> search(ObservedObject searchParameters) {
		List<ObservedObject> observedObjects = null;
		
		session = sessionFactory.openSession();
		session.beginTransaction();
		criteria = session.createCriteria(ObservedObject.class);
		if(searchParameters.getId()!=null){
			criteria.add(Restrictions.idEq(searchParameters.getId()));
		}
		if(!searchParameters.getName().isEmpty() && searchParameters.getName()!=null){
			criteria.add(Restrictions.like("name", searchParameters.getName().trim()));
		}
		if(!searchParameters.getProductKay().isEmpty() && searchParameters.getProductKay()!=null){
			criteria.add(Restrictions.like("productKay", searchParameters.getProductKay().trim()));
		}
		observedObjects = criteria.list();
		session.close();
		return observedObjects;
	}

}