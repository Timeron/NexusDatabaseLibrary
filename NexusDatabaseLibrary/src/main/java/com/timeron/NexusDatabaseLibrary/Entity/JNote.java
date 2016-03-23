package com.timeron.NexusDatabaseLibrary.Entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.timeron.NexusDatabaseLibrary.Entity.Interface.NexusEntity;

@Entity
@Table(name="j_Note")
public class JNote implements NexusEntity{

	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	private String content;
	
	private Date created;
	
	@ManyToOne
	@JoinColumn(name="j_task", nullable = false)
	private JTask task;
	
	@OneToOne(mappedBy="note", cascade = CascadeType.ALL )
	private JHistory history;
	
	@ManyToOne
	@JoinColumn(name="j_user", nullable = true)
	private NexusUser user;

	/**
	 * Getters & Setters
	 */
	
	
	
	public Integer getId() {
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public JTask getTask() {
		return task;
	}

	public void setTask(JTask task) {
		this.task = task;
	}

	public JHistory getHistory() {
		return history;
	}

	public void setHistory(JHistory history) {
		this.history = history;
	}

	public NexusUser getUser() {
		return user;
	}

	public void setUser(NexusUser user) {
		this.user = user;
	}
	
	
}
