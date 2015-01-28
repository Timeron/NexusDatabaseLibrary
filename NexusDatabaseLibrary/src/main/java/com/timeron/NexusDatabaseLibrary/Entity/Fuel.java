package com.timeron.NexusDatabaseLibrary.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Fuel {

	@Id
	@GeneratedValue
	private int id;
	private float liters;
	private float distance;
	private boolean traffic;
	private boolean mixed;
	private boolean track;
	@DateTimeFormat(pattern = "YYYY-MM-DD HH:mm:ss.S")
	private Date date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getLiters() {
		return liters;
	}
	public void setLiters(float liters) {
		this.liters = liters;
	}
	public float getDistance() {
		return distance;
	}
	public void setDistance(float distance) {
		this.distance = distance;
	}
	public boolean isTraffic() {
		return traffic;
	}
	public void setTraffic(boolean traffic) {
		this.traffic = traffic;
	}
	public boolean isMixed() {
		return mixed;
	}
	public void setMixed(boolean mixed) {
		this.mixed = mixed;
	}
	public boolean isTrack() {
		return track;
	}
	public void setTrack(boolean track) {
		this.track = track;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
