package com.timeron.NexusDatabaseLibrary.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.timeron.NexusDatabaseLibrary.Entity.NexusPerson;
import com.timeron.NexusDatabaseLibrary.helper.JpaHelper;

@Repository
public class NexusPersonDAO extends DaoImp<NexusPerson> {

	public NexusPersonDAO() {
		super(NexusPerson.class);
	}
	
	public NexusPersonDAO(Class<NexusPerson> persistantClass) {
		super(persistantClass);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public NexusPerson getByNick(String nick){
		List<NexusPerson> users = new ArrayList<NexusPerson>();

		Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);
		criteria.add(Restrictions.eq("nick", nick));
		users = (List<NexusPerson>) criteria.list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}

}
