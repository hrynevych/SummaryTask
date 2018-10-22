package ua.nure.hrynevych.SummaryTask4.db.entity;

public class Admin extends User {

	private static final long serialVersionUID = 384647826596646682L;

	@Override
	public String toString() {
		return "Admin [login=" + getLogin()  
				+ ", name=" + getName() 
				+ ", surname=" + getSurname()
				+ ", idRole=" + getIdRole() + "]";
	}
	
}
