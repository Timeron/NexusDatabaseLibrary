package com.timeron.NexusDatabaseLibrary.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="nexus_user_application_ref")
public class NexusUserApplicationRef {

	@Id
	@GeneratedValue
	private Integer id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.S")
	private Date timestamp;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private NexusPerson user;
	
	@ManyToOne
	@JoinColumn(name="application_id")
	private NexusApplication application;

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

	public NexusApplication getApplication() {
		return application;
	}

	public void setApplication(NexusApplication application) {
		this.application = application;
	}
	
	
	
}
