package com.rikhil;

import com.rikhil.DAO.EmployeeDAO;
import com.rikhil.DAO.EmployeeDAOImpl;
import com.rikhil.model.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //DB credentials
        String url = System.getenv("DB_URL");
        String username = System.getenv("DB_USERNAME");
        String password = System.getenv("DB_PASSWORD");

        try (Connection connection = DriverManager.getConnection
                (url, username, password)) {
            EmployeeDAO employeeDAO = new EmployeeDAOImpl(connection);

            Scanner scanner = new Scanner(System.in);
            int choice;
            do {
                System.out.println("Employee Management System");
                System.out.println("1. Add Employee");
                System.out.println("2. Find Employee by ID");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addEmployee(employeeDAO, scanner);
                        break;
                    case 2:
                        findEmployeeById(employeeDAO, scanner);
                        break;
                    case 3:
                        updateEmployee(employeeDAO, scanner);
                        break;
                    case 4:
                        deleteEmployee(employeeDAO, scanner);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 5);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addEmployee(EmployeeDAO employeeDAO, Scanner scanner) {
        System.out.print("Enter employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();
        System.out.print("Enter employee salary: ");
        double salary = scanner.nextDouble();
        Employee newEmployee = new Employee(id, name, salary);
        employeeDAO.save(newEmployee);
        System.out.println("Employee added successfully.");
    }

    private static void findEmployeeById(EmployeeDAO employeeDAO, Scanner scanner) {
        System.out.print("Enter employee ID to find: ");
        int id = scanner.nextInt();
        Employee foundEmployee = employeeDAO.getEmployeeById(id);
        if (foundEmployee != null) {
            System.out.println("Found employee: " + foundEmployee.getName());
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void updateEmployee(EmployeeDAO employeeDAO, Scanner scanner) {
        System.out.print("Enter employee ID to update: ");
        int id = scanner.nextInt();
        Employee existingEmployee = employeeDAO.getEmployeeById(id);
        if (existingEmployee != null) {
            scanner.nextLine(); // Consume newline character
            System.out.print("Enter new name: ");
            String newName = scanner.nextLine();
            System.out.print("Enter new salary: ");
            double newSalary = scanner.nextDouble();
            existingEmployee.setName(newName);
            existingEmployee.setSalary(newSalary);
            employeeDAO.update(existingEmployee);
            System.out.println("Employee updated successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void deleteEmployee(EmployeeDAO employeeDAO, Scanner scanner) {
        System.out.print("Enter employee ID to delete: ");
        int id = scanner.nextInt();
        Employee employeeToDelete = employeeDAO.getEmployeeById(id);
        if (employeeToDelete != null) {
            employeeDAO.delete(employeeToDelete);
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }
}
