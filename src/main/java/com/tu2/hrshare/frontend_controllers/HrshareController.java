package com.tu2.hrshare.frontend_controllers;

import com.tu2.hrshare.employee.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

/**
 * Controller for handling request on the frontend of the application.
 */
@Controller
public class HrshareController {

    @GetMapping("/")
    public  String index(Model model){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee[]> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/v1/employees", Employee[].class);
        Employee[] employees = responseEntity.getBody();
        model.addAttribute("employees", employees);
        return "index";
    }

    @GetMapping("/create")
    public String create(){
        return "create";
    }
}
