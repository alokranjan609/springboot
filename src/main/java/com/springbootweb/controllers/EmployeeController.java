package com.springbootweb.controllers;

import com.springbootweb.dto.EmployeeDTO;
import com.springbootweb.entities.EmployeeEntity;
import com.springbootweb.repositories.EmployeeRepository;
import com.springbootweb.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId){
        return employeeService.getEmployeeById(employeeId);

    }
    @GetMapping
    public List<EmployeeDTO> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO inputEmployee){
        return employeeService.createEmployee(inputEmployee);
    }

    @PutMapping(path = "/{employeeId}")
    public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO employeeDTO,@PathVariable long employeeId){
        return employeeService.updateEmployee(employeeDTO,employeeId);
    }
   @DeleteMapping(path ="/{employeeId}")
    public boolean deleteEmployee(@PathVariable long employeeId){
        return employeeService.deleteEmployee(employeeId);
   }

   @PatchMapping(path = "/{employeeId}")
    public EmployeeDTO updatePartialEmployee(@RequestBody Map<String,Object> updates, @PathVariable long employeeId){
        return employeeService.updatePartialEmployee(updates,employeeId);
   }

}
