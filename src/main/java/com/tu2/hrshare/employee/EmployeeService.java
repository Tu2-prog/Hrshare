package com.tu2.hrshare.employee;

import java.util.Comparator;
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
        List<Employee> employees = employeeRepository.findAll();
        employees.sort(Comparator.comparingLong(Employee::getId));
        return employees;
    }

    /**
     * Helper function to get a single employee by id.
     * @param id The id of the desired employee.
     * @return Optional<Employee>
     */
    public Optional<Employee> getEmployeeById(Long id) { return  employeeRepository.findEmployeeById(id); }

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

    public void updateEmployee(Employee updatedEmployee) {
        // Check if the employee exists in the database
        Optional<Employee> existingEmployeeOptional = employeeRepository.findById(updatedEmployee.getId());

        if (existingEmployeeOptional.isPresent()) {
            Employee existingEmployee = existingEmployeeOptional.get();

            // Update the fields of the existing employee with the new values
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setAge(updatedEmployee.getAge());
            existingEmployee.setEmail(updatedEmployee.getEmail());
            existingEmployee.setDateOfBirth(updatedEmployee.getDateOfBirth());
            // Save the updated employee to the database
            employeeRepository.save(existingEmployee);
        }
    }
}
