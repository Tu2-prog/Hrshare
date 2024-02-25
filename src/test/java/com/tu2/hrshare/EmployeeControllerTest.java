package com.tu2.hrshare;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tu2.hrshare.employee.Employee;
import com.tu2.hrshare.employee.EmployeeController;
import com.tu2.hrshare.employee.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    void testGetEmployees() {
        List<Employee> employees = new ArrayList<>();
        // Add some dummy employees
        employees.add(new Employee(1L, "John Doe", "john@example.com", LocalDate.of(2000, 3, 5)));
        employees.add(new Employee(2L, "Jane Doe", "jane@example.com", LocalDate.of(2000, 3 , 4)));

        when(employeeService.getEmployees()).thenReturn(employees);

        List<Employee> returnedEmployees = employeeController.getEmployees();

        assertEquals(2, returnedEmployees.size());
    }

    @Test
    void testGetSingleEmployee() {
        Long id = 1L;
        Employee employee = new Employee(id, "John Doe", "john@example.com", LocalDate.of(2000, 3, 5));

        when(employeeService.getEmployeeById(id)).thenReturn(Optional.of(employee));

        ResponseEntity<Employee> responseEntity = employeeController.getSingleEmployee(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(employee, responseEntity.getBody());
    }

    @Test
    void testPostEmployee() {
        Employee employee = new Employee(1L, "John Doe", "john@example.com", LocalDate.of(2000, 3, 5));

        employeeController.postEmployee(employee);

        verify(employeeService).addNewEmployee(employee);
    }

    @Test
    void testUpdateEmployee() {
        long id = 1L;
        Employee existingEmployee = new Employee(id, "John Doe", "john@example.com", LocalDate.of(2000, 3, 5));
        Employee updatedEmployee = new Employee(id, "John Updated", "john.updated@example.com", LocalDate.of(2000, 3, 5));

        when(employeeService.getEmployeeById(id)).thenReturn(Optional.of(existingEmployee));

        ResponseEntity<Employee> responseEntity = employeeController.updateEmployee(id, updatedEmployee);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedEmployee, responseEntity.getBody());
    }

    @Test
    void testDeleteEmployee() {
        Long id = 1L;
        employeeController.deleteEmployee(id);

        verify(employeeService).deleteEmployeeId(id);
    }
}

