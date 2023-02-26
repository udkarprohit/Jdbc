package com.bridgelabz;

import java.sql.*;

public class JDBCDemoClass {
    static String url = "jdbc:mysql://localhost:3306/payroll_service";
    static String userName="root";
    static String password="";
    public static void fetchData(String queryFetch) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.print("The error in the class -" + e);
        }
        try {
            Connection connection =DriverManager.getConnection(url,userName,password);
            Statement st = connection.createStatement();
            System.out.println("The felting the data is started  \n");
            ResultSet rs =st.executeQuery(queryFetch);
            //The result we want in the table formatte so we use RS
            //fetch the data
            System.out.println("The result set - " + rs);
            while (rs.next()) {
                System.out.println(rs.getInt(1)+" "+rs.getNString(2) + "  " + rs.getDate(3)+" "+ rs.getDouble(4 ) + " "+ rs.getString(5));
            }
            System.out.println("Closing point of the Fetch method");
            st.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("The error at the Connection - " + e);
        }
    }
    public static void set(){
        try {
            Connection connection =DriverManager.getConnection(url,userName,password);
            // Statement st = connection.createStatement();

            String query ="update employee_payroll set salary = ? where id = ? ";
            // " insert into employee_payroll(name,salary,start) values(?,?,?)";
            System.out.println("--------The query point for the updating ------ \n");
            PreparedStatement st = connection.prepareStatement(query);
            st.setDouble(1,3600000);
            st.setInt(2,4);

            int count = st.executeUpdate();
            System.out.println("The no row affected + "+ count);
            st.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("error - "+ e);
        }

    }
    public static void main(String[] args) {
        fetchData("select * from employee_payroll");
        set();
        fetchData("select * from employee_payroll");
    }
}
