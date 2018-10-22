package ua.nure.hrynevych.SummaryTask4.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.hrynevych.SummaryTask4.Path;
import ua.nure.hrynevych.SummaryTask4.exception.AppException;
import ua.nure.hrynevych.SummaryTask4.web.command.Command;
import ua.nure.hrynevych.SummaryTask4.web.command.CommandContainer;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		// extract command name from the request
		String commandName = request.getParameter("command");

		// obtain command object by its name
		Command command = CommandContainer.get(commandName);

		// execute command and get forward address
		String forward = Path.PAGE_ERROR_PAGE;
		try {
			forward = command.execute(request, response);
		} catch (AppException ex) {
			request.setAttribute("errorMessage", ex.getMessage());
		}

		// go to forward
		request.getRequestDispatcher(forward).forward(request, response);
	}

}
