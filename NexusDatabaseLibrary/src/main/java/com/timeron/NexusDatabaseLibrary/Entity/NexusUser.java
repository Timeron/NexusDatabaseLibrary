package com.timeron.NexusDatabaseLibrary.Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "nexus_user")
public class NexusUser {

	private String username;
	private String password;
	private boolean enabled;
	private Set<NexusUserRole> userRole = new HashSet<NexusUserRole>(0);

	public NexusUser() {
	}

	public NexusUser(String username, String password, boolean enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public NexusUser(String username, String password, 
		boolean enabled, Set<NexusUserRole> userRole) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.userRole = userRole;
	}

	@Id
	@Column(name = "username", unique = true, 
		nullable = false, length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", 
		nullable = false, length = 60)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "enabled", nullable = false)
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<NexusUserRole> getUserRole() {
		return this.userRole;
	}

	public void setUserRole(Set<NexusUserRole> userRole) {
		this.userRole = userRole;
	}

}
