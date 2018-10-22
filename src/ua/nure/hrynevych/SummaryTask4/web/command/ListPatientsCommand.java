package ua.nure.hrynevych.SummaryTask4.web.command;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.hrynevych.SummaryTask4.Path;
import ua.nure.hrynevych.SummaryTask4.db.DBManager;
import ua.nure.hrynevych.SummaryTask4.db.entity.Patient;
import ua.nure.hrynevych.SummaryTask4.exception.AppException;

public class ListPatientsCommand extends Command {
	
	private static final long serialVersionUID = -7061184908328606730L;
	
	private static class CompareBySurname implements Comparator<Patient>, Serializable {

		private static final long serialVersionUID = -1483771824821551196L;

		public int compare(Patient pat1, Patient pat2) {
			return pat1.getSurname().compareTo(pat2.getSurname());
		}
	}
	
	private static class CompareByDateOfBirth implements Comparator<Patient>, Serializable {

		private static final long serialVersionUID = 2268407199178158026L;

		public int compare(Patient pat1, Patient pat2) {
			return pat2.getDateOfBirth().compareTo(pat1.getDateOfBirth());
		}
	}
	
	private static Comparator<Patient> compareBySurname = new CompareBySurname();
	private static Comparator<Patient> compareByDateOfBirth = new CompareByDateOfBirth();
			
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException, AppException {
		
		List<Patient> patientList = DBManager.getInstance().findPatients();
		String sort = request.getParameter("sort");
		
		if ((sort != null) && (sort.equals("surname"))) {
			Collections.sort(patientList, compareBySurname);
		} else if ((sort != null) && (sort.equals("date"))) {
			Collections.sort(patientList, compareByDateOfBirth);
		}

		request.setAttribute("patientList", patientList);		
		return Path.PAGE_LIST_PATIENTS;
	}

}