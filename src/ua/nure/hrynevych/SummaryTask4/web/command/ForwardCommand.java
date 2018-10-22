package ua.nure.hrynevych.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.hrynevych.SummaryTask4.exception.AppException;
import ua.nure.hrynevych.SummaryTask4.Path;
import ua.nure.hrynevych.SummaryTask4.db.Role;

public class ForwardCommand extends Command{

	private static final long serialVersionUID = 5366015689022595269L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {

		HttpSession session = request.getSession();
		String path = request.getParameter("path");
		String role = "no such role";
		try {
			Role temp = (Role) session.getAttribute("userRole");
			role = temp.getName();
		} catch (Exception ex) { }
		
		String forward = Path.PAGE_ERROR_PAGE;

		if (path == null) {
			throw new AppException("Specify path and try again.");
		} else if (path.equals("admin_page") && role.equals("admin")) {
			forward = Path.PAGE_ADMIN_PAGE;
		} else if (path.equals("doctor_page") && role.equals("doctor")) {
			forward = Path.PAGE_DOCTOR_PAGE;
		} else if (path.equals("patient_page") && role.equals("patient")) {
			forward = Path.PAGE_PATIENT_PAGE;
		} else if (path.equals("nurse_page") && role.equals("nurse")) {
			forward = Path.PAGE_NURSE_PAGE;
		} else {
			throw new AppException("Page is not available.");
		}

		return forward;
	}

	
}
