package com.tu2.hrshare.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class providing helper methods interacting with the employee database.
 */
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    /**
     * Class constructor for the EmployeeService
     * @param employeeRepository The Spring Data Repository for the Employee database.
     */
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Function to get all employees in the database.
     * @return List<Employee>
     */
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    /**
     * Insert a new employee object into the database.
     * @param employee The new employee to be added into the database.
     */
    public void addNewEmployee(Employee employee) {
        Optional<Employee> employeeByEmail = employeeRepository.findEmployeeByEmail(employee.getEmail());
        if(employeeByEmail.isPresent()){
            throw new IllegalStateException("E-Mail taken");
        }
        employeeRepository.save(employee);
    }
}
