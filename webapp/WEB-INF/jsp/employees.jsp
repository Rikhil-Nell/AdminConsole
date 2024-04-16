<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.rikhil.model.Employee" %>
<%@ page import="com.rikhil.service.EmployeeService" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Management System</title>
</head>
<body>
<h1>Employee List</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Salary</th>
    </tr>
    <%
        EmployeeService employeeService = new EmployeeService();
        List<Employee> employeeList = employeeService.getAllEmployees();
        for (Employee employee : employeeList) {
    %>
    <tr>
        <td><%= employee.getId() %></td>
        <td><%= employee.getName() %></td>
        <td><%= employee.getSalary() %></td>
    </tr>
    <% } %>
</table>
</body>
</html>
