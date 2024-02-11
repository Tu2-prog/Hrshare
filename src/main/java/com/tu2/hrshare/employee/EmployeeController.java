package com.tu2.hrshare.employee;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    /**
     * Class constructor for the Employeeservice.
     * @param employeeService
     */
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Endpoint for retrieving all employees from the database.
     * @return
     */
    @GetMapping("/api/v1/employees")
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    /***
     * Endpoint to insert a new employee into the database.
     * @param employee
     */
    @PostMapping("/api/v1/new/employee")
    public void postEmployee(@RequestBody Employee employee) {        
        employeeService.addNewEmployee(employee);
    }
    
}
