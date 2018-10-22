package ua.nure.hrynevych.SummaryTask4.db.entity;

import java.sql.Date;

public class Patient extends User {

	private static final long serialVersionUID = 9005017702909495617L;
	
	private Date dateOfBirth;
	
	private String address;
	
	private String sex;
	
	private String phoneNumber;
	
	private String email;
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Patient [login=" + getLogin()  
				+ ", name=" + getName() 
				+ ", surname=" + getSurname()
				+ ", dateOfBirth=" + dateOfBirth
				+ ", address=" + address
				+ ", sex=" + sex
				+ ", phoneNumber=" + phoneNumber
				+ ", email=" + email
				+ ", idRole=" + getIdRole() + "]";
	}
	
}
