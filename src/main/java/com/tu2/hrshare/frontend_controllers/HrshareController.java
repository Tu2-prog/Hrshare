package com.tu2.hrshare.frontend_controllers;

import com.tu2.hrshare.employee.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;


/**
 * Controller for handling request on the frontend of the application.
 */
@Controller
public class HrshareController {

    /**
     * Endpoint for the home page.
     * @param model Model to add properties into the application.
     * @return Name of the html one needs to navigate to.
     */
    @GetMapping("/")
    public  String index(Model model){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee[]> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/v1/employees", Employee[].class);
        Employee[] employees = responseEntity.getBody();
        model.addAttribute("employees", employees);
        return "index";
    }

    /**
     * Endpoint leading to the create page of hrshare.
     * @return Name of the view.
     */
    @GetMapping("/create")
    public String create(){
        return "create";
    }

    /**
     * Endpoint to the update page of an object.
     * @param id The identifier of the object.
     * @param model Model to parse the employee into the single view.
     * @return Name of the view.
     */
    @GetMapping("/update/{id}")
    public  String update(@PathVariable Long id, Model model){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/v1/employee/{id}", Employee.class, id);
        Employee employee = responseEntity.getBody();
        model.addAttribute("employee", employee);
        return "update";
    }
}
