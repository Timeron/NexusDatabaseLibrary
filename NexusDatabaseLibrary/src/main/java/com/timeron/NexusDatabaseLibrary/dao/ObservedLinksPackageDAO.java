package com.timeron.NexusDatabaseLibrary.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.timeron.NexusDatabaseLibrary.Entity.ObservedLinksPackage;
import com.timeron.NexusDatabaseLibrary.Entity.Site;
import com.timeron.NexusDatabaseLibrary.helper.JpaHelper;

@Repository
public class ObservedLinksPackageDAO extends DaoImp<ObservedLinksPackage>{

	public ObservedLinksPackageDAO() {
		super(ObservedLinksPackage.class);
	}
	
	static Logger log = Logger.getLogger(SiteTypeDAO.class.getName());

	@SuppressWarnings("unchecked")
	public List<ObservedLinksPackage> getByUrl(String url) {
		List<ObservedLinksPackage> observedLinksPackage = new ArrayList<ObservedLinksPackage>();
		Session session = JpaHelper.createSession(entityManager, persistantClass);
		String hql = "FROM ObservedLinksPackage WHERE url = '"+url+"'";
		
		Query query = session.createQuery(hql);
		observedLinksPackage = (List<ObservedLinksPackage>) query.list();
		
		
		if (observedLinksPackage.size() > 0) {
			return observedLinksPackage;
		} else {
			List<ObservedLinksPackage> emptyList = Collections.emptyList();
			return emptyList;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ObservedLinksPackage> getLinksByShopId(Site site) {
		List<ObservedLinksPackage> observedLinksPackage = new ArrayList<ObservedLinksPackage>();
		Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);
		criteria.add(Restrictions.eq("site", site));
		observedLinksPackage = criteria.list();
		return observedLinksPackage;
	}

}
