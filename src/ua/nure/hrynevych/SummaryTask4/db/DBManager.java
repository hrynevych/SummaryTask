package ua.nure.hrynevych.SummaryTask4.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import ua.nure.hrynevych.SummaryTask4.db.bean.DoctorCategoryBean;
import ua.nure.hrynevych.SummaryTask4.db.entity.Admin;
import ua.nure.hrynevych.SummaryTask4.db.entity.Category;
import ua.nure.hrynevych.SummaryTask4.db.entity.Doctor;
import ua.nure.hrynevych.SummaryTask4.db.entity.Nurse;
import ua.nure.hrynevych.SummaryTask4.db.entity.Patient;
import ua.nure.hrynevych.SummaryTask4.db.entity.User;
import ua.nure.hrynevych.SummaryTask4.exception.DBException;
import ua.nure.hrynevych.SummaryTask4.exception.Messages;

public class DBManager {

	private static final String SQL_FIND_ADMIN_BY_LOGIN = "SELECT * FROM admins WHERE login=?";
	
	private static final String SQL_FIND_PATIENT_BY_LOGIN = "SELECT * FROM patients WHERE login=?";
	
	private static final String SQL_FIND_DOCTOR_BY_LOGIN = "SELECT * FROM doctors WHERE login=?";
	
	private static final String SQL_FIND_NURSE_BY_LOGIN = "SELECT * FROM nurses WHERE login=?";
	
	private static final String SQL_FIND_CATEGORY_BY_NAME = "SELECT * FROM categories WHERE name=?";
	
	private static final String SQL_FIND_CATEGORY_BY_ID = "SELECT * FROM categories WHERE id=?";
	
	private static final String SQL_FIND_ALL_ADMINS = "SELECT * FROM admins";
	
	private static final String SQL_FIND_ALL_PATIENTS = "SELECT * FROM patients";
	
	private static final String SQL_FIND_ALL_DOCTORS = "SELECT * FROM doctors";
	
	private static final String SQL_FIND_ALL_NURSES = "SELECT * FROM nurses";
	
	private static final String SQL_FIND_ALL_CATEGORIES = "SELECT * FROM categories";
	
	private static final String SQL_FIND_CARDS_BY_IDS = "SELECT * FROM cards "
			+ "WHERE id_patient=? AND id_doctor=?";
	
	private static final String SQL_COUNT_PATIENTS_OF_DOCTOR = "SELECT COUNT(id) FROM cards WHERE id_doctor=?";
	
	private static final String SQL_GET_DOCTOR_CATEGORY_BEANS = "SELECT c.id, c.name, d.id, d.name, d.surname, "
			+ "d.date_of_birth, d.phone_number, d.email, d.id_role, d.login"
			+ "	FROM categories c, doctors d"
			+ "	WHERE d.id_category=c.id";
	
	private static final String SQL_GET_DOCTOR_CATEGORY_BEANS_BY_CATEGORY = "SELECT c.id, c.name, d.id, d.name, d.surname, "
			+ "d.date_of_birth, d.phone_number, d.email, d.id_role, d.login"
			+ "	FROM categories c, doctors d"
			+ "	WHERE d.id_category=c.id AND c.name=?";
	
	private static final String SQL_INSERT_NEW_PATIENT = "INSERT INTO patients "
			+ "(id, name, surname, date_of_birth, sex, address, phone_number, email, "
			+ "login, password, id_role) VALUES " 
			+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1)";
	
	private static final String SQL_INSERT_NEW_DOCTOR = "INSERT INTO doctors "
			+ "(id, name, surname, date_of_birth, phone_number, email, login, "
			+ "password, id_role, id_category) VALUES " 
			+ "(?, ?, ?, ?, ?, ?, ?, ?, 2, ?)";
	
	private static final String SQL_INSERT_NEW_NURSE = "INSERT INTO nurses "
			+ "(id, name, surname, date_of_birth, phone_number, email, login, "
			+ "password, id_role) VALUES " 
			+ "(?, ?, ?, ?, ?, ?, ?, ?, 3)";
	
	private static final String SQL_ADD_NEW_CARD = "INSERT INTO cards "
			+ "(id, id_patient, id_doctor, diagnosis) VALUES "
			+ "(?, ?, ?, ' ')";
	
	private static final String SQL_GET_MAX_ID = "SELECT MAX(id) FROM ";
	
	private static final String SQL_DELETE_PATIENT_BY_ID = "DELETE FROM patients WHERE id = ?";
	
	private static final String SQL_DELETE_DOCTOR_BY_ID = "DELETE FROM doctors WHERE id = ?";
	
	private static final String SQL_DELETE_NURSE_BY_ID = "DELETE FROM nurses WHERE id = ?";
	
	private static final String SQL_DISCHARGE_PATIENT_BY_ID_1 = "DELETE FROM prescriptions WHERE id_card "
			+ "IN (SELECT id FROM cards WHERE id_patient = ?)";
	
	private static final String SQL_DISCHARGE_PATIENT_BY_ID_2 = "DELETE FROM cards WHERE id_patient = ?";
	
	private static final String SQL_DISCHARGE_PATIENT_BY_ID_3 = "DELETE FROM patients WHERE id = ?";
	
	private static DBManager instance;

	public static synchronized DBManager getInstance() throws DBException {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}
	
	private DBManager() throws DBException {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			
			ds = (DataSource) envContext.lookup("jdbc/hospital");
		} catch (NamingException ex) {
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
		}
	}
	
	public DataSource ds;
	
	public Connection getConnection() throws DBException {
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (Exception ex) {
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
		}
		return con;
	}
	
	public Admin findAdminByLogin(String login) throws DBException {
		Admin admin = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_ADMIN_BY_LOGIN);
			pstmt.setString(1, login);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				admin = extractAdmin(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return admin;
	}

	private Admin extractAdmin(ResultSet rs) throws SQLException {
		Admin admin = new Admin();
		admin.setId(rs.getLong(Fields.ENTITY_ID));
		admin.setLogin(rs.getString(Fields.USER_LOGIN));
		admin.setPassword(rs.getString(Fields.USER_PASSWORD));
		admin.setName(rs.getString(Fields.USER_NAME));
		admin.setSurname(rs.getString(Fields.USER_SURNAME));
		admin.setIdRole(rs.getInt(Fields.USER_ID_ROLE));
		return admin;
	}
	
	public Patient findPatientByLogin(String login) throws DBException {
		Patient patient = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_PATIENT_BY_LOGIN);
			pstmt.setString(1, login);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				patient = extractPatient(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return patient;
	}
	
	
	private Patient extractPatient(ResultSet rs) throws SQLException {
		Patient patient = new Patient();
		patient.setId(rs.getLong(Fields.ENTITY_ID));
		patient.setLogin(rs.getString(Fields.USER_LOGIN));
		patient.setPassword(rs.getString(Fields.USER_PASSWORD));
		patient.setName(rs.getString(Fields.USER_NAME));
		patient.setSurname(rs.getString(Fields.USER_SURNAME));
		patient.setDateOfBirth(rs.getDate(Fields.USER_DATE_OF_BIRTH));
		patient.setAddress(rs.getString(Fields.USER_ADDRESS));
		patient.setSex(rs.getString(Fields.USER_SEX));
		patient.setPhoneNumber(rs.getString(Fields.USER_PHONE_NUMBER));
		patient.setEmail(rs.getString(Fields.USER_EMAIL));
		patient.setIdRole(rs.getInt(Fields.USER_ID_ROLE));
		return patient;
	}
	
	public Doctor findDoctorByLogin(String login) throws DBException {
		Doctor doctor = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_DOCTOR_BY_LOGIN);
			pstmt.setString(1, login);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				doctor = extractDoctor(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return doctor;
	}
	
	private Doctor extractDoctor(ResultSet rs) throws SQLException {
		Doctor doctor = new Doctor();
		doctor.setId(rs.getLong(Fields.ENTITY_ID));
		doctor.setLogin(rs.getString(Fields.USER_LOGIN));
		doctor.setPassword(rs.getString(Fields.USER_PASSWORD));
		doctor.setName(rs.getString(Fields.USER_NAME));
		doctor.setSurname(rs.getString(Fields.USER_SURNAME));
		doctor.setDateOfBirth(rs.getDate(Fields.USER_DATE_OF_BIRTH));
		doctor.setPhoneNumber(rs.getString(Fields.USER_PHONE_NUMBER));
		doctor.setEmail(rs.getString(Fields.USER_EMAIL));
		doctor.setIdRole(rs.getInt(Fields.USER_ID_ROLE));
		doctor.setIdCategory(rs.getInt(Fields.USER_ID_CATEGORY));
		return doctor;
	}
	
	public int countPatientsOfDoctor(Doctor doc) throws DBException {
		int result = -1;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_COUNT_PATIENTS_OF_DOCTOR);
			pstmt.setLong(1, doc.getId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_COUNT_PATIENTS_OF_DOCTOR, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return result;
	}
	
	public int countPatientsOfDoctor(DoctorCategoryBean doc) throws DBException {
		int result = -1;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_COUNT_PATIENTS_OF_DOCTOR);
			pstmt.setLong(1, doc.getDoctorId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_COUNT_PATIENTS_OF_DOCTOR, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return result;
	}
	
	public Nurse findNurseByLogin(String login) throws DBException {
		Nurse nurse = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_NURSE_BY_LOGIN);
			pstmt.setString(1, login);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				nurse = extractNurse(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return nurse;
	}
	
	private Nurse extractNurse(ResultSet rs) throws SQLException {
		Nurse nurse = new Nurse();
		nurse.setId(rs.getLong(Fields.ENTITY_ID));
		nurse.setLogin(rs.getString(Fields.USER_LOGIN));
		nurse.setPassword(rs.getString(Fields.USER_PASSWORD));
		nurse.setName(rs.getString(Fields.USER_NAME));
		nurse.setSurname(rs.getString(Fields.USER_SURNAME));
		nurse.setDateOfBirth(rs.getDate(Fields.USER_DATE_OF_BIRTH));
		nurse.setPhoneNumber(rs.getString(Fields.USER_PHONE_NUMBER));
		nurse.setEmail(rs.getString(Fields.USER_EMAIL));
		nurse.setIdRole(rs.getInt(Fields.USER_ID_ROLE));
		return nurse;
	}
	
	public User findUserByLogin(String login) throws DBException {
		Admin admin = findAdminByLogin(login);
		Patient patient = findPatientByLogin(login);
		Doctor doctor = findDoctorByLogin(login);
		Nurse nurse = findNurseByLogin(login);
		
		if (admin != null) {
			return admin;
		} else if (patient != null) {
			return patient;
		} else if (doctor != null) {
			return doctor;
		} else {
			return nurse;
		}
	}

//	private User extractUser(ResultSet rs) throws SQLException {
//		User user = new User();
//		admin.setId(rs.getLong(Fields.ENTITY_ID));
//		admin.setLogin(rs.getString(Fields.USER_LOGIN));
//		admin.setPassword(rs.getString(Fields.USER_PASSWORD));
//		admin.setName(rs.getString(Fields.USER_NAME));
//		admin.setSurname(rs.getString(Fields.USER_SURNAME));
//		admin.setIdRole(rs.getInt(Fields.USER_ID_ROLE));
//		return admin;
//	}
	
	public Category findCategoryByName(String name) throws DBException {
		Category category = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_CATEGORY_BY_NAME);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				category = extractCategory(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CATEGORY_BY_NAME, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return category;
	}

	public Category findCategoryById(int id) throws DBException {
		Category category = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_CATEGORY_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				category = extractCategory(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CATEGORY_BY_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return category;
	}
	
	private List<Admin> findAdmins() throws DBException {
		List<Admin> adminsList = new ArrayList<Admin>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_ADMINS);
			while (rs.next()) {
				adminsList.add(extractAdmin(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ADMINS, ex);
		} finally {
			close(con, stmt, rs);
		}
		return adminsList;
	}
	
	public List<Patient> findPatients() throws DBException {
		List<Patient> patientsList = new ArrayList<Patient>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_PATIENTS);
			while (rs.next()) {
				patientsList.add(extractPatient(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_PATIENTS, ex);
		} finally {
			close(con, stmt, rs);
		}
		return patientsList;
	}
	
	public List<Doctor> findDoctors() throws DBException {
		List<Doctor> doctorsList = new ArrayList<Doctor>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_DOCTORS);
			while (rs.next()) {
				doctorsList.add(extractDoctor(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_DOCTORS, ex);
		} finally {
			close(con, stmt, rs);
		}
		return doctorsList;
	}
	
	public List<DoctorCategoryBean> findDoctorsByCategory(String category) throws DBException {
		Category categ = findCategoryByName(category);
		List<DoctorCategoryBean> doctorsList = new ArrayList<DoctorCategoryBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			if (categ == null) {
				pstmt = con.prepareStatement(SQL_GET_DOCTOR_CATEGORY_BEANS);
			} else {
				pstmt = con.prepareStatement(SQL_GET_DOCTOR_CATEGORY_BEANS_BY_CATEGORY);
				pstmt.setString(1, categ.getName());
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				doctorsList.add(extractDoctorCategoryBean(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_DOCTOR_CATEGORY_BEANS, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return doctorsList;
	}
	
	public List<Nurse> findNurses() throws DBException {
		List<Nurse> nursesList = new ArrayList<Nurse>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_NURSES);
			while (rs.next()) {
				nursesList.add(extractNurse(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_NURSES, ex);
		} finally {
			close(con, stmt, rs);
		}
		return nursesList;
	}
	
	public List<Category> findCategories() throws DBException {
		List<Category> categoriesList = new ArrayList<Category>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_CATEGORIES);
			while (rs.next()) {
				categoriesList.add(extractCategory(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
		} finally {
			close(con, stmt, rs);
		}
		return categoriesList;
	}

	private Category extractCategory(ResultSet rs) throws SQLException {
		Category category = new Category();
		category.setId(rs.getLong(Fields.ENTITY_ID));
		category.setName(rs.getString(Fields.CATEGORY_NAME));
		return category;
	}
	
	public List<DoctorCategoryBean> getDoctorCategoryBeans() throws DBException {
		List<DoctorCategoryBean> beans = new ArrayList<DoctorCategoryBean>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_GET_DOCTOR_CATEGORY_BEANS);
			while (rs.next()) {
				beans.add(extractDoctorCategoryBean(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_DOCTOR_CATEGORY_BEANS, ex);
		} finally {
			close(con, stmt, rs);
		}
		return beans;
	}

	private DoctorCategoryBean extractDoctorCategoryBean(ResultSet rs) throws SQLException {
		DoctorCategoryBean bean = new DoctorCategoryBean();
		bean.setCategoryId(rs.getLong(1));
		bean.setCategoryName(rs.getString(2));
		bean.setDoctorId(rs.getLong(3));
		bean.setDoctorName(rs.getString(4));
		bean.setDoctorSurname(rs.getString(Fields.USER_SURNAME));
		bean.setDoctorDateOfBirth(rs.getDate(Fields.USER_DATE_OF_BIRTH));
		bean.setDoctorPhoneNumber(rs.getString(Fields.USER_PHONE_NUMBER));
		bean.setDoctorEmail(rs.getString(Fields.USER_EMAIL));
		bean.setDoctorIdRole(rs.getInt(Fields.USER_ID_ROLE));
		bean.setDoctorLogin(rs.getString(Fields.USER_LOGIN));
		try {
			bean.setPatientsNumber(countPatientsOfDoctor(bean));
		} catch (DBException e) { }
		return bean;
	}

	public void appoint(Patient pat, DoctorCategoryBean doc) throws DBException {
		if ((pat.getSex().equals("M")) && (doc.getCategoryName().equals("gynecologist"))) {
			throw new DBException("Only female patients can visit a gynecologist");
		}
		boolean isCard = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_CARDS_BY_IDS);
			pstmt.setLong(1, pat.getId());
			pstmt.setLong(2, doc.getDoctorId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				isCard = true;
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_ADD_SUCH_CARD, ex);
		} finally {
			close(con, pstmt, rs);
			if (isCard) {
				throw new DBException("There is such appointment already");
			}
		}
		
		pstmt = null;
		con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_ADD_NEW_CARD);
			pstmt.setLong(1, getMaxId("cards") + 1);
			pstmt.setLong(2, pat.getId());
			pstmt.setLong(3, doc.getDoctorId());
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_ADD_SUCH_CARD, ex);
		} finally {
			close(con, pstmt, null);
		}
	}
	
	public Patient addNewPatient(HttpServletRequest request) throws DBException {
		checkLogin(request.getParameter("login"));
		Patient patient = null;
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_INSERT_NEW_PATIENT);
			pstmt.setLong(1, getMaxId("patients") + 1);
			pstmt.setString(2, request.getParameter("name"));
			pstmt.setString(3, request.getParameter("surname"));
			pstmt.setString(4, request.getParameter("date_of_birth"));
			pstmt.setString(5, request.getParameter("sex"));
			pstmt.setString(6, request.getParameter("address"));
			pstmt.setString(7, request.getParameter("phone_number"));
			pstmt.setString(8, request.getParameter("email"));
			pstmt.setString(9, request.getParameter("login"));
			pstmt.setString(10, request.getParameter("password"));
			pstmt.executeUpdate();
			con.commit();
			patient = findPatientByLogin(request.getParameter("login"));
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_ADD_SUCH_USER, ex);
		} finally {
			close(con, pstmt, null);
		}
		return patient;
	}

	public Doctor addNewDoctor(HttpServletRequest request) throws DBException {
		checkLogin(request.getParameter("login"));
		Doctor doc = null;
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_INSERT_NEW_DOCTOR);
			pstmt.setLong(1, getMaxId("doctors") + 1);
			pstmt.setString(2, request.getParameter("name"));
			pstmt.setString(3, request.getParameter("surname"));
			pstmt.setString(4, request.getParameter("date_of_birth"));
			pstmt.setString(5, request.getParameter("phone_number"));
			pstmt.setString(6, request.getParameter("email"));
			pstmt.setString(7, request.getParameter("login"));
			pstmt.setString(8, request.getParameter("password"));
			pstmt.setInt(9, Integer.parseInt(request.getParameter("category")));
			pstmt.executeUpdate();
			con.commit();
			doc = findDoctorByLogin(request.getParameter("login"));
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_ADD_SUCH_USER, ex);
		} catch (NumberFormatException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_ADD_SUCH_USER, ex);
		} finally {
			close(con, pstmt, null);
		}
		return doc;
	}
	
	public Nurse addNewNurse(HttpServletRequest request) throws DBException {
		checkLogin(request.getParameter("login"));
		Nurse nurse = null;
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_INSERT_NEW_NURSE);
			pstmt.setLong(1, getMaxId("nurses") + 1);
			pstmt.setString(2, request.getParameter("name"));
			pstmt.setString(3, request.getParameter("surname"));
			pstmt.setString(4, request.getParameter("date_of_birth"));
			pstmt.setString(5, request.getParameter("phone_number"));
			pstmt.setString(6, request.getParameter("email"));
			pstmt.setString(7, request.getParameter("login"));
			pstmt.setString(8, request.getParameter("password"));
			pstmt.executeUpdate();
			con.commit();
			nurse = findNurseByLogin(request.getParameter("login"));
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_ADD_SUCH_USER, ex);
		} finally {
			close(con, pstmt, null);
		}
		return nurse;
	}

	public long getMaxId(String tableName) throws DBException {
		long result = -1;
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_GET_MAX_ID + tableName);
			if (rs.next()) {
				result = rs.getLong(1);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_GET_MAX_ID + tableName, ex);
		} finally {
			close(con, stmt, rs);
		}
		return result;
	}
	
	public void deletePatientById(long id) throws DBException {
		int result = 0;
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_DELETE_PATIENT_BY_ID);
			pstmt.setLong(1, id);
			result = pstmt.executeUpdate();
			con.commit();
			if (result == 0) {
				throw new SQLException();
			}
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_DELETE_SUCH_USER, ex);
		} finally {
			close(con, pstmt, null);
		}
	}
	
	public void deleteDoctorById(long id) throws DBException {
		int result = 0;
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_DELETE_DOCTOR_BY_ID);
			pstmt.setLong(1, id);
			result = pstmt.executeUpdate();
			con.commit();
			if (result == 0) {
				throw new SQLException();
			}
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_DELETE_SUCH_USER, ex);
		} finally {
			close(con, pstmt, null);
		}
	}
	
	public void deleteNurseById(long id) throws DBException {
		int result = 0;
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_DELETE_NURSE_BY_ID);
			pstmt.setLong(1, id);
			result = pstmt.executeUpdate();
			con.commit();
			if (result == 0) {
				throw new SQLException();
			}
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_DELETE_SUCH_USER, ex);
		} finally {
			close(con, pstmt, null);
		}
	}
	
	public Patient dischargePatientByLogin(String login) throws DBException {
		Patient pat = findPatientByLogin(login);
		PreparedStatement pstmt = null;
		Connection con = null;
		
		if (pat == null) {
			throw new DBException(Messages.ERR_CANNOT_DISCHARGE_SUCH_PATIENT);
		}

		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_DISCHARGE_PATIENT_BY_ID_1);
			pstmt.setLong(1, pat.getId());
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_DISCHARGE_SUCH_PATIENT, ex);
		} finally {
			close(con, pstmt, null);
		}
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_DISCHARGE_PATIENT_BY_ID_2);
			pstmt.setLong(1, pat.getId());
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_DISCHARGE_SUCH_PATIENT, ex);
		} finally {
			close(con, pstmt, null);
		}
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_DISCHARGE_PATIENT_BY_ID_3);
			pstmt.setLong(1, pat.getId());
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_DISCHARGE_SUCH_PATIENT, ex);
		} finally {
			close(con, pstmt, null);
		}
		return pat;
	}
	
	private void checkLogin(String login) throws DBException {
		Iterator<Admin> ita = findAdmins().iterator();
		Iterator<Patient> itp = findPatients().iterator();
		Iterator<Doctor> itd = findDoctors().iterator();
		Iterator<Nurse> itn = findNurses().iterator();
		ArrayList<String> allLogins = new ArrayList<String>();
		
		while (ita.hasNext()) {
			allLogins.add(ita.next().getLogin());
		}
		while (itp.hasNext()) {
			allLogins.add(itp.next().getLogin());
		}
		while (itd.hasNext()) {
			allLogins.add(itd.next().getLogin());
		}
		while (itn.hasNext()) {
			allLogins.add(itn.next().getLogin());
		}
		
		if (allLogins.contains(login)) {
			throw new DBException("There is user with such login in the base.");
		}
	}
	
	private void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException ex) { }
		}
	}
	
	private void close(Connection con, Statement stmt, ResultSet rs) {
		close(rs);
		close(stmt);
		close(con);
	}
	
	private void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException ex) { }
		}
	}

	private void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) { }
		}
	}

	private void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) { }
		}
	}
}
