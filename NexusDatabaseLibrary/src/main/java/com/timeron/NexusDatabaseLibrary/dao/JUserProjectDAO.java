package com.timeron.NexusDatabaseLibrary.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.timeron.NexusDatabaseLibrary.Entity.JProject;
import com.timeron.NexusDatabaseLibrary.Entity.JUserProject;
import com.timeron.NexusDatabaseLibrary.Entity.NexusPerson;

@Repository
public class JUserProjectDAO extends DaoImp<JUserProject>{

	static Logger LOG = Logger.getLogger(JUserProjectDAO.class);
	
	public JUserProjectDAO() {
		super(JUserProject.class);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<JUserProject> getByUser(NexusPerson user){
		List<JUserProject> result = new ArrayList<JUserProject>();
		
		Query query = entityManager.createNamedQuery("GetUserProjectsByUser");
		query.setParameter("userId", user.getId());
		result = (List<JUserProject>) query.getResultList();
		if (result.size() > 0) {
			return result;
		} else {
			List<JUserProject> emptyList = Collections.emptyList();
			return emptyList;
		}	
	}
	
	@Transactional
	public List<JUserProject> getByProject(JProject project){
		List<JUserProject> result = new ArrayList<JUserProject>();
		
		Query query = entityManager.createNamedQuery("GetUserProjectsByProject");
		query.setParameter("projectId", project.getId());
		result = (List<JUserProject>) query.getResultList();
		if (result.size() > 0) {
			return result;
		} else {
			List<JUserProject> emptyList = Collections.emptyList();
			return emptyList;
		}	
	}

}
