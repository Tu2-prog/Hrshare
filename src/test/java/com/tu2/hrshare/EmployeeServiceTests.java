package com.tu2.hrshare;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tu2.hrshare.employee.Employee;
import com.tu2.hrshare.employee.EmployeeRepository;
import com.tu2.hrshare.employee.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void testGetEmployees() {
        // Prepare mock data
        List<Employee> mockEmployees = new ArrayList<>();
        mockEmployees.add(new Employee(1, "John", "John@gmail.com", LocalDate.of(1999, 1, 8)));
        mockEmployees.add(new Employee(2, "Alice", "Alice@gmail", LocalDate.of(1999, 1, 8)));

        // Mock the behavior of the employeeRepository
        when(employeeRepository.findAll()).thenReturn(mockEmployees);

        // Call the service method
        List<Employee> result = employeeService.getEmployees();

        // Verify the result
        assertEquals(2, result.size());
    }

    @Test
    void testGetEmployeeById() {
        // Prepare mock data
        Employee mockEmployee = new Employee("John", "\"john@example.com\"", LocalDate.of(1999, 1, 8));
        Optional<Employee> mockOptionalEmployee = Optional.of(mockEmployee);

        // Mock the behavior of the employeeRepository
        when(employeeRepository.findEmployeeById(1L)).thenReturn(mockOptionalEmployee);

        // Call the service method
        Optional<Employee> result = employeeService.getEmployeeById(1L);

        // Verify the result
        assertEquals(mockOptionalEmployee, result);
    }

    // Add tests for other methods: addNewEmployee, updateEmployee, deleteEmployeeId
    // Ensure to cover various scenarios, including success and failure cases

    @Test
    void testAddNewEmployee(){
        Employee mockEmployee = new Employee(1, "Bob", "existing@example.com", LocalDate.of(2000, 3, 4));
        when(employeeRepository.findEmployeeByEmail("existing@example.com"))
                .thenReturn(Optional.of(mock()));

        assertThrows(IllegalStateException.class, () -> employeeService.addNewEmployee(mockEmployee));
    }

    @Test
    void testAddNewEmployeeEmailNotTaken(){
        Employee mockEmployee = new Employee(1, "Bob", "existing@example.com", LocalDate.of(2000, 3, 4));

        employeeService.addNewEmployee(mockEmployee);
        verify(employeeRepository, Mockito.times(1)).save(mockEmployee);
    }

    @Test
    void testUpdateEmployee() {
        // Create sample employee data
        Employee existingEmployee = new Employee();
        existingEmployee.setId(1L);
        existingEmployee.setName("John Doe");
        existingEmployee.setAge(30);
        existingEmployee.setEmail("john.doe@example.com");
        existingEmployee.setDateOfBirth(LocalDate.of(1992, 5, 10));

        Employee updatedEmployee = new Employee();
        updatedEmployee.setId(1L);
        updatedEmployee.setName("Jane Doe");
        updatedEmployee.setAge(35);
        updatedEmployee.setEmail("jane.doe@example.com");
        updatedEmployee.setDateOfBirth(LocalDate.of(1989, 3, 15));

        // Mock behavior of repository
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(existingEmployee));

        // Call the method to be tested
        employeeService.updateEmployee(updatedEmployee);

        // Verify that findById and save methods are called
        verify(employeeRepository).findById(1L);
        verify(employeeRepository).save(existingEmployee);

        // Assert that the existing employee is updated with new values
        assertEquals("Jane Doe", existingEmployee.getName());
        assertEquals(34, existingEmployee.getAge());
        assertEquals("jane.doe@example.com", existingEmployee.getEmail());
        assertEquals(LocalDate.of(1989, 3, 15), existingEmployee.getDateOfBirth());
    }

    @Test
    void testDeleteEmployeeId_Success() {
        // Mock data
        Long employeeId = 1L;
        Employee employee = new Employee();
        employee.setId(employeeId);
        when(employeeService.getEmployeeById(employeeId)).thenReturn(Optional.of(employee));
        employeeService.deleteEmployeeId(employeeId);
        verify(employeeRepository, times(1)).delete(employee);
        Optional<Employee> deletedEmployee = employeeRepository.findById(employeeId);
        assertFalse(deletedEmployee.isPresent(), "Employee should not exist after deletion");
    }
}

