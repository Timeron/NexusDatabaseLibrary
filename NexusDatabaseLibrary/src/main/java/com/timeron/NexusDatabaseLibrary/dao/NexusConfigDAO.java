package com.timeron.NexusDatabaseLibrary.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.timeron.NexusDatabaseLibrary.Entity.NexusConfig;
import com.timeron.NexusDatabaseLibrary.dao.Enum.Config;

public class NexusConfigDAO extends DaoImp<NexusConfigDAO>{

	public NexusConfigDAO() {
		super(NexusConfigDAO.class);
	}
	
	public NexusConfigDAO(Class<NexusConfigDAO> persistantClass) {
		super(persistantClass);
	}

	public NexusConfig getParametr(String config) {
		NexusConfig result;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		criteria = session.createCriteria(NexusConfig.class);
		criteria.add(Restrictions.eq("name", config));
		criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);
		result = (NexusConfig) criteria.list().get(0);
		session.close();
		return result;
	}
	
	public void done(){
		NexusConfig result;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		criteria = session.createCriteria(NexusConfig.class);
		criteria.add(Restrictions.eq("name", Config.WALLET_UPLOADER_RUN));
		criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);
		result = (NexusConfig) criteria.list().get(0);
		result.setValue("false");
		session.update(result);
		session.getTransaction().commit();
		session.close();
	}

	
	
}
