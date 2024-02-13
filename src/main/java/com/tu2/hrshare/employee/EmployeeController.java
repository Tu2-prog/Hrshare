package com.tu2.hrshare.employee;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controller class handling requests to interact with the Employee database.
 */
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    /**
     * Class constructor for the EmployeeController class.
     * @param employeeService The service providing access to the employee data.
     */
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Endpoint for retrieving all employees from the database.
     * @return List<Employee>
     */
    @GetMapping("/api/v1/employees")
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    /***
     * Endpoint to insert a new employee into the database.
     * @param employee The new Employee object to be added into the database.
     */
    @PostMapping("/api/v1/new/employee")
    public void postEmployee(@RequestBody Employee employee) {        
        employeeService.addNewEmployee(employee);
    }
    
}
