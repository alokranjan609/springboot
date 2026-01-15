package com.springbootweb.controllers;

import com.springbootweb.dto.DepartmentDTO;
import com.springbootweb.dto.EmployeeDTO;
import com.springbootweb.exceptions.ResourceNotFoundException;
import com.springbootweb.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path="/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService= departmentService;
    }
    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartment(){
        return ResponseEntity.ok(departmentService.getAllDepartment());
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody @Valid  DepartmentDTO departmentDTO){
        DepartmentDTO departmentDTO1= departmentService.createDepartment(departmentDTO);
        return new ResponseEntity<>(departmentDTO1, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long departmentId ){
         return ResponseEntity.ok(departmentService.getDepartmentById(departmentId));

    }

    @PutMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@RequestBody DepartmentDTO departmentDTO,@PathVariable long departmentId){
        return ResponseEntity.ok(departmentService.updateDepartment(departmentDTO,departmentId));
    }

    @DeleteMapping(path ="/{departmentId}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable long departmentId){
        departmentService.deleteDepartment(departmentId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> updatePartialDepartment(@RequestBody Map<String,Object> updates, @PathVariable long departmentId){
        DepartmentDTO departmentDTO = departmentService.updatePartialDepartment(updates,departmentId);
        if(departmentDTO==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(departmentDTO);
    }
}
