<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="org.hibernate.SessionFactory" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="com.vignan.Employee" %>
<%@ page import="com.helper.FactoryProvider" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Employee</title>
    <style>
        /* Add your CSS styles here */
        body {
            font-family: Arial, sans-serif;
        }
        h1 {
            color: #333;
        }
        form {
            max-width: 400px;
            margin: 0 auto;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h1>Update Employee</h1>
    
    <h2>List of Employees</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
        </tr>
        
        <% 
        Session session2 = FactoryProvider.getFactory().openSession();
            
            try {
                session2.beginTransaction();
                List<Employee> employees = session2.createQuery("from Employee").getResultList();
                
                for (Employee employee : employees) {
        %>
                <tr>
                    <td><%= employee.getId() %></td>
                    <td><%= employee.getFirstName() %></td>
                    <td><%= employee.getLastName() %></td>
                </tr>
        <%
                }
                session2.getTransaction().commit();
            } finally {
            	session2.close();
            }
        %>
    </table>
    
    <h2>Update Employee</h2>
    <form action="updateEmployeeServlet" method="post">
        <label for="id">Employee ID:</label>
        <input type="text" name="id" required>
        
        <label for="firstName">First Name:</label>
        <input type="text" name="firstName" required>
        
        <label for="lastName">Last Name:</label>
        <input type="text" name="lastName" required>
        
        <input type="submit" value="Update Employee">
    </form>
</body>
</html>
