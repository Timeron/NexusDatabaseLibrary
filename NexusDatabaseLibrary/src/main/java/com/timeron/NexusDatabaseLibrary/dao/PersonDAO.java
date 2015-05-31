package com.timeron.NexusDatabaseLibrary.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.timeron.NexusDatabaseLibrary.Entity.NexusPerson;
import com.timeron.NexusDatabaseLibrary.dao.helper.PersonDaoHelper;
import com.timeron.NexusDatabaseLibrary.helper.JpaHelper;

@Repository
public class PersonDAO extends DaoImp<NexusPerson>{

	public PersonDAO() {
		super(NexusPerson.class);
	}

	static Logger log = Logger.getLogger(PersonDAO.class.getName());

	@SuppressWarnings("unchecked")
	public List<NexusPerson> searchPerson(NexusPerson nexusPerson) {
		List<NexusPerson> nexusPersonList = new ArrayList<NexusPerson>();

		PersonDaoHelper personDaoHelper = new PersonDaoHelper();

		Session session = JpaHelper.createSession(entityManager, persistantClass);

		Query query = session.createQuery(personDaoHelper
				.buildQuerySearchPerson(nexusPerson));
		nexusPersonList = (List<NexusPerson>) query.list();
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
		
		Session session = JpaHelper.createSession(entityManager, persistantClass);
		
		Query query = session.createQuery(personDaoHelper
				.buildGetAllPerson());
		nexusPersonList = (List<NexusPerson>) query.list();
		
		if (nexusPersonList.size() > 0) {
			return nexusPersonList;
		} else {
			List<NexusPerson> emptyList = Collections.emptyList();
			return emptyList;
		}
	}
}
