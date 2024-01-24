package com.tu2.hrshare.employee;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    public List<Employee> getEmployees() {
        return List.of(
            new Employee(1L,
                         "Mariam", 
                         "mariam@mariam.com", 
                         LocalDate.of(2000, Month.APRIL, 5),
                         21
            )
        );
    }
}
