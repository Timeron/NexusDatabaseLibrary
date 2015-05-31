package com.timeron.NexusDatabaseLibrary.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.timeron.NexusDatabaseLibrary.Entity.Site;
import com.timeron.NexusDatabaseLibrary.helper.JpaHelper;

@Repository
public class SiteDAO extends DaoImp<Site>{
	
	public SiteDAO() {
		super(Site.class);
	}

	@Autowired
	ObservedLinksPackageDAO observedLinksPackageDAO;
	@Autowired
	SiteTypeDAO siteTypeDAO;
	
	static Logger log = Logger.getLogger(SiteDAO.class.getName());
	
	@SuppressWarnings("unchecked")
	public List<Site> getByName(String name) {
		List<Site> sites = new ArrayList<Site>();
		Session session = JpaHelper.createSession(entityManager, persistantClass);
		String hql = "FROM Site WHERE name ='"+name+"'";
		
		
		Query query = session.createQuery(hql);
		sites = (List<Site>) query.list();
		
		if (sites.size() > 0) {
			return sites;
		} else {
			List<Site> emptyList = Collections.emptyList();
			return emptyList;
		}
		
	}
	
}
