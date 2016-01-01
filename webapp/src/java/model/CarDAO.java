/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Alexander
 */

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import database.DatabaseManager;
import exceptions.ApplicationException;
import model.Car;

public class CarDAO {

	DataSource ds;

	public CarDAO(DataSource ds) {
		this.ds = ds;
	}

        /*
	public int addEmployee(Car employee) throws ApplicationException {
		Connection connection = null;
		Statement stmt = null;
		String query = "insert into employee(emp_name, salary, dept_name) values('"
				+ employee.getEmployeeName()
				+ "',"
				+ employee.getSalary()
				+ ",'" + employee.getDeptName() + "')";
		
		int row = -1;
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			row = stmt.executeUpdate(query);
		} catch (SQLException e) {
			ApplicationException exception = new ApplicationException(
					"Unable to insert data: " + e.getMessage(), e);
			throw exception;
		} finally {
			DbUtil.close(stmt);
			DbUtil.close(connection);
		}
		return row;
	} */ 
}
