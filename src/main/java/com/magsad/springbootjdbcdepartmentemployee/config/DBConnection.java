package com.magsad.springbootjdbcdepartmentemployee.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    Connection connection = null;
    public Connection getConnection(){
        String url  = "jdbc:postgresql://localhost:5432/repeat-lessons";
        String user = "postgres";
        String password = "";
//        System.out.println("Connecting ...");
        try {
//            Ehtiyac yoxdur buna:
//            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url,user,password);
//            System.out.println("Connected!");

        } catch (SQLException throwable) {
            throwable.printStackTrace();
            System.out.println(throwable.getMessage());
        }
        return connection;
    }
    public void closeConnection(){
            try {
                if (connection != null){
                    connection.close();
//                    System.out.println("Connection closed!");
                }
            } catch (SQLException throwable) {
                throwable.printStackTrace();
                System.out.println(throwable.getMessage());
            }
        }
}
