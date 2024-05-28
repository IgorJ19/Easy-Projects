package com.epam.rd.autotasks;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.*;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(ReplaceCamelCase.class)
public class DatabaseTest {

	private static final String JDBC_URL = "jdbc:derby:memory:test;create=true";

	private static final int DEPARTMENT_NUM = new Random().nextInt(2, 10);
	private static final int EMPLOYEE_NUM = DEPARTMENT_NUM + 50;

	private static String[] departmentNames = new String[DEPARTMENT_NUM];
	private static String[] firstNames = new String[EMPLOYEE_NUM];
	private static String[] lastNames = new String[EMPLOYEE_NUM];
	private static int[] departmentIds = new int[EMPLOYEE_NUM];

	private static Connection connection;

	public static void countDepartmentsDerby(int[] totalDepartments) throws SQLException {
		try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
			String query = "SELECT COUNT(*) FROM departments";
			totalDepartments[0] = 0;
			try (PreparedStatement preparedStatement = connection.prepareStatement(query);
					ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					totalDepartments[0] = resultSet.getInt(1);
				}
			}
		}
	}

	public static void countEmployeesDerby(int[] totalEmployees) throws SQLException {
		try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
			String query = "SELECT COUNT(*) FROM employees";
			totalEmployees[0] = 0;
			try (PreparedStatement preparedStatement = connection.prepareStatement(query);
					ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					totalEmployees[0] = resultSet.getInt(1);
				}
			}
		}
	}

	public static void countEmployeesByDepartmentIdDerby(int departmentId, int[] result) throws SQLException {
		try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
			String query = "SELECT COUNT(*) FROM employees WHERE department_id = ?";
			result[0] = 0;
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setInt(1, departmentId);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (resultSet.next()) {
						result[0] = resultSet.getInt(1);
					}
				}
			}
		}
	}

	@BeforeAll
	static void init() throws SQLException {
		connection = DriverManager.getConnection(JDBC_URL);
		Arrays.setAll(departmentNames, s -> UUID.randomUUID().toString());
		Arrays.setAll(firstNames, s -> UUID.randomUUID().toString());
		Arrays.setAll(lastNames, i -> UUID.randomUUID().toString());
		Random rnd = new Random();
		Arrays.setAll(departmentIds, s -> rnd.nextInt(departmentNames.length) + 1);
		try (Statement statement = connection.createStatement()) {
			statement.execute("""
					CREATE TABLE departments (
					  id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1) PRIMARY KEY,
					  department_name VARCHAR(255) not null
					)
					""");
			statement.execute("""
					CREATE TABLE employees (
					  id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
					  first_name VARCHAR(255) not null,
					  last_name VARCHAR(255) not null,
					  department_id INTEGER,
					  FOREIGN KEY (department_id) REFERENCES departments (id)
					)
					""");
			for (String department : departmentNames) {
				statement.execute("INSERT INTO departments (department_name) VALUES ('" + department + "')");
			}
			for (int i = 0; i < EMPLOYEE_NUM; ++i) {
				statement.execute("INSERT INTO employees (first_name, last_name, department_id) VALUES ('"
						+ firstNames[i] + "', '" + lastNames[i] + "', " + departmentIds[i] + ")");
			}
			statement.execute("""
					CREATE PROCEDURE COUNT_DEPARTMENTS(OUT totalDepartments INTEGER)
					PARAMETER STYLE JAVA READS SQL DATA LANGUAGE JAVA EXTERNAL NAME
					'com.epam.rd.autotasks.DatabaseTest.countDepartmentsDerby'
					""");
			statement.execute("""
					CREATE PROCEDURE COUNT_EMPLOYEES(OUT totalEmployees INTEGER)
					PARAMETER STYLE JAVA READS SQL DATA LANGUAGE JAVA EXTERNAL NAME
					'com.epam.rd.autotasks.DatabaseTest.countEmployeesDerby'
					""");
			statement.execute("""
					CREATE PROCEDURE COUNT_EMPLOYEES_BY_DEPARTMENT_ID(IN id INTEGER, OUT result INTEGER)
					PARAMETER STYLE JAVA READS SQL DATA LANGUAGE JAVA EXTERNAL NAME
					'com.epam.rd.autotasks.DatabaseTest.countEmployeesByDepartmentIdDerby'
					""");
		}
	}

	@AfterAll
	static void shutdown() throws SQLException {
		connection.close();
	}

	@Test
	void testCallCountDepartments() throws SQLException {
		int expectedTotalDepartments = DEPARTMENT_NUM;
		int actualTotalDepartments = DbManager.callCountDepartments(connection);
		assertEquals(expectedTotalDepartments, actualTotalDepartments,
				"The total number of departments is calculated incorrectly");
	}

	@Test
	void testCallCountEmployees() throws SQLException {
		int expectedTotalEmployees = EMPLOYEE_NUM;
		int actualTotalEmployees = DbManager.callCountEmployees(connection);
		assertEquals(expectedTotalEmployees, actualTotalEmployees,
				"The total number of employees is calculated incorrectly");
	}

	static IntStream provideDepartmentIds() {
		return IntStream.range(1, departmentNames.length + 2);
	}

	@ParameterizedTest
	@MethodSource("provideDepartmentIds")
	void testCallCountEmployeesByDepartmentId(int departmentId) throws SQLException {
		String query = "SELECT COUNT(*) FROM employees WHERE department_id = " + Integer.toString(departmentId);
		int expectedTotalEmployees = -1;
		try (Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				expectedTotalEmployees = resultSet.getInt(1);
			}
		}
		int actualTotalEmployees = DbManager.callCountEmployeesByDepartmentId(connection, departmentId);
		assertEquals(expectedTotalEmployees, actualTotalEmployees,
				"The total number of employees in the department is calculated incorrectly");
	}
}