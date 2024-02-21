package com.tu2.hrshare.employee;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data Repository interface for the Employee database.
 */
@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Long>{

    @Query("SELECT e FROM Employee e WHERE e.email = ?1")
    Optional<Employee> findEmployeeByEmail(String email);

    Optional<Employee> findEmployeeById(Long id);
}
