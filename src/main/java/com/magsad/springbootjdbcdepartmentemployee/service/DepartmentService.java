package com.magsad.springbootjdbcdepartmentemployee.service;

import com.magsad.springbootjdbcdepartmentemployee.dto.Department;
import com.magsad.springbootjdbcdepartmentemployee.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> findAll(){
        return departmentRepository.findAll();
    }

    public Department findById(Long id){
       return departmentRepository.findById(id);
    }

    public Department create(Department department){
        departmentRepository.save(department);
        return department;
    }

    public Department update(Department department) {
        departmentRepository.update(department);
        return department;
    }

    public Department delete(Department department){
        departmentRepository.delete(department);
        return department;
    }

    public Long deleteById(Long id){
        departmentRepository.deleteById(id);
        return id;
    }
}
