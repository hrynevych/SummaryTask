package ua.nure.hrynevych.SummaryTask4.exception;

public class DBException extends AppException {

	private static final long serialVersionUID = 7545588116615136929L;

	public DBException() {
		super();
	}

	public DBException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DBException(String message) {
		super(message);
	}

}
