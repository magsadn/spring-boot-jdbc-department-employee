package com.magsad.springbootjdbcdepartmentemployee.repository;

import com.magsad.springbootjdbcdepartmentemployee.config.DBConnection;
import com.magsad.springbootjdbcdepartmentemployee.dto.Department;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentRepository {
    public static final DBConnection dbConnection = new DBConnection();
    public static final EmployeeRepository employeeRepository = new EmployeeRepository();

    public List<Department> findAll() {
        List<Department> departmentList = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQuerySelectAll = "select * from departments order by id";
            PreparedStatement psSelectAll = connection.prepareStatement(sqlQuerySelectAll);
            ResultSet resultSet = psSelectAll.executeQuery();
            while (resultSet.next()) {
                Department a = new Department(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("phone"),
                        new ArrayList<>()
                );
                departmentList.add(a);
            }
        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
        return departmentList;
    }

    public Department findById(Long id) {
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQuerySelectById = "select * from departments where id = ?";
            PreparedStatement psSelectById = connection.prepareStatement(sqlQuerySelectById);
            psSelectById.setLong(1,id);
            ResultSet resultSet = psSelectById.executeQuery();
            if (resultSet.next()){
                Department department = new Department(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("phone"),
                        new ArrayList<>()
                );
                department.setEmployeeList(employeeRepository.findByDepartment(department));
                return department;
            }
        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());

        }
        return null;
    }

    public int save(Department dep) {
        int queryResult = 0;
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQueryInsert = "insert into departments values (nextval('department_id'),?,?)";
            PreparedStatement psInsert = connection.prepareStatement(sqlQueryInsert);
//            psInsert.setLong(1, dep.getId());
            psInsert.setString(1, dep.getName());
            psInsert.setString(2, dep.getPhone());

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

    public int update(Department dep) {
        int queryResult = 0;
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQueryUpdate = "update departments set name=?,phone=? where id=?";
            PreparedStatement psUpdate = connection.prepareStatement(sqlQueryUpdate);
            psUpdate.setString(1, dep.getName());
            psUpdate.setString(2, dep.getPhone());
            psUpdate.setLong(3, dep.getId());

            queryResult = psUpdate.executeUpdate();
            System.out.println(queryResult);
            if (queryResult != 0) {
                System.out.format("Id %d department was Updated!\n",dep.getId());
            }

        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
        return queryResult;
    }

    public int delete(Department dep) {
        int queryResult = 0;
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQueryDelete = "delete from departments where id=?";
            PreparedStatement psDelete = connection.prepareStatement(sqlQueryDelete);
            psDelete.setLong(1, dep.getId());//1ci ? isaresinin evezine yazilir.2ci sual isaresi yoxdur.
            queryResult = psDelete.executeUpdate();
            System.out.println(queryResult);
            if (queryResult != 0) {
                System.out.println("Deleted!");
            }
        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
        return queryResult;
    }

    public int deleteById(Long id) {
        int queryResult = 0;
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQueryDelete = "delete from departments where id=?";
            PreparedStatement psDelete = connection.prepareStatement(sqlQueryDelete);
            psDelete.setLong(1, id);//1ci ? isaresinin evezine yazilir.2ci sual isaresi yoxdur.
            queryResult = psDelete.executeUpdate();
            System.out.println(queryResult);
            if (queryResult != 0) {
                System.out.println("Deleted!");
            }
        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
        return queryResult;
    }
}
