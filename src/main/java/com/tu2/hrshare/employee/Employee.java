package com.tu2.hrshare.employee;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/**
 * Entity class modelling an employee in the company.
 */
@Entity
@Table
public class Employee {

    /**
     * Default constructor for the Employee class.
     * return Employee.
     */
    public Employee(){}

    @Id
    @SequenceGenerator(
        name = "employee_sequence",
        sequenceName = "employee_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "employee_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private LocalDate dateOfBirth;

    @Transient
    private Integer age;

    /**
     * Class constructor for Employee entity.
     * @param email String equaling the work email of the employee.
     * @param dateOfBirth Date when the respective employee was born.
     */
    public Employee(long id, String name, String email, LocalDate dateOfBirth){
        this.id = id;
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }
    
    /**
     * Class constructor for the Employee without id.
     * @param name The full name of the employee.
     * @param email String equaling the work email of the employee.
     * @param dateOfBirth Date when the respective employee was born.
     */
    public Employee(String name, String email, LocalDate dateOfBirth) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }


    
    /** Getter function for ID
     * @return long
     */
    public long getId() {
        return id;
    }

    
    /** Setter function for ID
     * @param id The new id of the employee.
     */
    public void setId(long id) {
        this.id = id;
    }

    
    /** Getter function for Name
     * @return String
     */
    public String getName() {
        return name;
    }

    
    /** Setter function for Name
     * @param name The new name for the employee.
     */
    public void setName(String name) {
        this.name = name;
    }

    
    /** Getter function for email 
     * @return String
     */
    public String getEmail() {
        return email;
    }

    
    /** Setter function for email
     * @param email The new email for the employee.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    
    /** Getter function for the data of birth 
     * @return LocalDate
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    
    /** Setter function for the data of birth
     * @param dateOfBirth The new date of Birth for the employee.
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    
    /** Getter function for the Age 
     * @return Integer
     */
    public Integer getAge() {
        return (int) ChronoUnit.YEARS.between(this.dateOfBirth, LocalDate.now());
    }

    
    /** Setter function for the age
     * @param age The new age for the employee.
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    
    /** Instance operation to print out the object's attributes and its values.
     * @return String
     */
    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", dateOfBirth=" + dateOfBirth + ", age="
                + age + "]";
    }
}
