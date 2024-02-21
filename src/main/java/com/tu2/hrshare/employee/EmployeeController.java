package com.tu2.hrshare.employee;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    /**
     * Endpoint for retrieving a single employee from the database
     * @param id Path variable to determine the correct employee.
     * @return Optional<Employee>
     */
    @GetMapping("/api/v1/employee/{id}")
    public ResponseEntity<Employee> getSingleEmployee(@PathVariable Long id){
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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
