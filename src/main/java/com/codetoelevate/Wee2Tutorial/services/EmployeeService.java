package com.codetoelevate.Wee2Tutorial.services;

import com.codetoelevate.Wee2Tutorial.dto.EmployeeDto;
import com.codetoelevate.Wee2Tutorial.entities.EmployeeEntity;
import com.codetoelevate.Wee2Tutorial.exceptions.ResourceNotFoundException;
import com.codetoelevate.Wee2Tutorial.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDto> getEmployeeById(Long id) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
        return employeeEntity
                .map(employeeEntity1 -> modelMapper.map(employeeEntity1, EmployeeDto.class));
    }

    public List<EmployeeDto> getAllEmployee() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    public EmployeeDto createNewEmployee(EmployeeDto inputEmployee) {
        EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(toSaveEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDto.class);
    }

    //if the employeeId is not present then will create a new employee, and if already present then update the employee.
    public EmployeeDto updateEmployeeById(Long employeeId, EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
        isExistByEmployeeId(employeeId);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    public boolean deleteEmployeeById(Long employeeId) {
        isExistByEmployeeId(employeeId);
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDto updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
        isExistByEmployeeId(employeeId);
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        updates.forEach((field, value)->{
                Field fieldToBeUpdated = ReflectionUtils.getRequiredField(EmployeeEntity.class, field);
                fieldToBeUpdated.setAccessible(true);
                ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
        });
        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDto.class);
    }

    public boolean isExistByEmployeeId(Long employeeId){
        boolean exist = employeeRepository.existsById(employeeId);
        if(!exist) throw new ResourceNotFoundException("Employee not found with id :"+employeeId);
        return true;
    }
}
