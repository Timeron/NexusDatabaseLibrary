package com.timeron.NexusDatabaseLibrary.Entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="j_task_type")
public class JTaskType {

	@Id
	@GeneratedValue
	private int id;
	
	private String description;
	
	@OneToMany(mappedBy="taskType")
	private List<JTask> task;
	
	/**
	 * Getters & Setters
	 */
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<JTask> getTask() {
		return task;
	}

	public void setTask(List<JTask> task) {
		this.task = task;
	}
	
}
