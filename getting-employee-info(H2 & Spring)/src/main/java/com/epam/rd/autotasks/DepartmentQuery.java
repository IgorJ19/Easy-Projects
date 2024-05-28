package com.epam.rd.autotasks;

import java.sql.*;

public class DepartmentQuery {
    public static int countDepartments() {
        int departmentCount = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:h2:file:./db", "Admin", "1234567");
            System.out.println(connection);

            statement = connection.createStatement();

            String sqlQuery = "SELECT COUNT(*) AS department_count FROM DEPARTMENTS;";
            resultSet = statement.executeQuery(sqlQuery);

            if (resultSet.next()) {
                departmentCount = resultSet.getInt("department_count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return departmentCount;
    }

    public static int countEmployees() {
        int employeeCount = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:h2:file:./db", "Admin", "1234567");
            System.out.println(connection);
            // Creating statement
            statement = connection.createStatement();

            String sqlQuery = "SELECT COUNT(*) AS employees_count FROM EMPLOYEE;";
            resultSet = statement.executeQuery(sqlQuery);

            if (resultSet.next()) {
                employeeCount = resultSet.getInt("employees_count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return employeeCount;
     }
}



