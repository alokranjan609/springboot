package com.springbootweb.controllers;

import com.springbootweb.dto.EmployeeDTO;
import com.springbootweb.entities.EmployeeEntity;
import com.springbootweb.exceptions.ResourceNotFoundException;
import com.springbootweb.repositories.EmployeeRepository;
import com.springbootweb.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeId){
        Optional<EmployeeDTO> employeeDTO= employeeService.getEmployeeById(employeeId);
        return employeeDTO.map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1)).orElseThrow(()-> new ResourceNotFoundException("Employee was not found"));

    }
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee(){
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Valid EmployeeDTO inputEmployee){
        EmployeeDTO savedemployeeDTO= employeeService.createEmployee(inputEmployee);
        return new ResponseEntity<>(savedemployeeDTO, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTO,@PathVariable long employeeId){
        return ResponseEntity.ok(employeeService.updateEmployee(employeeDTO,employeeId));
    }
   @DeleteMapping(path ="/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable long employeeId){
        boolean gotDeleted= employeeService.deleteEmployee(employeeId);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
   }



   @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployee(@RequestBody Map<String,Object> updates, @PathVariable long employeeId){
        EmployeeDTO employeeDTO= employeeService.updatePartialEmployee(updates,employeeId);
        if(employeeDTO==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
   }

}
