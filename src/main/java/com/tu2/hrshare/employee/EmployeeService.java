package com.tu2.hrshare.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    /**
     * Class constructor for the EmployeeService
     * @param employeeRepository
     */
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Function to get all employees in the database.
     * @return
     */
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    /**
     * Insert a new employee object into the database.
     * @param employee
     */
    public void addNewEmployee(Employee employee) {
        Optional<Employee> employeeByEmail = employeeRepository.findEmployeeByEmail(employee.getEmail());
        if(employeeByEmail.isPresent()){
            throw new IllegalStateException("E-Mail taken");
        }
        employeeRepository.save(employee);
    }
}
