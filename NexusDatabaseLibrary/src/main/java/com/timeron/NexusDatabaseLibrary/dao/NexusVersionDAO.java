package com.timeron.NexusDatabaseLibrary.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.timeron.NexusDatabaseLibrary.Entity.NexusVersion;
import com.timeron.NexusDatabaseLibrary.helper.JpaHelper;

@Repository
public class NexusVersionDAO extends DaoImp<NexusVersion>{

	public NexusVersionDAO() {
		super(NexusVersion.class);
	}
	
	public NexusVersionDAO(Class<NexusVersion> persistantClass) {
		super(persistantClass);
	}
	
	@Transactional
	public NexusVersion getByName(String name){
		NexusVersion entity = null;
		try {
			Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);
			
			criteria.add(Restrictions.eq("app", name));
			if(criteria.list().size()>0){
				entity = (NexusVersion) criteria.list().get(0);
			}else{
				entity = null;
			}
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {

		}
		return entity;
	}
	
}
