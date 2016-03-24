package com.timeron.NexusDatabaseLibrary.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.joda.time.DateTime;

@Entity
@Table(name = "nexus_person")
public class NexusPerson {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "first_name", length = 30)
	private String firstName;

	@Column(name = "last_name", length = 30)
	private String lastName;

	@Column(length = 30)
	private String nick;
	
	@Column(length = 40)
	private String nickLogo;

	@Column(length = 40)
	private String emailPrv;
	
	@Column(length = 40)
	private String emailOffice;

	@Column(length = 40)
	private String city;

	@Column(length = 30)
	private String country;

	@Column(length = 40)
	private String address;

	@Column(length = 15)
	private String phone1;

	@Column(length = 15)
	private String phone2;

	@Column(length = 15)
	private String phone3;

	@Lob
	private String description;
	
	@Column(length = 255)
	private String tags;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	@Column(name = "name_day")
	@Temporal(TemporalType.DATE)
	private Date nameDay;

	@GeneratedValue
	@Column(name = "updat_timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTimestamp;

	@GeneratedValue
	@Column(name = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTimestamp;

	@OneToMany(mappedBy="user")
	private List<JTask> tasks;
	
	@OneToMany(mappedBy="user")
	private List<WalletAccount> walletAccounts;
	
	@ManyToMany(mappedBy="contacts")
	private List<ContactEvent> event;
	
	@Transient
	private String birthdayYear;
	@Transient
	private String birthdayMonth;
	@Transient
	private String birthdayDay;
	@Transient
	private String nameDayMonth;
	@Transient
	private String nameDayDay;
	
	/*
	 * Getters & setters
	 */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPseudo() {
		return nick;
	}

	public void setPseudo(String nick) {
		this.nick = nick;
	}

	public String getEmailPrv() {
		return emailPrv;
	}

	public void setEmailPrv(String emailPrv) {
		this.emailPrv = emailPrv;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DateTime getBirthdayJoda() {
		return new DateTime(birthday);
	}

	public void setBirthdayJoda(DateTime birthday) {
		this.birthday = new Date(birthday.getMillis());
	}
	
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public DateTime getNameDayJoda() {
		return new DateTime(nameDay);
	}

	public void setNameDay(DateTime nameDay) {
		this.nameDay = new Date(nameDay.getMillis());
	}
	
	public Date getNameDay() {
		return nameDay;
	}

	public void setNameDay(Date nameDay) {
		this.nameDay = nameDay;
	}

	public Date getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(Date updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	public DateTime getCreateTimestampJoda() {
		return new DateTime(createTimestamp);
	}
	
	public void setCreateTimestampJoda(DateTime createTimestamp) {
		this.createTimestamp = new Date(createTimestamp.getMillis());
	}
	
	public Date getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public String getBirthdayYear() {
		return birthdayYear;
	}

	public String getBirthdayMonth() {
		return birthdayMonth;
	}

	public String getBirthdayDay() {
		return birthdayDay;
	}

	public String getNameDayMonth() {
		return nameDayMonth;
	}

	public String getNameDayDay() {
		return nameDayDay;
	}
	
	public void setBirthdayYear(String birthdayYear) {
		this.birthdayYear = birthdayYear;
	}

	public void setBirthdayMonth(String birthdayMonth) {
		this.birthdayMonth = birthdayMonth;
	}

	public void setBirthdayDay(String birthdayDay) {
		this.birthdayDay = birthdayDay;
	}

	public void setNameDayMonth(String nameDayMonth) {
		this.nameDayMonth = nameDayMonth;
	}

	public void setNameDayDay(String nameDayDay) {
		this.nameDayDay = nameDayDay;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public List<JTask> getTasks() {
		return tasks;
	}

	public void setTasks(List<JTask> tasks) {
		this.tasks = tasks;
	}

	public String getNickLogo() {
		return nickLogo;
	}

	public void setNickLogo(String nickLogo) {
		this.nickLogo = nickLogo;
	}

	public String getEmailOffice() {
		return emailOffice;
	}

	public void setEmailOffice(String emailOffice) {
		this.emailOffice = emailOffice;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
	
}
