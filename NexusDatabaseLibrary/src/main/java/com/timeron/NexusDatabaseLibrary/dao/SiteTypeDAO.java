package com.timeron.NexusDatabaseLibrary.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.timeron.NexusDatabaseLibrary.Entity.SiteType;

@Repository
public class SiteTypeDAO extends DaoImp<SiteType>{

	public SiteTypeDAO(Class<SiteType> persistantClass) {
		super(persistantClass);
		// TODO Auto-generated constructor stub
	}

	static Logger log = Logger.getLogger(SiteTypeDAO.class.getName());

	@SuppressWarnings("unchecked")
	public SiteType getByDescription(String description) {
		List<SiteType> siteType = new ArrayList<SiteType>();
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "FROM SiteType WHERE description = '"+description+"'";
		
		Query query = session.createQuery(hql);
		siteType = (List<SiteType>) query.list();
		session.close();
		if(siteType.isEmpty()){
			return new SiteType();
		}else{
			return siteType.get(0);
		}
	}
	
}
