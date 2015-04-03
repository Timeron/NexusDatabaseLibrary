package com.timeron.NexusDatabaseLibrary.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="j_project")
public class JProject {

	@Id
	@GeneratedValue
	@Expose
	private int id;
	@Expose
	private String name;
	@Expose
	private String description;
	@Expose
	private Date created;
	
	@OneToMany(mappedBy="project")
	private List<JTask> task;
	
	@OneToMany(mappedBy="project")
	private List<JRelease> releases;
	
	@ManyToOne
	@JoinColumn(name="status")
	private JStatus status;
	
	@ManyToOne
	@JoinColumn(name="autor")
	private NexusUser user;

	/**
	 * Getters & Setters
	 */
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public List<JTask> getTask() {
		return task;
	}

	public void setTask(List<JTask> task) {
		this.task = task;
	}

	public JStatus getStatus() {
		return status;
	}

	public void setStatus(JStatus status) {
		this.status = status;
	}

	public NexusUser getUser() {
		return user;
	}

	public void setUser(NexusUser user) {
		this.user = user;
	}

	public List<JRelease> getReleases() {
		return releases;
	}

	public void setReleases(List<JRelease> releases) {
		this.releases = releases;
	}
	
}
