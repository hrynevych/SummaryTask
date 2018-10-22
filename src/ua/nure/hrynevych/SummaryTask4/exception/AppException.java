package ua.nure.hrynevych.SummaryTask4.exception;

public class AppException extends Exception{

	private static final long serialVersionUID = 7486076157799755344L;

	public AppException() {
		super();
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppException(String message) {
		super(message);
	}
}
