package com.rikhil.service;

import com.rikhil.DAO.EmployeeDAO;
import com.rikhil.model.Employee;

import java.util.List;

public class EmployeeService {
    private final EmployeeDAO employeeDAO;

    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public List<Employee> getAllEmployees() {
        return employeeDAO.getEmployees();
    }

    public Employee getEmployeeById(int id) {
        return employeeDAO.getEmployeeById(id);
    }

    public void addEmployee(Employee employee) {
        // Perform any business logic or validation before saving
        employeeDAO.save(employee);
    }

    public void updateEmployee(Employee employee) {
        // Perform any business logic or validation before updating
        employeeDAO.update(employee);
    }

    public void deleteEmployee(Employee employee) {
        // Perform any business logic or validation before deleting
        employeeDAO.delete(employee);
    }
}
