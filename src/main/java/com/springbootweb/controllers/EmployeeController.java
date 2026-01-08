package com.springbootweb.controllers;

import com.springbootweb.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
//    @GetMapping(path="/getMessage")
//            public String getMessage()
//    {
//        return "my message:get well soon";
//    }
    @GetMapping
    public String getEmployeeById(){
        return "Hello get";
    }
    @PostMapping
    public String getMessage(){
        return "Hello Post";
    }

}
