package com.epam.rd.autotasks;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@DisplayNameGeneration(ReplaceCamelCase.class)
@ExtendWith(MockitoExtension.class)
class DbManagerTest {

	@Mock
	private Connection connection;

	@Mock
	private CallableStatement callableStatement;

	@Test
	void testCallCountDepartments() throws SQLException {
		Mockito.when(connection.prepareCall(anyString())).thenReturn(callableStatement);
		InOrder inOrder = Mockito.inOrder(connection, callableStatement);

		DbManager.callCountDepartments(connection);

		inOrder.verify(connection).prepareCall(anyString());
		inOrder.verify(callableStatement).registerOutParameter(anyInt(), anyInt());
		inOrder.verify(callableStatement).close();
	}

	@Test
	void testCallCountEmployees() throws SQLException {
		Mockito.when(connection.prepareCall(anyString())).thenReturn(callableStatement);
		InOrder inOrder = Mockito.inOrder(connection, callableStatement);

		DbManager.callCountEmployees(connection);

		inOrder.verify(connection).prepareCall(anyString());
		inOrder.verify(callableStatement).registerOutParameter(anyInt(), anyInt());
		inOrder.verify(callableStatement).close();
	}

	@Test
	void testCallCountEmployeesByDepartmentId() throws SQLException {
		Mockito.when(connection.prepareCall(anyString())).thenReturn(callableStatement);
		InOrder inOrder = Mockito.inOrder(connection, callableStatement);

		DbManager.callCountEmployeesByDepartmentId(connection, 1);

		inOrder.verify(connection).prepareCall(anyString());
		inOrder.verify(callableStatement).registerOutParameter(anyInt(), anyInt());
		inOrder.verify(callableStatement).close();
	}
}