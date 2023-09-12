package com.servlets;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;


import com.helper.FactoryProvider;
import com.vignan.Employee; // Make sure to import your Employee entity class

@WebServlet("/updateEmployeeServlet")
public class updateEmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve data from the form
        int employeeId = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        Session session = FactoryProvider.getFactory().openSession();

        try {
            // Begin a transaction
            session.beginTransaction();

            // Retrieve the employee with the given ID from the database
            Employee employee = session.get(Employee.class, employeeId);

            // Update the employee's information
            if (employee != null) {
                employee.setFirstName(firstName);
                employee.setLastName(lastName);

                // Save the updated employee to the database
                session.merge(employee);
            } else {
            	response.setContentType("text/html");
        		PrintWriter out = response.getWriter();
            	out.println("<h1 style='text-align:center;'>Employee Not Found</h1>");
        		out.println("<h1 style='text-align:center;'><a href='listEmployees.jsp'>View all Employee</a></h1>");
            }

            // Commit the transaction
            session.getTransaction().commit();
        } finally {
            session.close();
        }

        // Redirect to a success page or a list of employees
        response.sendRedirect("listEmployees.jsp");
    }
}
