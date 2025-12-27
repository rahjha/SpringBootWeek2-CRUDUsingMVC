package com.codetoelevate.Wee2Tutorial.controllers;

import com.codetoelevate.Wee2Tutorial.dto.EmployeeDto;
import com.codetoelevate.Wee2Tutorial.entities.EmployeeEntity;
import com.codetoelevate.Wee2Tutorial.repositories.EmployeeRepository;
import com.codetoelevate.Wee2Tutorial.services.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    @GetMapping
    public List<EmployeeDto> getAllEmployee(@RequestParam(required = false, name="inputAge") Integer age,
                                               @RequestParam(required = false) String sortBy){
        return employeeService.getAllEmployee();
    }

    @PostMapping
    public EmployeeDto createNewEmployee(@RequestBody EmployeeDto inputEmployee){
        return employeeService.createNewEmployee(inputEmployee);
    }

    @PutMapping("/{employeeId}")
    public EmployeeDto updateEmployeeById(@RequestBody EmployeeDto employeeDto, @PathVariable Long employeeId){
        return employeeService.updateEmployeeById(employeeId, employeeDto);
    }

    @DeleteMapping("/{employeeId}")
    public boolean deleteEmployeeById(@PathVariable Long employeeId){
        return employeeService.deleteEmployeeById(employeeId);
    }

    @PatchMapping("/{employeeId}")
    public EmployeeDto updatePartialEmployeeById(@RequestBody Map<String, Object> employeeDto,@PathVariable Long employeeId){
        return employeeService.updatePartialEmployeeById(employeeId, employeeDto);
    }
}
