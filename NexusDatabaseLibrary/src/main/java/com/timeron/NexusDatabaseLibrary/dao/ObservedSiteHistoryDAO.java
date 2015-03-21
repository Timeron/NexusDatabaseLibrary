package com.timeron.NexusDatabaseLibrary.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateJdbcException;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;

import com.timeron.NexusDatabaseLibrary.Entity.ObservedSite;
import com.timeron.NexusDatabaseLibrary.Entity.ObservedSiteHistory;

@Repository
public class ObservedSiteHistoryDAO extends DaoImp<ObservedSiteHistory>{
	
	public ObservedSiteHistoryDAO() {
		super(ObservedSiteHistory.class);
	}
	
	public ObservedSiteHistoryDAO(Class<ObservedSiteHistory> persistantClass) {
		super(persistantClass);
	}

	static Logger log = Logger.getLogger(ObservedSiteHistoryDAO.class.getName());
	
	@SuppressWarnings("unchecked")
	public List<ObservedSiteHistory> getByObservedSite(ObservedSite observedSite) throws HibernateJdbcException {
		List<ObservedSiteHistory> result = new ArrayList<ObservedSiteHistory>();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		criteria = session.createCriteria(ObservedSiteHistory.class);
		criteria.add(Restrictions.eq("observedSite", observedSite));
		result = criteria.list();
		
		session.close();

		if (result.size() > 0) {
			return result;
		} else {
			List<ObservedSiteHistory> emptyList = Collections.emptyList();
			return emptyList;
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean priceChanged(ObservedSiteHistory newObservedSiteHistory) {
		List<ObservedSiteHistory> observedSiteHistories;
		session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "FROM ObservedSiteHistory WHERE observedSite = '"+newObservedSiteHistory.getObservedSite().getId()+"' ORDER BY timestamp DESC";
		Query query = session.createQuery(hql);
		query.setMaxResults(1);
		observedSiteHistories = (List<ObservedSiteHistory>) query.list();
		session.close();
		
		if(!observedSiteHistories.isEmpty()){
			if(newObservedSiteHistory.getPrice() != observedSiteHistories.get(0).getPrice() || newObservedSiteHistory.getOldPrice() != observedSiteHistories.get(0).getOldPrice()){
				return true;
			}else{
				return false;
			}
		}else{
			return true;
		}
		
	}

}
