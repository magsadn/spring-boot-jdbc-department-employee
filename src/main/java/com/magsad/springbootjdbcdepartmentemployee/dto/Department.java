package com.magsad.springbootjdbcdepartmentemployee.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Department {
    private Long id;
    private String name;
    private String phone;
    private List<Employee> employeeList=new ArrayList<>();

    public Department(Long id, String name, String phone, List<Employee> employeeList) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.employeeList = employeeList;
    }

    public Department() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(phone, that.phone)
                && Objects.equals(employeeList, that.employeeList) // comment cause - stackoverflow
        ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone , employeeList // comment cause - stackoverflow
                );
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", employeeList=" + employeeList + // comment cause - stackoverflow
                '}';
    }
}
