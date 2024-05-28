# Getting Employee Information

The goal of this task is to give you some practice working with the JDBC API.

Duration: *40 minutes*

## Description

In this task, you will create two tables, departments and employees, and then perform various CRUD operations using SQL queries and the JDBC API. 

## Requirements

1) Use the classes `Department` and `Employee`.

2) If necessary, you can use your own relational database. Your database should contain two tables to represent the `Department` and `Employee` objects: `departments (id, department_name)` and `employees (id, first_name, last_name, department_id)`. 

3) Or you can add the required dependency to your `pom.xml` to install the correct driver for connecting to your database. 

4) If you are using your own database, create the following stored procedures:

- `COUNT_DEPARTMENTS` counts the total number of records in the `departments` table. This stored procedure has an output parameter called `totalDepartments` that holds the number of records in the `departments` table.

- `COUNT_EMPLOYEES` counts the total number of records in the `employees` table. This stored procedure has an output parameter called `totalEmployees` that holds the number of records in the `employees` table.

- `COUNT_EMPLOYEES_BY_DEPARTMENT_ID` counts the number of employees in the `department` with the specified ID. This stored procedure has two parameters: an input parameter named `departmentId` to specify the department ID for which you want to count the employees and an output parameter called `totalEmployees` that holds the number of employees records for the specified department. 

5) Provide an implementation of the following `DbManager` class methods using the `java.sql.CallableStatement` object:

- `callCountDepartments` calls the stored procedure `COUNT_DEPARTMENTS` and returns the total number of departments.

- `callCountEmployees` calls the stored procedure `COUNT_EMPLOYEES` and returns the total number of employees.

- `callCountEmployeesByDepartmentId` calls the stored procedure `COUNT_EMPLOYEES_BY_DEPARTMENT_ID` and returns the number of employees in the department with the specified ID.

## Examples

An example of creating tables for an H2 or Apache Derby database:

```sql
CREATE TABLE departments (
  id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  department_name VARCHAR(255) not null
)

CREATE TABLE employees (
  id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  first_name VARCHAR(255) not null,
  last_name VARCHAR(255) not null,
  department_id INTEGER,
  FOREIGN KEY (department_id) REFERENCES departments (id)
)
```

An example of creating tables for a MySQL database: 

```sql
CREATE TABLE departments (
  id INT AUTO_INCREMENT PRIMARY KEY,
  department_name VARCHAR(255) not null
)

CREATE TABLE employees (
  id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(255) not null,
  last_name VARCHAR(255) not null,
  department_id INT,
  FOREIGN KEY (department_id) REFERENCES departments (id)
)
```

To create the `COUNT_DEPARTMENTS` stored procedure in the MySQL console, use the following commands:

```sql
DELIMITER //
CREATE PROCEDURE COUNT_DEPARTMENTS(OUT totalDepartments INT)
BEGIN
  SELECT COUNT(*) INTO totalDepartments FROM departments;
END //
DELIMITER ;
```

The following sequence of MySQL console commands will call the `COUNT_DEPARTMENTS` stored procedure and display the total number of departments:

```sql
SET @departmentCount = 0;
CALL COUNT_DEPARTMENTS(@departmentCount);
SELECT @departmentCount;
```

To create the `COUNT_EMPLOYEES_BY_DEPARTMENT_ID` stored procedure in the MySQL console, use the following commands:

```sql
DELIMITER //
CREATE PROCEDURE COUNT_EMPLOYEES_BY_DEPARTMENT_ID(IN departmentId INT, OUT totalEmployees INT)
BEGIN
  SELECT COUNT(*) INTO totalEmployees FROM employees WHERE department_id = departmentId;
END //
DELIMITER ;
```

The following sequence of MySQL console commands will call the COUNT_EMPLOYEES_BY_DEPARTMENT_ID stored procedure and display the number of employees in the department specified:

```sql
SET @inputDepartmentId = 1;
SET @employeeCount = 0;
CALL COUNT_EMPLOYEES_BY_DEPARTMENT_ID(@inputDepartmentId, @employeeCount);
SELECT @employeeCount;
```

An example of calling methods of the DbManager class:

```java
try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
	int departmentCount = DbManager.callCountDepartments(connection);
	LOGGER.log(System.Logger.Level.INFO, "Total number of departments: " + departmentCount);

	int employeeCount = DbManager.callCountEmployees(connection);
	LOGGER.log(System.Logger.Level.INFO, "Total number of employees: " + employeeCount);

	employeeCount = DbManager.callCountEmployeesByDepartmentId(connection, 1);
	LOGGER.log(System.Logger.Level.INFO, "Number of employees in the first department: " + employeeCount);

	employeeCount = DbManager.callCountEmployeesByDepartmentId(connection, 2);
	LOGGER.log(System.Logger.Level.INFO, "Number of employees in the second department: " + employeeCount);

	employeeCount = DbManager.callCountEmployeesByDepartmentId(connection, 3);
	LOGGER.log(System.Logger.Level.INFO, "Number of employees in the third department: " + employeeCount);
} catch (SQLException e) {
	// TODO: Error handling.
	throw e;
}
```
