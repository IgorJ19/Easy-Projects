package com.epam.rd.autotasks;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Objects;


public class Employee {
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

	public Employee(){}
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


	public Employee(String first_nameOfEmmployee, String last_nameOfEmployee, int idOFdepartment) {
		first_name = first_nameOfEmmployee;
		last_name = last_nameOfEmployee;
		department_id = idOFdepartment;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"first_name='" + first_name + '\'' +
				", last_name='" + last_name + '\'' +
				", department_id=" + department_id +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Employee employee = (Employee) o;
		return department_id == employee.department_id && Objects.equals(first_name, employee.first_name) && Objects.equals(last_name, employee.last_name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(first_name, last_name, department_id);
	}
}