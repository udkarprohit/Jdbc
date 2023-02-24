package com.bridgelabz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCDemoClass {

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_service", "root", "");
            // here so noo is database name, root is username and password
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from employee_payroll");
            System.out.println("ID\t" + "EmpName   " + "Salary\t" + "Date");
            while (rs.next())
                System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getDouble(3) + "\t" + rs.getDate(4));

            int count = stmt.executeUpdate("update employee_payroll set basic_pay = 3000000.00 where name = 'Terissa'");
            System.out.println(count);

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
