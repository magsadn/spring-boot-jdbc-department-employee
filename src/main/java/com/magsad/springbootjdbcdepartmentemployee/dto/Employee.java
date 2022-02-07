package com.magsad.springbootjdbcdepartmentemployee.dto;

import java.util.Objects;

public class Employee {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private Department department;

    public Employee(Long id, String name, String surname, String email, String phone, String address, Department department) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.department = department;
    }

    public Employee() {
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(name, employee.name) && Objects.equals(surname, employee.surname) && Objects.equals(email, employee.email) && Objects.equals(phone, employee.phone) && Objects.equals(address, employee.address) && Objects.equals(department, employee.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, phone, address, department);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", department=" + department + // comment cause - stackoverflow
                '}';
    }

}
