package com.springbootweb.controllers;

import com.springbootweb.dto.DepartmentDTO;
import com.springbootweb.dto.EmployeeDTO;
import com.springbootweb.exceptions.ResourceNotFoundException;
import com.springbootweb.services.DepartmentService;
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
    public List<DepartmentDTO> getAllDepartment(){
        return departmentService.getAllDepartment();
    }

    @PostMapping
    public DepartmentDTO createDepartment(@RequestBody DepartmentDTO departmentDTO){
        return departmentService.createDepartment(departmentDTO);
    }

    @GetMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long departmentId ){
         Optional<DepartmentDTO> departmentDTO=departmentService.getDepartmentById(departmentId);
        return departmentDTO
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PutMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@RequestBody DepartmentDTO departmentDTO,@PathVariable long departmentId){
        return ResponseEntity.ok(departmentService.updateDepartment(departmentDTO,departmentId));
    }

    @DeleteMapping(path ="/{departmentId}")
    public ResponseEntity<Boolean> deleteDepartment(@PathVariable long departmentId){
        boolean gotDeleted= departmentService.deleteDepartment(departmentId);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> updatePartialDepartment(@RequestBody Map<String,Object> updates, @PathVariable long departmentId){
        DepartmentDTO departmentDTO = departmentService.updatePartialDepartment(updates,departmentId);
        if(departmentDTO==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(departmentDTO);
    }
}
