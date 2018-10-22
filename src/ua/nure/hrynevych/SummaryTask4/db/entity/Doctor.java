package ua.nure.hrynevych.SummaryTask4.db.entity;

import java.sql.Date;

public class Doctor extends User {

	private static final long serialVersionUID = -2800184324369979028L;
	
	private Date dateOfBirth;
	
	private String phoneNumber;
	
	private String email;
	
	private int idCategory;
	
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
	
	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	@Override
	public String toString() {
		return "Doctor [login=" + getLogin()  
				+ ", name=" + getName() 
				+ ", surname=" + getSurname()
				+ ", dateOfBirth=" + dateOfBirth
				+ ", phoneNumber=" + phoneNumber
				+ ", email=" + email
				+ ", idCategory=" + idCategory
				+ ", idRole=" + getIdRole() + "]";
	}
	
}

