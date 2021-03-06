package com.timeron.NexusDatabaseLibrary.Entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="j_project")
public class JProject {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String description;
	private Date created;
	private String prefix;
	
	@OneToMany(mappedBy="project", fetch=FetchType.EAGER)
	private List<JTask> task;
	
	@OneToMany(mappedBy="project", fetch=FetchType.EAGER)
	private Set<JRelease> releases;
	
	@ManyToOne
	@JoinColumn(name="status", nullable = true)
	private JStatus status;
	
	@ManyToOne
	@JoinColumn(name="autor", nullable = true)
	private NexusPerson user;
	
	@OneToMany(mappedBy="project")
	private List<JUserProject> userProjects;

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

	public NexusPerson getUser() {
		return user;
	}

	public void setUser(NexusPerson user) {
		this.user = user;
	}

	public Set<JRelease> getReleases() {
		return releases;
	}

	public void setReleases(Set<JRelease> releases) {
		this.releases = releases;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
}
