package ua.nure.hrynevych.SummaryTask4.db.entity;

public abstract class User extends Entity {

	private static final long serialVersionUID = -6374718200098696881L;

	private String login;
	
	private String password;
	
	private String name;
	
	private String surname;

	private int idRole;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public int getIdRole() {
		return idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}
	
}

