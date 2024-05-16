package com.epam.rd.autotasks;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;


@Entity
@Table(name = "departments")
public class DbManager {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int ID;

	@NotBlank(message = "cant be null")
	public String department_name;


	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ID", fetch = FetchType.EAGER)
	public Set<DbManager> departmentTable = new HashSet<>();

	public DbManager() {
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getDepartment_name() {
		department_name = department_name + " " + ID;
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		if (department_name == null) {
			department_name = department_name + ID;
			this.department_name = department_name;
		} else
			this.department_name = department_name;

	}

	//eksperyment
	public Set<DbManager> getDepartmentTable() {
		return departmentTable.stream().map(original -> {
			var result = new DbManager();
			return result;
		}).collect(Collectors.toUnmodifiableSet());
	}

	static final Logger LOGGER = Logger.getLogger(DbManager.class.getName());

	public static int callCountDepartments(Connection connection) throws SQLException {
		int departmentCount = 0;
		CallableStatement callableStatement = null;
		try {
			callableStatement = connection.prepareCall("{CALL COUNT_DEPARTMENTS(?)}");
			callableStatement.registerOutParameter(1, Types.INTEGER);
			callableStatement.execute();
			departmentCount = callableStatement.getInt(1);
			LOGGER.info("Total number of departments: " + departmentCount);
		} catch (SQLException e) {
			LOGGER.severe("An error occurred while counting departments: " + e.getMessage());
			throw e;
		} finally {
			if (callableStatement != null) {
				callableStatement.close();
			}
		}
		return departmentCount;
	}

	public static int callCountEmployees(Connection connection) throws SQLException {
		int employeeCount = 0;
		CallableStatement callableStatement = null;
		try {
			callableStatement = connection.prepareCall("{CALL COUNT_EMPLOYEES(?)}");
			callableStatement.registerOutParameter(1, Types.INTEGER);
			callableStatement.execute();
			employeeCount = callableStatement.getInt(1);
			LOGGER.info("Total number of employees: " + employeeCount);
		} catch (SQLException e) {
			LOGGER.severe("An error occurred while counting employees: " + e.getMessage());
			throw e;
		} finally {
			if (callableStatement != null) {
				callableStatement.close();
			}
		}
		return employeeCount;
	}

	public static int callCountEmployeesByDepartmentId(Connection connection, int departmentId) throws SQLException {
		int employeeByIDCount = 0;
		CallableStatement callableStatement = null;
		try {
			callableStatement = connection.prepareCall("{CALL COUNT_EMPLOYEES_BY_DEPARTMENT_ID(?, ?)}");
			callableStatement.setInt(1, departmentId);
			callableStatement.registerOutParameter(2, Types.INTEGER);
			callableStatement.execute();
			employeeByIDCount = callableStatement.getInt(2);
			LOGGER.info("Total number of employees in department " + departmentId + ": " + employeeByIDCount);
		} catch (SQLException e) {
			LOGGER.severe("An error occurred while counting employees by department ID: " + e.getMessage());
			throw e;
		} finally {
			if (callableStatement != null) {
				callableStatement.close();
			}
		}
		return employeeByIDCount;
	}

}