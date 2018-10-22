package ua.nure.hrynevych.SummaryTask4.web.command;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.hrynevych.SummaryTask4.Path;
import ua.nure.hrynevych.SummaryTask4.db.DBManager;
import ua.nure.hrynevych.SummaryTask4.db.bean.DoctorCategoryBean;
import ua.nure.hrynevych.SummaryTask4.db.entity.Category;
import ua.nure.hrynevych.SummaryTask4.exception.AppException;

public class ListDoctorsCommand extends Command {

	private static final long serialVersionUID = -8327555115189475914L;

	private static class CompareBySurname implements Comparator<DoctorCategoryBean>, Serializable {

		private static final long serialVersionUID = 4446973438000106138L;

		public int compare(DoctorCategoryBean doc1, DoctorCategoryBean doc2) {
			return doc1.getDoctorSurname().compareTo(doc2.getDoctorSurname());
		}
	}
	
	private static class CompareByCategory implements Comparator<DoctorCategoryBean>, Serializable {

		private static final long serialVersionUID = -6533306986666651810L;

		public int compare(DoctorCategoryBean doc1, DoctorCategoryBean doc2) {
			return doc1.getCategoryName().compareTo(doc2.getCategoryName());
		}
	}
	
	private static class CompareByPatients implements Comparator<DoctorCategoryBean>, Serializable {

		private static final long serialVersionUID = -1018055517793725893L;

		public int compare(DoctorCategoryBean doc1, DoctorCategoryBean doc2) {
			return doc2.getPatientsNumber() - doc1.getPatientsNumber();
		}
	}
	
	private static Comparator<DoctorCategoryBean> compareBySurname = new CompareBySurname();
	private static Comparator<DoctorCategoryBean> compareByCategory = new CompareByCategory();
	private static Comparator<DoctorCategoryBean> compareByPatients = new CompareByPatients();
			
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException, AppException {
		
		String sort = request.getParameter("sort");
		String category = request.getParameter("category");
		List<DoctorCategoryBean> doctorList = DBManager.getInstance().findDoctorsByCategory(category);
		List<Category> categoryList = DBManager.getInstance().findCategories();
		
		if ((sort != null) && (sort.equals("surname"))) {
			Collections.sort(doctorList, compareBySurname);
		} else if ((sort != null) && (sort.equals("category"))) {
			Collections.sort(doctorList, compareByCategory);
		} else if ((sort != null) && (sort.equals("patients"))) {
			Collections.sort(doctorList, compareByPatients);
		}
		
		// put user order beans list to request
		request.setAttribute("doctorList", doctorList);
		request.setAttribute("categoryList", categoryList);
		return Path.PAGE_LIST_DOCTORS;
	}
}