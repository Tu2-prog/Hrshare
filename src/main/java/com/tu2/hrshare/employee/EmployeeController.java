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

    /**
     * Endpoint to update a whole employee or update it partially and save it in the database.
     * @param id The unique identifier of the respective employee.
     * @param updatedEmployee The employee with possibly new data that is used to update the database entry.
     * @return Updated element or error code if the object cannot be found.
     */
    @PatchMapping("/api/v1/update/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        Optional<Employee> employeeOptional = employeeService.getEmployeeById(id);

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();

            employee.setName(updatedEmployee.getName());
            employee.setEmail(updatedEmployee.getEmail());
            employee.setDateOfBirth(updatedEmployee.getDateOfBirth());

            employeeService.updateEmployee(employee);

            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DeleteMapping to delete an employee by ID.
     * After deletion, it redirects to the home page.
     * @param id The ID of the employee to delete.
     */
    @DeleteMapping("api/v1/delete/employee/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployeeId(id);
    }
    
}
