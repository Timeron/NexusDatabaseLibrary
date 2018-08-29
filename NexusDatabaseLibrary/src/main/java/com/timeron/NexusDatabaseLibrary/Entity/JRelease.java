package com.timeron.NexusDatabaseLibrary.Entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.timeron.NexusDatabaseLibrary.Entity.Interface.NexusEntity;

/**
 * Entity implementation class for Entity: JRelease
 *
 */
@Entity
@Table(name="j_release")
public class JRelease implements NexusEntity {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String version;
	private String comment;
	
	@ManyToOne
	@JoinColumn(name="j_project")
	private JProject project;
	
	@OneToMany(mappedBy="release")
	List<JTask> jTasks;

	public Integer getId() {
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<JTask> getjTasks() {
		return jTasks;
	}

	public void setjTasks(List<JTask> jTasks) {
		this.jTasks = jTasks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JRelease other = (JRelease) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}
	
	
   
}
