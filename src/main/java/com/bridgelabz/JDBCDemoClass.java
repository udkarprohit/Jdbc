package com.bridgelabz;

import java.sql.*;

public class JDBCDemoClass {
    static String url = "jdbc:mysql://localhost:3306/payroll_service";
    static String userName="root";
    static String password="";
    public static void fetchData(String quaryFetch) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.print("The error in the class -" + e);
        }
        try {
            Connection connection =DriverManager.getConnection(url,userName,password);
            Statement st = connection.createStatement();
            ResultSet rs =st.executeQuery(quaryFetch);
            //The result we want in the table formatte so we use RS
            //fetch the data
            System.out.println("The result set - " + rs);
            while (rs.next()) {
                String userData = rs.getInt(1)+" "+rs.getNString(2) + "  " + rs.getDate(3)+" "+ rs.getDouble(4 ) + " "+ rs.getString(5);
                System.out.println(userData);
            }
            st.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("The error at the Connection - " + e);
        }
    }

    public static void main(String[] args) {
        fetchData("SELECT * FROM employee_payroll WHERE start BETWEEN CAST('2021-01-01'AS DATE) AND DATE(NOW())");
    }
}
