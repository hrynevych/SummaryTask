package ua.nure.hrynevych.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.hrynevych.SummaryTask4.exception.AppException;
import ua.nure.hrynevych.SummaryTask4.Path;
import ua.nure.hrynevych.SummaryTask4.db.DBManager;
import ua.nure.hrynevych.SummaryTask4.db.Role;
import ua.nure.hrynevych.SummaryTask4.db.entity.Doctor;
import ua.nure.hrynevych.SummaryTask4.db.entity.Nurse;
import ua.nure.hrynevych.SummaryTask4.db.entity.Patient;

public class AddUserCommand extends Command{

	private static final long serialVersionUID = 4643395215683963940L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		
		DBManager dbm = DBManager.getInstance();
		String formFilled = request.getParameter("form_filled");
		String user = request.getParameter("userParam");
		String userToDel = request.getParameter("userToDel");
		Role userRole = Role.getRole(user);
		String forward;

		if (userRole == null) {
			String errorMessage = "No such command";
			request.setAttribute("errorMessage", errorMessage);
			forward = Path.PAGE_ERROR_PAGE;
		} else if ((formFilled != null) && (formFilled.equals("true"))) {
			forward = Path.PAGE_USER_ADDED;
			if (userRole.getName().equals("patient")) {
				Patient pt = dbm.addNewPatient(request);
				request.setAttribute("addedUser", pt);
			} else if (userRole.getName().equals("doctor")) {
				Doctor doc = dbm.addNewDoctor(request);
				request.setAttribute("addedUser", doc);
				request.setAttribute("docCategory", dbm.findCategoryById(doc.getIdCategory()));
			} else if (userRole.getName().equals("nurse")) {
				Nurse nur = dbm.addNewNurse(request);
				request.setAttribute("addedUser", nur);
			} else {
				String errorMessage = "The ability of adding new admin is not yet available.";
				request.setAttribute("errorMessage", errorMessage);
				forward = Path.PAGE_ERROR_PAGE;
			}
		} else {
			forward = Path.PAGE_ADD_USER;
			if (userToDel != null) {
				try {
					long id = Long.parseLong(userToDel);
					if (userRole.getName().equals("patient")) {
						dbm.deletePatientById(id);
					} else if (userRole.getName().equals("doctor")) {
						dbm.deleteDoctorById(id);
					} else if (userRole.getName().equals("nurse")) {
						dbm.deleteNurseById(id);
					} else {
						throw new NumberFormatException();
					}
					request.setAttribute("delSuccessfully", "not null");
				} catch (NumberFormatException ex) {
					String errorMessage = "Cannot find user to delete.";
					request.setAttribute("errorMessage", errorMessage);
					forward = Path.PAGE_ERROR_PAGE;
				}
			}
		}
		
		request.setAttribute("userToAdd", userRole);
		if ((user != null) && (user.equals("doctor"))) {
			request.setAttribute("categories", dbm.findCategories());
		}
		return forward;
	}

	
}
