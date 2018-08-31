package com.timeron.NexusDatabaseLibrary.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.timeron.NexusDatabaseLibrary.Entity.JRelease;

@Repository
public class JReleaseDAO extends DaoImp<JRelease>  {
	
	static Logger LOG = Logger.getLogger(JReleaseDAO.class);
	
	public JReleaseDAO(){
		super(JRelease.class);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<JRelease> getAllReleasesForProjectInNameOrder(int projectId) {
		LOG.info("enter getAllForProjectInNameOrder");
		List<JRelease> jReleases;
		Query query = entityManager.createNamedQuery("getAllReleasesForProjectInNameOrder");
		query.setParameter("projectId", projectId);
		jReleases = (List<JRelease>) query.getResultList();
		
		LOG.info("exit getAllForProjectInNameOrder");
		return jReleases;
		
	}

}
