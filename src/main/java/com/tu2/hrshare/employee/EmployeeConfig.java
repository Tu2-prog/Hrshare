package com.tu2.hrshare.employee;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeConfig {

    
    /** Function to configure the employee database at start.
     * @param repository
     * @return CommandLineRunner
     */
    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository repository){
        return args -> {

        };
    }
}
