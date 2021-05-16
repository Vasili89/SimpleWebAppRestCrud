package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") Integer id) {
        Employee employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
        employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        employeeService.updateEmployee(id, employee);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable("id") Integer id) {
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
}
