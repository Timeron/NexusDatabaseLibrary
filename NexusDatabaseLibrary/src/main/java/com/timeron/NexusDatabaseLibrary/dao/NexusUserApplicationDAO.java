package com.timeron.NexusDatabaseLibrary.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.timeron.NexusDatabaseLibrary.Entity.NexusApplication;
import com.timeron.NexusDatabaseLibrary.Entity.NexusPerson;
import com.timeron.NexusDatabaseLibrary.Entity.NexusUserApplicationRef;

@Repository
public class NexusUserApplicationDAO extends DaoImp<NexusUserApplicationRef> {

	public NexusUserApplicationDAO() {
		super(NexusUserApplicationRef.class);
	}
	
	public NexusUserApplicationDAO(
			Class<NexusUserApplicationRef> persistantClass) {
		super(persistantClass);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<NexusPerson> getUsersWithAccessToApp(NexusApplication app) {
		List<NexusPerson> result = new ArrayList<NexusPerson>();

		Query query = entityManager.createNamedQuery("GetUserApplicationsByApp");
		query.setParameter("appId", app.getId());
		result = (List<NexusPerson>) query.getResultList();

		if (result.size() > 0) {
			return result;
		} else {
			return Collections.emptyList();
		}
		
	}
	
}
