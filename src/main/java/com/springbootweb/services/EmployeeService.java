package com.springbootweb.services;

import com.springbootweb.dto.EmployeeDTO;
import com.springbootweb.entities.EmployeeEntity;
import com.springbootweb.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }



    public Optional<EmployeeDTO> getEmployeeById(Long employeeId){
     return employeeRepository.findById(employeeId).map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployee(){
        List<EmployeeEntity> employeeEntities= employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createEmployee(EmployeeDTO inputEmployee){
        EmployeeEntity tosaveEntity=modelMapper.map(inputEmployee,EmployeeEntity.class);
        EmployeeEntity savedEmployee = employeeRepository.save(tosaveEntity);
        return modelMapper.map(savedEmployee,EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployee( EmployeeDTO employeeDTO, long employeeId) {
        EmployeeEntity employeeEntity=modelMapper.map(employeeDTO,EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity updatedEntity=employeeRepository.save(employeeEntity);
        return modelMapper.map(updatedEntity,EmployeeDTO.class);
    }

    public boolean deleteEmployee(long employeeId) {
        boolean isExist =employeeRepository.existsById(employeeId);
        if(!isExist) return false;
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO updatePartialEmployee(Map<String, Object> updates, long employeeId) {
        boolean isExist =employeeRepository.existsById(employeeId);
        if(!isExist) return null;
        EmployeeEntity employeeEntity=employeeRepository.findById(employeeId).get();
        updates.forEach((field,value)->{
                    Field fieldTobeUpdated= ReflectionUtils.findField(EmployeeEntity.class,field);
                    assert fieldTobeUpdated != null;
                    fieldTobeUpdated.setAccessible(true);
                    ReflectionUtils.setField(fieldTobeUpdated,employeeEntity,value);
                }
                );
        return modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);

    }
}
