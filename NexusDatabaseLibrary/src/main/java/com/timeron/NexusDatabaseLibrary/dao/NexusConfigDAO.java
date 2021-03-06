package com.timeron.NexusDatabaseLibrary.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.timeron.NexusDatabaseLibrary.Entity.NexusConfig;
import com.timeron.NexusDatabaseLibrary.dao.Enum.Config;
import com.timeron.NexusDatabaseLibrary.helper.JpaHelper;

@Repository
public class NexusConfigDAO extends DaoImp<NexusConfig>{

	public NexusConfigDAO() {
		super(NexusConfig.class);
	}
	
	@Transactional
	public NexusConfig getParametr(String config) {
		Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);
		criteria.add(Restrictions.eq("name", config));
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return (NexusConfig) criteria.list().get(0);
	}

//	@SuppressWarnings("static-access")
//	public NexusConfig getParametr(String config) {
//		NexusConfig result;
//		Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);
//		criteria.add(Restrictions.eq("name", config));
//		criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);
//		result = (NexusConfig) criteria.list().get(0);
//		return result;
//	}
	
	@SuppressWarnings("static-access")
	public void done(){
		NexusConfig result;
		Session session = JpaHelper.createSession(entityManager, persistantClass);
		Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);
		criteria.add(Restrictions.eq("name", Config.WALLET_UPLOADER_RUN));
		criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);
		result = (NexusConfig) criteria.list().get(0);
		result.setValue("false");
		session.update(result);
		session.getTransaction().commit();
	}

	
	
}
