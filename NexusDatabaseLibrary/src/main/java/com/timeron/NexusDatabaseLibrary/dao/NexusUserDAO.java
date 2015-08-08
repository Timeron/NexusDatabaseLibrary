package com.timeron.NexusDatabaseLibrary.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.timeron.NexusDatabaseLibrary.Entity.NexusUserRole;
import com.timeron.NexusDatabaseLibrary.Entity.NexusUser;
import com.timeron.NexusDatabaseLibrary.helper.JpaHelper;

@Repository
public class NexusUserDAO extends DaoImp<NexusUser>{
	
	public NexusUserDAO(Class<NexusUser> persistantClass) {
		super(persistantClass);
	}
	
	public NexusUserDAO() {
		super(NexusUser.class);
	}

	@SuppressWarnings("unchecked")
	public NexusUser findByUserName(String username) {

		List<NexusUser> users = new ArrayList<NexusUser>();

		Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);
		
		criteria.add(Restrictions.eq("username", username));
		
		users = (List<NexusUser>) criteria.list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}
}
