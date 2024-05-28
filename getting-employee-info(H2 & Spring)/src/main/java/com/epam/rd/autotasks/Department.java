package com.epam.rd.autotasks;

import jakarta.persistence.*;


public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "department_name", nullable = false)
	private String name;

	public Department(int departmentId, String departmentName) {
		id = departmentId;
		name = departmentName;
	}
	public Department(String departmentName) {
		name = departmentName;
	}

	public int getId() {
		return id;
	}

	public void setId(int departmentId) {
		id = departmentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String departmentName) {
		name = departmentName;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		Department other = (Department) object;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}