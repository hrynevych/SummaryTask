package ua.nure.hrynevych.SummaryTask4.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.hrynevych.SummaryTask4.Path;
import ua.nure.hrynevych.SummaryTask4.db.DBManager;
import ua.nure.hrynevych.SummaryTask4.db.entity.Patient;
import ua.nure.hrynevych.SummaryTask4.exception.AppException;

public class DischargeCommand extends Command {

	private static final long serialVersionUID = -181888577814944894L;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException, AppException {
		
		DBManager dbm = DBManager.getInstance();
		List<Patient> patientList = dbm.findPatients();
		String patient = request.getParameter("patientToDischarge");
		String undo = request.getParameter("undo");
		String forward;

		if ((patient != null) && (undo == null)) {
			Patient discharged = dbm.dischargePatientByLogin(patient);
			request.setAttribute("discharge_page_form", "form_result");
			request.setAttribute("discharged", discharged);
			forward = Path.PAGE_DISCHARGE_PAGE;
		} else {
			request.setAttribute("discharge_page_form", "form_list");
			request.setAttribute("patientList", patientList);
			forward = Path.PAGE_DISCHARGE_PAGE;
		}
		return forward;
	}
}
