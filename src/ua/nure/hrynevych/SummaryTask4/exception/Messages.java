package ua.nure.hrynevych.SummaryTask4.exception;

public class Messages {

	private Messages() {
		throw new UnsupportedOperationException();
	}
	
	public static final String ERR_CANNOT_OBTAIN_ADMINS = "Cannot obtain admins";
	
	public static final String ERR_CANNOT_OBTAIN_PATIENTS = "Cannot obtain patients";
	
	public static final String ERR_CANNOT_OBTAIN_DOCTORS = "Cannot obtain doctors";
	
	public static final String ERR_CANNOT_OBTAIN_NURSES = "Cannot obtain nurses";
	
	public static final String ERR_CANNOT_OBTAIN_DOCTOR_CATEGORY_BEANS = "Cannot obtain doctor category beans";

	public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain a connection from the pool";

	public static final String ERR_CANNOT_OBTAIN_CATEGORIES = "Cannot obtain categories";

	public static final String ERR_CANNOT_OBTAIN_USER_BY_ID = "Cannot obtain a user by its id";

	public static final String ERR_CANNOT_OBTAIN_USER_BY_LOGIN = "Cannot obtain a user by its login";
	
	public static final String ERR_CANNOT_OBTAIN_CATEGORY_BY_NAME = "Cannot obtain a category with such name";
	
	public static final String ERR_CANNOT_OBTAIN_CATEGORY_BY_ID = "Cannot obtain a category by its id";
	
	public static final String ERR_CANNOT_COUNT_PATIENTS_OF_DOCTOR = "Cannot count patients of the doctor";

	public static final String ERR_CANNOT_CLOSE_CONNECTION = "Cannot close a connection";

	public static final String ERR_CANNOT_CLOSE_RESULTSET = "Cannot close a result set";

	public static final String ERR_CANNOT_CLOSE_STATEMENT = "Cannot close a statement";

	public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = "Cannot obtain the data source";

	public static final String ERR_CANNOT_GET_MAX_ID = "Cannot get maximum id from the table ";

	public static final String ERR_CANNOT_ADD_SUCH_USER = "Cannot add such user";

	public static final String ERR_CANNOT_DELETE_SUCH_USER = "Cannot delete such user";

	public static final String ERR_CANNOT_ADD_SUCH_CARD = "Cannot add such card";
	
	public static final String ERR_CANNOT_DISCHARGE_SUCH_PATIENT = "Cannot discharge such patient";
}