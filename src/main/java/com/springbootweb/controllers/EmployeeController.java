package com.springbootweb.controllers;

import com.springbootweb.dto.EmployeeDTO;
import com.springbootweb.entities.EmployeeEntity;
import com.springbootweb.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
//    @GetMapping(path="/getMessage")
//            public String getMessage()
//    {
//        return "my message:get well soon";
//    }

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable Long employeeId){
        return employeeRepository.findById(employeeId).orElse(null);
    }
    @GetMapping
    public List<EmployeeEntity> getAllEmployee(){
        return employeeRepository.findAll();
    }

    @PostMapping
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity inputEmployee){
        return employeeRepository.save(inputEmployee);
    }


}
