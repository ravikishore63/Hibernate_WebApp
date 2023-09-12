package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.helper.FactoryProvider;
import com.vignan.Employee;

@WebServlet("/createEmployeeServlet")
public class createEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public createEmployeeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");

		Employee employee = new Employee();
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		Session session = FactoryProvider.getFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.persist(employee);
		tx.commit();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h1 style='text-align:center;'>Employee is added successfully</h1>");
		out.println("<h1 style='text-align:center;'><a href='listEmployees.jsp'>View all Employee</a></h1>");
	}

}
