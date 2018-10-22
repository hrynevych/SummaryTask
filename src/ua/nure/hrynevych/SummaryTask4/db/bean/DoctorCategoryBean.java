package ua.nure.hrynevych.SummaryTask4.db.bean;

import java.sql.Date;

import ua.nure.hrynevych.SummaryTask4.db.entity.Entity;

public class DoctorCategoryBean extends Entity {

	private static final long serialVersionUID = -3827014300450838676L;

	private long categoryId;
	
	private String categoryName;
	
	private long doctorId;
	
	private String doctorName;

	private String doctorSurname;
	
	private Date doctorDateOfBirth;

	private String doctorPhoneNumber;
	
	private String doctorEmail;
	
	private String doctorLogin;
	
	private int doctorIdRole;
	
	private int patientsNumber;

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public long getDoctorId() {
		return doctorId;
	}
	
	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	
	public String getDoctorSurname() {
		return doctorSurname;
	}

	public void setDoctorSurname(String doctorSurname) {
		this.doctorSurname = doctorSurname;
	}
	
	public Date getDoctorDateOfBirth() {
		return doctorDateOfBirth;
	}

	public void setDoctorDateOfBirth(Date doctorDateOfBirth) {
		this.doctorDateOfBirth = doctorDateOfBirth;
	}
	
	public String getDoctorPhoneNumber() {
		return doctorPhoneNumber;
	}

	public void setDoctorPhoneNumber(String doctorPhoneNumber) {
		this.doctorPhoneNumber = doctorPhoneNumber;
	}
	
	public String getDoctorEmail() {
		return doctorEmail;
	}

	public void setDoctorEmail(String doctorEmail) {
		this.doctorEmail = doctorEmail;
	}
	
	public String getDoctorLogin() {
		return doctorLogin;
	}

	public void setDoctorLogin(String doctorLogin) {
		this.doctorLogin = doctorLogin;
	}
	
	public int getDoctorIdRole() {
		return doctorIdRole;
	}

	public void setDoctorIdRole(int doctorIdRole) {
		this.doctorIdRole = doctorIdRole;
	}
	
	public int getPatientsNumber() {
		return patientsNumber;
	}

	public void setPatientsNumber(int patientsNumber) {
		this.patientsNumber = patientsNumber;
	}

	@Override
	public String toString() {
		return "DoctorCategoryBean [doctorId=" + doctorId
				+ ", doctorName=" + doctorName
				+ ", doctorSurname=" + doctorSurname
				+ ", doctorDateOfBirth=" + doctorDateOfBirth
				+ ", doctorPhoneNumber=" + doctorPhoneNumber
				+ ", doctorEmail=" + doctorEmail
				+ ", doctorIdRole=" + doctorIdRole
				+ ", categoryName=" + categoryName
				+ ", categoryId=" + categoryId
				+ ", patientsNumber=" + patientsNumber + "]";
	}
}
