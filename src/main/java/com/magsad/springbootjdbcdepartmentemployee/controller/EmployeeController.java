package com.magsad.springbootjdbcdepartmentemployee.controller;

import com.magsad.springbootjdbcdepartmentemployee.dto.Employee;
import com.magsad.springbootjdbcdepartmentemployee.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
@CrossOrigin(origins = "http://localhost:8088")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAll(){
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @GetMapping("query")
    public ResponseEntity<Employee> getByIdQuery(@RequestParam Long id){
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Employee> save(@RequestBody Employee employee){
        return ResponseEntity.ok(employeeService.create(employee));
    }

    @PutMapping
    public ResponseEntity<Employee> update(@RequestBody Employee employee){
        return ResponseEntity.ok(employeeService.update(employee));
    }

    @DeleteMapping
    public ResponseEntity<Employee> delete(@RequestBody Employee employee){
        return ResponseEntity.ok(employeeService.delete(employee));
    }

/*    @DeleteMapping("{id}")
    public ResponseEntity<Employee> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.delete(id));
    }

    @DeleteMapping("query")
    public ResponseEntity<Employee> deleteByIdQuery(@RequestParam Long id){
        return ResponseEntity.ok(employeeService.delete(id));
    }*/
}
