package com.rikhil.DAO;

import com.rikhil.model.Employee;
import java.util.List;

public interface EmployeeDAO {

    void save(Employee employee);
    void update(Employee employee);
    void delete(Employee employee);
    Employee getEmployeeById(int id);
    List<Employee> getEmployees();
}
