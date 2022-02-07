package com.magsad.springbootjdbcdepartmentemployee.service;

import com.magsad.springbootjdbcdepartmentemployee.dto.Employee;
import com.magsad.springbootjdbcdepartmentemployee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Employee findById(Long id){
        return employeeRepository.findById(id);
    }

    public Employee create (Employee employee){
        employeeRepository.save(employee);
        return employee;
    }

    public Employee update(Employee employee) {
        employeeRepository.update(employee);
        return employee;
    }

    public Employee delete(Employee employee){
        employeeRepository.delete(employee);
        return employee;
    }
}
