package com.timeron.NexusDatabaseLibrary.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: JRelease
 *
 */
@Entity
@Table(name="j_release")
public class JRelease {
	
	@Id
	@GeneratedValue
	private int id;
	private String version;
	
	@ManyToOne
	@JoinColumn(name="j_project")
	private JProject project;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public JProject getProject() {
		return project;
	}

	public void setProject(JProject project) {
		this.project = project;
	}
	
	
   
}
