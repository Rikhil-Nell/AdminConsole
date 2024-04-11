package com.rikhil.DAO;

import com.rikhil.model.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    private Connection connection;

    //constructor for establishing connection

    public EmployeeDAOImpl(Connection connection) {
        this.connection = connection;
    }

    public void save(Employee employee) {
        String query = "insert into employees (id, name, salary) values(?,?,?)";
        try(PreparedStatement statement = connection.prepareStatement(query)){

            statement.setInt(1,employee.getId());
            statement.setString(2,employee.getName());
            statement.setInt(3,employee.getId());
            statement.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
            //more exception handling required*
        }
    }

    public void update(Employee employee) {
        String query = "UPDATE employees SET name = ?, salary = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, employee.getName());
            statement.setDouble(2, employee.getSalary());
            statement.setInt(3, employee.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }
    }

    @Override
    public void delete(Employee employee) {
        String query = "DELETE FROM employees WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, employee.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }
    }

    public Employee getEmployeeById(int id) {
        String query = "SELECT * FROM employees WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                return new Employee(id, name, salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }
        return null;
    }

    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employees";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                employees.add(new Employee(id, name, salary));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }
        return employees;
    }
}
