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
@Table(name="wallet_record")
public class WalletRecord {
	
	@Id
	@GeneratedValue
	private Integer id;
	private float value;
	private String description;
	private boolean income;
	private boolean transfer;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.S")
	private Date date;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.S")
	private Date updated;
	
	@ManyToOne
	@JoinColumn(name="wallet_type")
	private WalletType walletType;
	@ManyToOne
	@JoinColumn(name="wallet_account")
	private WalletAccount walletAccount;
	@ManyToOne
	@JoinColumn(name="destination_wallet_account")
	private WalletAccount destinationWalletAccount;
	@ManyToOne
	@JoinColumn(name="source_wallet_account")
	private WalletAccount sourceWalletAccount;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isIncome() {
		return income;
	}
	public void setIncome(boolean income) {
		this.income = income;
	}
	public boolean isTransfer() {
		return transfer;
	}
	public void setTransfer(boolean transfer) {
		this.transfer = transfer;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public WalletType getWalletType() {
		return walletType;
	}
	public void setWalletType(WalletType walletType) {
		this.walletType = walletType;
	}
	public WalletAccount getWalletAccount() {
		return walletAccount;
	}
	public void setWalletAccount(WalletAccount walletAccount) {
		this.walletAccount = walletAccount;
	}
	public WalletAccount getSourceWalletAccount() {
		return sourceWalletAccount;
	}
	public void setSourceWalletAccount(WalletAccount sourceWalletAccount) {
		this.sourceWalletAccount = sourceWalletAccount;
	}
	public WalletAccount getDestinationWalletAccount() {
		return destinationWalletAccount;
	}
	public void setDestinationWalletAccount(WalletAccount destinationWalletAccount) {
		this.destinationWalletAccount = destinationWalletAccount;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
}
