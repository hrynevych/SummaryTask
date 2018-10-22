package ua.nure.hrynevych.SummaryTask4.web.command;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.hrynevych.SummaryTask4.Path;
import ua.nure.hrynevych.SummaryTask4.db.DBManager;
import ua.nure.hrynevych.SummaryTask4.db.bean.DoctorCategoryBean;
import ua.nure.hrynevych.SummaryTask4.db.entity.Patient;
import ua.nure.hrynevych.SummaryTask4.exception.AppException;

public class AppointCommand extends Command {

	private static final long serialVersionUID = 7124338794232626990L;
			
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException, AppException {
		
		DBManager dbm = DBManager.getInstance();
		List<Patient> patientList = dbm.findPatients();
		List<DoctorCategoryBean> doctorList = dbm.getDoctorCategoryBeans();
		String action = request.getParameter("action");
		String forward;
		
		if ((action != null) && (action.equals("makeAppointment"))) {
			Patient patient = dbm.findPatientByLogin(request.getParameter("selectPat"));
			DoctorCategoryBean doctor = findDoctorCategoryBeanByLogin(doctorList, 
					request.getParameter("selectDoc"));
			
			if ((patient == null) || (doctor == null)) {
				String errorMessage = "Cannot make appointment";
				request.setAttribute("errorMessage", errorMessage);
				forward = Path.PAGE_ERROR_PAGE;
			} else {
				dbm.appoint(patient, doctor);
				request.setAttribute("pat", patient);
				request.setAttribute("doc", doctor);
				forward = Path.PAGE_APPOINTMENT_MADE_PAGE;
			}
		} else {
			forward = Path.PAGE_APPOINTMENT;
		}
		request.setAttribute("doctorList", doctorList);
		request.setAttribute("patientList", patientList);
		return forward;
	}
	
	private static DoctorCategoryBean findDoctorCategoryBeanByLogin(List<DoctorCategoryBean> list, 
			String login) {
		Iterator<DoctorCategoryBean> it = list.iterator();
		DoctorCategoryBean result = null;
		
		while (it.hasNext()) {
			DoctorCategoryBean cur = it.next();
			if (cur.getDoctorLogin().equals(login)) {
				result = cur;
				break;
			}
		}
		return result;
	}
}
