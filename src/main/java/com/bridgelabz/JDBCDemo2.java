package com.bridgelabz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCDemo2 {
	public static void main(String[] args) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_service", "root", "");
			// here sonoo is database name, root is username and password
			PreparedStatement stmt = con.prepareStatement("select * from employee_payroll where id =? and salary =?");
			
			stmt.setInt(1, 3);
			stmt.setDouble(2, 5000.0);
			boolean response = stmt.execute();

			if (response) {
				ResultSet rs = stmt.getResultSet();
				while (rs.next())
					System.out.println(
							rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getDouble(3) + "  " + rs.getString(4) + "  " + rs.getDate(5));

			}else {
				int count = stmt.getUpdateCount();
				System.out.println(count);

			}

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

//"insert into employee_payroll(name,salary,email,start)\r\n"
//+ "values(\"Sarvesh2\",6100000.0,\"sample4\",'2022-08-22')"
