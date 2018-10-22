package ua.nure.hrynevych.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.hrynevych.SummaryTask4.exception.AppException;
import ua.nure.hrynevych.SummaryTask4.Path;
import ua.nure.hrynevych.SummaryTask4.db.DBManager;
import ua.nure.hrynevych.SummaryTask4.db.Role;
import ua.nure.hrynevych.SummaryTask4.db.entity.User;

public class LoginCommand extends Command{

	private static final long serialVersionUID = 3486290842570349659L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		HttpSession session = request.getSession();
		
		// obtain login and password from a request
		DBManager manager = DBManager.getInstance();
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
			throw new AppException("Login/password cannot be empty");
		}

		User user = manager.findUserByLogin(login);
		if (user == null || !password.equals(user.getPassword())) {
			throw new AppException("Cannot find user with such login/password");
		}

		Role userRole = Role.getRole(user);

		String forward = Path.PAGE_ERROR_PAGE;

		if (userRole == Role.ADMIN) {
			forward = Path.PAGE_ADMIN_PAGE;
		}

		if (userRole == Role.PATIENT) {

		}

		if (userRole == Role.DOCTOR) {

		}
		
		if (userRole == Role.NURSE) {

		}
		
		session.setAttribute("user", user);
		session.setAttribute("userRole", userRole);
		return forward;
	}

	
}
