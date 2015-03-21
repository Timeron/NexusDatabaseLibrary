package com.timeron.NexusDatabaseLibrary.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.timeron.NexusDatabaseLibrary.Entity.ObservedSite;

@Repository
public class ObservedSiteDAO extends DaoImp<ObservedSite> {

	public ObservedSiteDAO() {
		super(ObservedSite.class);
	}
	
	public ObservedSiteDAO(Class<ObservedSite> persistantClass) {
		super(persistantClass);
	}

	static Logger log = Logger.getLogger(ObservedSiteDAO.class.getName());

	public boolean checkIfIdExist(ObservedSite observedSite) {
		ObservedSite observedSiteDB;
		session = sessionFactory.openSession();
		session.beginTransaction();
		observedSiteDB = (ObservedSite) session.get(ObservedSite.class,
				observedSite.getId());
		session.close();
		return observedSiteDB.getId() != null ? true : false;
	}

	@SuppressWarnings("unchecked")
	public boolean checkIfHashExist(ObservedSite observedSite) {
		List<ObservedSite> observedSites;
		session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "FROM ObservedSite WHERE hashUrl = '"
				+ observedSite.getHashUrl() + "'";
		Query query = session.createQuery(hql);
		observedSites = (List<ObservedSite>) query.list();
		session.close();

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
		session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "FROM ObservedSite WHERE hashUrl = '"
				+ observedSite.getHashUrl() + "'";
		Query query = session.createQuery(hql);
		observedSites = (List<ObservedSite>) query.list();
		session.close();

		return observedSites.get(0);
	}

	@SuppressWarnings("unchecked")
	public Integer getId(ObservedSite observedSite) {
		List<ObservedSite> observedSites;
		session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "FROM ObservedSite WHERE hashUrl = '"
				+ observedSite.getHashUrl() + "'";
		Query query = session.createQuery(hql);
		observedSites = (List<ObservedSite>) query.list();
		session.close();
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

		session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "FROM ObservedSite WHERE hashUrl = '"
				+ observedSite.getHashUrl() + "'";
		Query query = session.createQuery(hql);
		observedSites = (List<ObservedSite>) query.list();
		session.close();

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

		session = sessionFactory.openSession();
		session.beginTransaction();

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			criteria = session.createCriteria(ObservedSite.class);
			criteria.add(Restrictions.isNull("observedObject"));
			observedSite = criteria.list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return observedSite;

	}

	@SuppressWarnings("unchecked")
	public List<ObservedSite> search(ObservedSite searchParameters) {
		List<ObservedSite> result = null;

		session = sessionFactory.openSession();
		session.beginTransaction();
		
		criteria = session.createCriteria(ObservedSite.class);
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
		session.close();
		
		return result;
	}
}
