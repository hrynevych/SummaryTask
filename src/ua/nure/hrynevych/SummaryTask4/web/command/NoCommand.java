package ua.nure.hrynevych.SummaryTask4.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.hrynevych.SummaryTask4.Path;

public class NoCommand extends Command {

	private static final long serialVersionUID = 5613653458707192624L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		String errorMessage = "No such command";
		request.setAttribute("errorMessage", errorMessage);

		return Path.PAGE_ERROR_PAGE;
	}

}