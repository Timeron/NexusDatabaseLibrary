package com.timeron.NexusDatabaseLibrary.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="j_history")
public class JHistory {

	@Id
	@GeneratedValue
	private int id;
	
	@Type(type="text")
	private String message;
	
	private Date created;
	
	@ManyToOne
	@JoinColumn(name="j_task", nullable = true)
	private JTask task;
	
	@OneToOne
	@JoinColumn(name="j_note", nullable = true)
	private JNote note;
	
	@ManyToOne
	@JoinColumn(name="j_status", nullable = true)
	private JStatus status;
	
	@ManyToOne
	@JoinColumn(name="j_user", nullable = true)
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public JNote getNote() {
		return note;
	}

	public void setNote(JNote note) {
		this.note = note;
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
	
	
}
