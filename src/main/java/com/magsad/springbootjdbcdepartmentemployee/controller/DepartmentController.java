package com.magsad.springbootjdbcdepartmentemployee.controller;

import com.magsad.springbootjdbcdepartmentemployee.dto.Department;
import com.magsad.springbootjdbcdepartmentemployee.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("departments")
@CrossOrigin(origins = "http://localhost:8088")
public class DepartmentController {
    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAll(){
        return new ResponseEntity<>(departmentService.findAll(), HttpStatus.ACCEPTED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Department> getById(@PathVariable Long id){
        return ResponseEntity.ok(departmentService.findById(id));
    }

    @GetMapping("query")
    public ResponseEntity<Department> getByIdQuery(@RequestParam Long id){
        return ResponseEntity.ok(departmentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Department> save(@RequestBody Department department){
        return ResponseEntity.ok(departmentService.create(department));
    }

    @PutMapping
    public ResponseEntity<Department> update(@RequestBody Department department){
        return ResponseEntity.ok(departmentService.update(department));
    }

    @DeleteMapping
    public ResponseEntity<Department> delete(@RequestBody Department department){
        return ResponseEntity.ok(departmentService.delete(department));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        return ResponseEntity.ok(departmentService.deleteById(id));
    }

    @DeleteMapping("query")
    public ResponseEntity deleteByIdQuery(@RequestParam Long id){
        return ResponseEntity.ok(departmentService.deleteById(id));
    }
}
