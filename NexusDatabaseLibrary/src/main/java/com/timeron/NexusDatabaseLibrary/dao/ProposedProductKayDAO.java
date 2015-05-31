package com.timeron.NexusDatabaseLibrary.dao;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.timeron.NexusDatabaseLibrary.Entity.ObservedObject;
import com.timeron.NexusDatabaseLibrary.Entity.ObservedSite;
import com.timeron.NexusDatabaseLibrary.Entity.ProposedProductKay;
import com.timeron.NexusDatabaseLibrary.helper.JpaHelper;

@Repository
public class ProposedProductKayDAO extends DaoImp<ProposedProductKay>{
	
	public ProposedProductKayDAO() {
		super(ProposedProductKay.class);
	}

	static Logger log = Logger.getLogger(ProposedProductKayDAO.class.getName());
	
//	public void save(ProposedProductKay proposedProductKay) {
//		session = sessionFactory.openSession();
//		session.beginTransaction();
//		session.save(proposedProductKay);
//		session.getTransaction().commit();
//		session.close();
//		log.info("ObservedSite saved");
//		
//	}

	@SuppressWarnings("unchecked")
	public List<ProposedProductKay> getByObservedSiteAndObject(ObservedSite observedSite,
			ObservedObject observedObject) {
		Session session = JpaHelper.createSession(entityManager, persistantClass);
		
		List<ProposedProductKay> proposedProductKay = session.createCriteria(ProposedProductKay.class)
				.add(Restrictions.eq("observedSite", observedSite))
				.add(Restrictions.eq("observedObject", observedObject))
				.list();
		
		if (proposedProductKay.size() > 0) {
			return proposedProductKay;
		} else {
			List<ProposedProductKay> emptyList = Collections.emptyList();
			return emptyList;
		}
	}

	public void update(ProposedProductKay proposedProductKay) {
		Session session = JpaHelper.createSession(entityManager, persistantClass);
		session.update(proposedProductKay);
		session.getTransaction().commit();
		
	}
}
