package ua.nure.hrynevych.SummaryTask4.db.entity;

import java.sql.Date;

public class Nurse extends User {

	private static final long serialVersionUID = 193752416812956814L;
	
	private Date dateOfBirth;
	
	private String phoneNumber;
	
	private String email;

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
		return "Doctor [login=" + getLogin()  
				+ ", name=" + getName() 
				+ ", surname=" + getSurname()
				+ ", dateOfBirth=" + dateOfBirth
				+ ", phoneNumber=" + phoneNumber
				+ ", email=" + email
				+ ", idRole=" + getIdRole() + "]";
	}
	
}

