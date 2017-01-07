package com.timeron.NexusDatabaseLibrary.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="wallet_account")
public class WalletAccount {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String description;
	private String currency;
	@DateTimeFormat(pattern = "YYYY-MM-DD HH:mm:ss.S")
	private Date timestamp;
	@DateTimeFormat(pattern = "YYYY-MM-DD HH:mm:ss.S")
	private Date updated;
	
	@ManyToOne
	@JoinColumn(name="owner")
	private NexusPerson owner;
	
	@OneToMany(mappedBy="walletAccount")
	private List<WalletRecord> walletRecords;
	@OneToMany(mappedBy="destinationWalletAccount")
	private List<WalletRecord> destinationWalletRecords;
	
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
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public List<WalletRecord> getWalletRecords() {
		return walletRecords;
	}
	public void setWalletRecords(List<WalletRecord> walletRecords) {
		this.walletRecords = walletRecords;
	}
	public List<WalletRecord> getDestinationWalletRecords() {
		return destinationWalletRecords;
	}
	public void setDestinationWalletRecords(
			List<WalletRecord> destinationWalletRecords) {
		this.destinationWalletRecords = destinationWalletRecords;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public NexusPerson getOwner() {
		return owner;
	}
	public void setOwner(NexusPerson owner) {
		this.owner = owner;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		WalletAccount other = (WalletAccount) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
