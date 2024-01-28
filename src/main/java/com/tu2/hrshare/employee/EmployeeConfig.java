package com.tu2.hrshare.employee;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class EmployeeConfig {

    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository repository){
        return args -> {
            Employee mariam = new Employee("Mariam", "mariam@mariam.com", LocalDate.of(2000, Month.JANUARY, 5));
            Employee alex = new Employee("Alex", "alex@gmail.com", LocalDate.of(2003, Month.JANUARY, 5));
            repository.saveAll(List.of(mariam, alex));
        };
    }
}
