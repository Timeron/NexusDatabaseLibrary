package com.timeron.NexusDatabaseLibrary.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.timeron.NexusDatabaseLibrary.Entity.ObservedSite;
import com.timeron.NexusDatabaseLibrary.helper.JpaHelper;

@Repository
public class ObservedSiteDAO extends DaoImp<ObservedSite> {

	public ObservedSiteDAO() {
		super(ObservedSite.class);
	}

	static Logger log = Logger.getLogger(ObservedSiteDAO.class.getName());

	public boolean checkIfIdExist(ObservedSite observedSite) {
		ObservedSite observedSiteDB;
		Session session = JpaHelper.createSession(entityManager, persistantClass);
		observedSiteDB = (ObservedSite) session.get(ObservedSite.class,
				observedSite.getId());
		return observedSiteDB.getId() != null ? true : false;
	}

	@SuppressWarnings("unchecked")
	public boolean checkIfHashExist(ObservedSite observedSite) {
		List<ObservedSite> observedSites;
		Session session = JpaHelper.createSession(entityManager, persistantClass);
		String hql = "FROM ObservedSite WHERE hashUrl = '"
				+ observedSite.getHashUrl() + "'";
		Query query = session.createQuery(hql);
		observedSites = (List<ObservedSite>) query.list();

		if (observedSites.isEmpty()) {
			return false;
		} else {
			if (observedSites.get(0).getUrl().equals(observedSite.getUrl())) {
				return true;
			} else {
				return false;
			}
		}
	}

	@SuppressWarnings("unchecked")
	public ObservedSite getEntity(ObservedSite observedSite) {
		List<ObservedSite> observedSites;
		Session session = JpaHelper.createSession(entityManager, persistantClass);
		String hql = "FROM ObservedSite WHERE hashUrl = '"
				+ observedSite.getHashUrl() + "'";
		Query query = session.createQuery(hql);
		observedSites = (List<ObservedSite>) query.list();

		return observedSites.get(0);
	}

	@SuppressWarnings("unchecked")
	public Integer getId(ObservedSite observedSite) {
		List<ObservedSite> observedSites;
		Session session = JpaHelper.createSession(entityManager, persistantClass);
		String hql = "FROM ObservedSite WHERE hashUrl = '"
				+ observedSite.getHashUrl() + "'";
		Query query = session.createQuery(hql);
		observedSites = (List<ObservedSite>) query.list();
		return observedSites.get(0).getId();
	}

	@SuppressWarnings("unchecked")
	public boolean siteWasAddedDoday(ObservedSite observedSite) {
		List<ObservedSite> observedSites;

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		Date today = new Date(cal.getTimeInMillis());

		Session session = JpaHelper.createSession(entityManager, persistantClass);
		String hql = "FROM ObservedSite WHERE hashUrl = '"
				+ observedSite.getHashUrl() + "'";
		Query query = session.createQuery(hql);
		observedSites = (List<ObservedSite>) query.list();

		if (observedSites.get(0).getTimestamp().after(today)) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ObservedSite> getSitesWithoutProductKay()
			throws SessionException {
		log.info("session");
		List<ObservedSite> observedSite = null;

		Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);

		try {
			criteria.add(Restrictions.isNull("observedObject"));
			observedSite = criteria.list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
		}

		return observedSite;

	}

	@SuppressWarnings("unchecked")
	public List<ObservedSite> search(ObservedSite searchParameters) {
		List<ObservedSite> result = null;

		Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);
		if(searchParameters.getId()!=null){
			criteria.add(Restrictions.idEq(searchParameters.getId()));
		}
		if(!searchParameters.getArticleName().isEmpty() && searchParameters.getArticleName()!=null){
			if(searchParameters.getArticleName().contains("%")){
				criteria.add(Restrictions.ilike("articleName", searchParameters.getArticleName()));
			}
			criteria.add(Restrictions.ilike("articleName", "%"+searchParameters.getArticleName().trim()+"%"));
		}
		if(!searchParameters.getUrl().isEmpty() && searchParameters.getUrl()!=null){
			if(searchParameters.getUrl().contains("%")){
				criteria.add(Restrictions.ilike("url", searchParameters.getArticleName()));
			}
			criteria.add(Restrictions.ilike("url", searchParameters.getUrl().trim()));
		}
		if(searchParameters.isApprovedProductKay()){
			
			criteria.add(Restrictions.eq("approvedProductKay", true));
		}
		result = criteria.list();
		
		return result;
	}
}
