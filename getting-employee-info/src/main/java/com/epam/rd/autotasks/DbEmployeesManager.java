package com.epam.rd.autotasks;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Entity
@Table(name = "Employee")
public class DbEmployeesManager {
	@Autowired
	public static Repozytory repository;

	public void insertDepartment(DbManager department) {

		repository.save(department);
	}

	@Column(name = "first_name", nullable = false)
	private String first_name;

	@Column(name = "last_name", nullable = false)
	private String last_name;

	@Column(name = "department_id", nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int department_id;

	public DbEmployeesManager(){}
	public static Repozytory getRepository() {
		return repository;
	}

	public static void setRepository(Repozytory repository) {
		DbEmployeesManager.repository = repository;
	}

	public int getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}


	public DbEmployeesManager(String first_nameOfEmmployee, String last_nameOfEmployee, int idOFdepartment) {
		first_name = first_nameOfEmmployee;
		last_name = last_nameOfEmployee;
		department_id = idOFdepartment;
	}

	public static int callCountDepartments(Connection connection) throws SQLException {
		int count = 0;
		String query = "SELECT COUNT(*) FROM Department";

		try (PreparedStatement statement = connection.prepareStatement(query);
			 ResultSet resultSet = statement.executeQuery()) {
			if (resultSet.next()) {
				count = resultSet.getInt(1);
			}
		}

		return count;
	}
	public static int callCountEmployees(Connection connection) throws SQLException {
		int count = 0;
		String query = "SELECT COUNT(*) FROM Employees";

		try (PreparedStatement statement = connection.prepareStatement(query);
			 ResultSet resultSet = statement.executeQuery()) {
			if (resultSet.next()) {
				count = resultSet.getInt(1);
			}
		}

		return count;
	}

	public static int callCountEmployeesByDepartmentId(Connection connection, int departmentId) throws SQLException {
		int count = 0;
		String query = "SELECT COUNT(*) FROM Employees WHERE department_id = ?";

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, departmentId);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					count = resultSet.getInt(1);
				}
			}
		}
		return count;
	}
}