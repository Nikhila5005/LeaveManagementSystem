package com.leaves.leavedemo.services;

import com.leaves.leavedemo.entities.Employee;
import com.leaves.leavedemo.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Fetch all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Fetch a specific employee by ID
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    // Fetch a specific employee by name
    public Optional<Employee> getEmployeeByName(String name) {
        return employeeRepository.findByName(name);
    }

    // Create a new employee
    @Transactional
    public Employee createEmployee(Employee employee) {
        if (isEmailValid(employee.getEmail())) {
            return employeeRepository.save(employee);
        } else {
            throw new IllegalArgumentException("Invalid email format for employee");
        }
    }

    // Create multiple employees
    @Transactional
    public List<Employee> createEmployees(List<Employee> employees) {
        for (Employee employee : employees) {
            if (!isEmailValid(employee.getEmail())) {
                throw new IllegalArgumentException("Invalid email format for employee: " + employee.getEmail());
            }
        }
        return employeeRepository.saveAll(employees);
    }

    // Update an existing employee
    @Transactional
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        return employeeRepository.findById(id).map(existingEmployee -> {
            if (isDesignationAllowed(updatedEmployee.getDesignation())) {
                existingEmployee.setName(updatedEmployee.getName());
                existingEmployee.setDesignation(updatedEmployee.getDesignation());
                existingEmployee.setEmail(updatedEmployee.getEmail());
                return employeeRepository.save(existingEmployee);
            } else {
                throw new IllegalArgumentException("Invalid designation for employee");
            }
        }).orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));
    }

    // Update an existing employee by name
    @Transactional
    public Employee updateEmployeeByName(String name, Employee updatedEmployee) {
        return employeeRepository.findByName(name).map(existingEmployee -> {
            if (isDesignationAllowed(updatedEmployee.getDesignation())) {
                existingEmployee.setName(updatedEmployee.getName());
                existingEmployee.setDesignation(updatedEmployee.getDesignation());
                existingEmployee.setEmail(updatedEmployee.getEmail());
                return employeeRepository.save(existingEmployee);
            } else {
                throw new IllegalArgumentException("Invalid designation for employee");
            }
        }).orElseThrow(() -> new RuntimeException("Employee not found with name: " + name));
    }

    // Delete an employee
    @Transactional
    public void deleteEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employeeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Employee not found with ID: " + id);
        }
    }

    // Delete an employee by name
    @Transactional
    public void deleteEmployeeByName(String name) {
        Optional<Employee> employee = employeeRepository.findByName(name);
        if (employee.isPresent()) {
            employeeRepository.delete(employee.get());
        } else {
            throw new RuntimeException("Employee not found with name: " + name);
        }
    }

    // Additional helper methods for business logic
    private boolean isEmailValid(String email) {
        return email != null && email.contains("@") && email.endsWith(".com");
    }

    private boolean isDesignationAllowed(String designation) {
        return designation.equalsIgnoreCase("Manager") ||
                designation.equalsIgnoreCase("Developer") ||
                designation.equalsIgnoreCase("HR");
    }
}
