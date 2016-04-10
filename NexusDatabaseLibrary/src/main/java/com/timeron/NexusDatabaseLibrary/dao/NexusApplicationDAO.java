package com.timeron.NexusDatabaseLibrary.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.timeron.NexusDatabaseLibrary.Entity.NexusApplication;
import com.timeron.NexusDatabaseLibrary.helper.JpaHelper;

@Repository
public class NexusApplicationDAO extends DaoImp<NexusApplication>{

	public NexusApplicationDAO() {
		super(NexusApplication.class);
	}
	
	public NexusApplicationDAO(Class<NexusApplication> persistantClass) {
		super(persistantClass);
	}

	@Transactional
	public NexusApplication getByName(String appName) {
		NexusApplication app = new NexusApplication();
		Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);
		criteria.add(Restrictions.eq("applicationName", appName));
		app = (NexusApplication) criteria.list().get(0);

		return app;
	}

	@Transactional
	public NexusApplication getByKey(String appKey) {
		NexusApplication app = new NexusApplication();
		Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);
		criteria.add(Restrictions.eq("appKey", appKey));
		app = (NexusApplication) criteria.list().get(0);

		return app;
	}

}
