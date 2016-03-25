package com.timeron.NexusDatabaseLibrary.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.timeron.NexusDatabaseLibrary.Entity.Interface.NexusEntity;

@Entity
@Table(name = "j_user_project_ref")
public class JUserProject implements NexusEntity {

	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;
	
	@ManyToOne
	@JoinColumn(name="nexus_user", nullable = true)
	private NexusPerson user;
	
	@ManyToOne
	@JoinColumn(name="j_project", nullable = true)
	private JProject project;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public NexusPerson getUser() {
		return user;
	}

	public void setUser(NexusPerson user) {
		this.user = user;
	}

	public JProject getProject() {
		return project;
	}

	public void setProject(JProject project) {
		this.project = project;
	}

	
}
