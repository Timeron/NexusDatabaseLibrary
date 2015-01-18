package com.timeron.NexusDatabaseLibrary.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.timeron.NexusDatabaseLibrary.Entity.NexusPerson;
import com.timeron.NexusDatabaseLibrary.dao.helper.PersonDaoHelper;

@Repository
public class PersonDAO extends DaoImp<NexusPerson>{

	public PersonDAO(Class<NexusPerson> persistantClass) {
		super(persistantClass);
		// TODO Auto-generated constructor stub
	}

	static Logger log = Logger.getLogger(PersonDAO.class.getName());

	@SuppressWarnings("unchecked")
	public List<NexusPerson> searchPerson(NexusPerson nexusPerson) {
		List<NexusPerson> nexusPersonList = new ArrayList<NexusPerson>();

		PersonDaoHelper personDaoHelper = new PersonDaoHelper();

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery(personDaoHelper
				.buildQuerySearchPerson(nexusPerson));
		nexusPersonList = (List<NexusPerson>) query.list();
		session.close();
		if (nexusPersonList.size() > 0) {
			return nexusPersonList;
		} else {
			List<NexusPerson> emptyList = Collections.emptyList();
			return emptyList;
		}

	}

	@SuppressWarnings("unchecked")
	public List<NexusPerson> getAllContacts() {
		List<NexusPerson> nexusPersonList = new ArrayList<NexusPerson>();
		PersonDaoHelper personDaoHelper = new PersonDaoHelper();
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery(personDaoHelper
				.buildGetAllPerson());
		nexusPersonList = (List<NexusPerson>) query.list();
		
		session.close();
		
		if (nexusPersonList.size() > 0) {
			return nexusPersonList;
		} else {
			List<NexusPerson> emptyList = Collections.emptyList();
			return emptyList;
		}
	}
}
