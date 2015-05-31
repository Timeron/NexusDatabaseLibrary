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

import com.timeron.NexusDatabaseLibrary.Entity.ObservedSite;
import com.timeron.NexusDatabaseLibrary.Entity.ObservedSiteHistory;
import com.timeron.NexusDatabaseLibrary.helper.JpaHelper;

@Repository
public class ObservedSiteHistoryDAO extends DaoImp<ObservedSiteHistory>{
	
	public ObservedSiteHistoryDAO() {
		super(ObservedSiteHistory.class);
	}

	static Logger log = Logger.getLogger(ObservedSiteHistoryDAO.class.getName());
	
	@SuppressWarnings("unchecked")
	public List<ObservedSiteHistory> getByObservedSite(ObservedSite observedSite) {
		List<ObservedSiteHistory> result = new ArrayList<ObservedSiteHistory>();
		Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);
		criteria.add(Restrictions.eq("observedSite", observedSite));
		result = criteria.list();

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
		Session session = JpaHelper.createSession(entityManager, persistantClass);
		String hql = "FROM ObservedSiteHistory WHERE observedSite = '"+newObservedSiteHistory.getObservedSite().getId()+"' ORDER BY timestamp DESC";
		Query query = session.createQuery(hql);
		query.setMaxResults(1);
		observedSiteHistories = (List<ObservedSiteHistory>) query.list();
		
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
