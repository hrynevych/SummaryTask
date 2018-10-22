package ua.nure.hrynevych.SummaryTask4.db;

import ua.nure.hrynevych.SummaryTask4.db.entity.User;

public enum Role {
	ADMIN, PATIENT, DOCTOR, NURSE;
	
	public static Role getRole(User user) {
		int idRole = user.getIdRole();
		return Role.values()[idRole];
	}
	
	public static Role getRole(String name) {
		Role role = null;
		for (Role r : Role.values()) {
			if (r.getName().equals(name)) {
				role = r;
			}
		}
		return role;
	}
	
	public String getName() {
		return name().toLowerCase();
	}

}
