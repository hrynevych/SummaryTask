package ua.nure.hrynevych.SummaryTask4.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.hrynevych.SummaryTask4.Path;

public class LogoutCommand extends Command {

	private static final long serialVersionUID = -670626806839635644L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		return Path.PAGE_LOGIN;
	}

}