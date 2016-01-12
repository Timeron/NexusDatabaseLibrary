package com.timeron.NexusDatabaseLibrary.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Event")
public class ContactEvent {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String description;
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name="contacts_event", 
    joinColumns={@JoinColumn(name="event")}, 
    inverseJoinColumns={@JoinColumn(name="contacts")})
	private List<NexusPerson> contacts;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<NexusPerson> getContacts() {
		return contacts;
	}

	public void setContacts(List<NexusPerson> contacts) {
		this.contacts = contacts;
	}
	
	
	
}
