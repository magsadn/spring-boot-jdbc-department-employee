package com.magsad.springbootjdbcdepartmentemployee.repository;

import com.magsad.springbootjdbcdepartmentemployee.config.DBConnection;
import com.magsad.springbootjdbcdepartmentemployee.dto.Department;
import com.magsad.springbootjdbcdepartmentemployee.dto.Employee;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    public static final DBConnection dbConnection = new DBConnection();

    public List<Employee> findAll(){
        List<Employee> employeeList = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection()){
            String sqlQuerySelectAll = "select * from employees order by id";
            PreparedStatement psSelectAll = connection.prepareStatement(sqlQuerySelectAll);
            ResultSet resultSet = psSelectAll.executeQuery();
            while (resultSet.next()){
                Employee e = new Employee(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("address"),
                        findDepartmentById(resultSet.getInt("dept_id"))
//                        departmentRepository.findById(resultSet.getInt("dept_id")) //comment cause - stackoverflow
//                        new Department()
//                        resultSet.getInt("dept_id")
                );
                employeeList.add(e);

            }
        }catch (SQLException throwable){
            System.out.println(throwable.getMessage());
        }
        return employeeList;
    }

    public Employee findById(Long id) {
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQuerySelectById = "select * from employees where id = ?";
            PreparedStatement psSelectById = connection.prepareStatement(sqlQuerySelectById);
            psSelectById.setLong(1,id);
            ResultSet resultSet = psSelectById.executeQuery();
            if (resultSet.next()){
                return new Employee(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("address"),
                        findDepartmentById(resultSet.getInt("dept_id"))
//                        departmentRepository.findById(resultSet.getInt("dept_id")) //comment cause - stackoverflow
//                        new Department()
//                        resultSet.getInt("dept_id")
                );
            }
        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
        return null;
    }

    public int save(Employee emp) {
        int queryResult = 0;
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQueryInsert = "insert into employees values (nextval('employee_id'),?,?,?,?,?,?)";
            PreparedStatement psInsert = connection.prepareStatement(sqlQueryInsert);
            psInsert.setString(1, emp.getName());
            psInsert.setString(2, emp.getSurname());
            psInsert.setString(3, emp.getEmail());
            psInsert.setString(4, emp.getPhone());
            psInsert.setString(5, emp.getAddress());
            psInsert.setLong(6, emp.getDepartment().getId());

            queryResult = psInsert.executeUpdate();
            System.out.println(queryResult);
            if (queryResult != 0) {
                System.out.println("Inserted!");
            }

        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
        return queryResult;
    }

    public int update(Employee emp) {
        int queryResult = 0;
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQueryUpdate = "update employees set name=?,surname=?,email=?,phone=?,address=?,dept_id=? where id=?";
            PreparedStatement psUpdate = connection.prepareStatement(sqlQueryUpdate);
            psUpdate.setString(1, emp.getName());
            psUpdate.setString(2, emp.getSurname());
            psUpdate.setString(3, emp.getEmail());
            psUpdate.setString(4, emp.getPhone());
            psUpdate.setString(5, emp.getAddress());
            psUpdate.setLong(6, emp.getDepartment().getId());
            psUpdate.setLong(7, emp.getId());

            queryResult = psUpdate.executeUpdate();
            System.out.println(queryResult);
            if (queryResult != 0) {
                System.out.format("Id %d employee was Updated!\n",emp.getId());
            }

        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
        return queryResult;
    }

    public int delete(Employee emp) {
        int queryResult = 0;
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQueryDelete = "delete from employees where id=?";
            PreparedStatement psDelete = connection.prepareStatement(sqlQueryDelete);
            psDelete.setLong(1, emp.getId());//1ci ? isaresinin evezine yazilir.2ci sual isaresi yoxdur.
            queryResult = psDelete.executeUpdate();
            if (queryResult != 0) {
                System.out.println("Deleted!");
            }
        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
        return queryResult;
    }

    public Department findDepartmentById(int id) {
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQuerySelectById = "select * from departments where id = ?";
            PreparedStatement psSelectById = connection.prepareStatement(sqlQuerySelectById);
            psSelectById.setInt(1,id);
            ResultSet resultSet = psSelectById.executeQuery();
            if (resultSet.next()){
                Department department = new Department(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("phone"),
                        new ArrayList<>()
                );
                return department;
            }
        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
        return null;
    }

    public List<Employee> findByDepartment(Department department){
        List<Employee> employeeList = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection()){
            String sqlQuerySelectAllByDepart = "select * from employees where dept_id=? order by id";
            PreparedStatement psSelectAllByDepart = connection.prepareStatement(sqlQuerySelectAllByDepart);
            psSelectAllByDepart.setLong(1,department.getId());
            ResultSet resultSet = psSelectAllByDepart.executeQuery();
            while (resultSet.next()){
                Employee e = new Employee(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("address"),
                        findDepartmentById(resultSet.getInt("dept_id"))
//                        departmentRepository.findById(resultSet.getInt("dept_id")) //comment cause - stackoverflow

//                        new Department()
//                        resultSet.getInt("dept_id")
                );
                employeeList.add(e);
//                System.out.println(e);
                return employeeList;
            }
        }catch (SQLException throwable){
            System.out.println(throwable.getMessage());
        }
        return null;
    }
}
