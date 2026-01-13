package com.springbootweb.services;

import com.springbootweb.dto.DepartmentDTO;
import com.springbootweb.dto.EmployeeDTO;
import com.springbootweb.entities.DepartmentEntity;
import com.springbootweb.entities.EmployeeEntity;
import com.springbootweb.exceptions.ResourceNotFoundException;
import com.springbootweb.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

        public List<DepartmentDTO> getAllDepartment(){
            List<DepartmentEntity> departmentEntities= departmentRepository.findAll();
            return departmentEntities
                    .stream()
                    .map(departmentEntity -> modelMapper.map(departmentEntity,DepartmentDTO.class))
                    .collect(Collectors.toList());

    }

    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        DepartmentEntity tosaveEntity=modelMapper.map(departmentDTO,DepartmentEntity.class);
        DepartmentEntity savedDepartment = departmentRepository.save(tosaveEntity);
        return modelMapper.map(savedDepartment, DepartmentDTO.class);
    }
    public DepartmentDTO getDepartmentById(Long id) {
        if(!departmentRepository.existsById(id)){
            throw new ResourceNotFoundException("No department found for this id");
        }

        return modelMapper.map(departmentRepository.findById(id),DepartmentDTO.class);
    }

    public DepartmentDTO updateDepartment( DepartmentDTO departmentDTO, long departmentId) {
        DepartmentEntity departmentEntity =modelMapper.map(departmentDTO,DepartmentEntity.class);
        if(!departmentRepository.existsById(departmentId)){
            throw new ResourceNotFoundException("No department found for this id");
        }
        departmentEntity.setId(departmentId);
        DepartmentEntity updatedEntity=departmentRepository.save(departmentEntity);
        return modelMapper.map(updatedEntity,DepartmentDTO.class);
    }

    public void deleteDepartment(long departmentId) {
        if(!departmentRepository.existsById(departmentId)){
            throw new ResourceNotFoundException("No department found for this id");
        }
        departmentRepository.deleteById(departmentId);
}
    public  DepartmentDTO updatePartialDepartment(Map<String, Object> updates, long departmentId) {
        if(!departmentRepository.existsById(departmentId)){
            throw new ResourceNotFoundException("No department found for this id");
        }
        DepartmentEntity departmentEntity=departmentRepository.findById(departmentId).get();
        updates.forEach((field,value)->{
                    Field fieldTobeUpdated= ReflectionUtils.findField(DepartmentEntity.class,field);
                    assert fieldTobeUpdated != null;
                    fieldTobeUpdated.setAccessible(true);
                    ReflectionUtils.setField(fieldTobeUpdated,departmentEntity,value);
                }
        );
        return modelMapper.map(departmentRepository.save(departmentEntity),DepartmentDTO.class);

    }

}