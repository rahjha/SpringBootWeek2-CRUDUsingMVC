package com.codetoelevate.Wee2Tutorial.controllers;

import com.codetoelevate.Wee2Tutorial.dto.EmployeeDto;
import com.codetoelevate.Wee2Tutorial.entities.EmployeeEntity;
import com.codetoelevate.Wee2Tutorial.repositories.EmployeeRepository;
import com.codetoelevate.Wee2Tutorial.services.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path="/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id){
        Optional<EmployeeDto> employeeDto = employeeService.getEmployeeById(id);
        return employeeDto
                .map(employeeDto1 -> ResponseEntity.ok(employeeDto1))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(@RequestParam(required = false, name="inputAge") Integer age,
                                               @RequestParam(required = false) String sortBy){
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createNewEmployee(@RequestBody EmployeeDto inputEmployee){
        EmployeeDto savedEmployee =  employeeService.createNewEmployee(inputEmployee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployeeById(@RequestBody EmployeeDto employeeDto, @PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId, employeeDto));
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){
        boolean gotDeleted = employeeService.deleteEmployeeById(employeeId);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> updatePartialEmployeeById(@RequestBody Map<String, Object> employeeDto,@PathVariable Long employeeId){
        EmployeeDto employeeDto1 = employeeService.updatePartialEmployeeById(employeeId, employeeDto);
        if(employeeDto1 == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDto1);
    }
}
