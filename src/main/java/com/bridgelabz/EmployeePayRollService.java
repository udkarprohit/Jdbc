package com.bridgelabz;
import java.util.*;
import java.sql.*;

public class EmployeePayRollService {
    static String url = "jdbc:mysql://localhost:3306/payroll_service";
    static String userName = "root";
    static String password = "";

    public void retrieveDataFromDatabase(String query) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.print("The error in the class -" + e);
        }
        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
            Statement st = connection.createStatement();
            System.out.println("The fetching the data is started  \n");
            ResultSet resultSet = st.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1)+":"+resultSet.getString(2)+":"+resultSet.getString(3)+":"+resultSet.getDouble(4)+":"+resultSet.getDate(5).toLocalDate()+":"+resultSet.getString(6)+":"+resultSet.getString(7)+":"+resultSet.getString(8)+":"+resultSet.getFloat(9)+":"+resultSet.getFloat(10)+":"+resultSet.getFloat(11)+":"+resultSet.getFloat(12)+":"+resultSet.getFloat(13));
            }
            st.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("The error at the Connection - " + e);
        }
    }

    public void updateDatabase(String query){
        try {
            Connection connection =DriverManager.getConnection(url,userName,password);
            Statement statement = connection.createStatement();
            int count =statement.executeUpdate(query);
            System.out.println("The no row are updated "+ count);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Exception - "+ e.getMessage());
        }
    }

    public boolean updateUsingPreparedStatement(String query) throws Exception {
        try {
            Connection connection =DriverManager.getConnection(url,userName,password);
            PreparedStatement statement = connection.prepareStatement(query);
            boolean response = statement.execute();
            if (response) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next())
                    System.out.println(resultSet.getInt(1)+":"+resultSet.getString(2)+":"+resultSet.getString(3)+":"+resultSet.getDouble(4)+":"+resultSet.getDate(5).toLocalDate()+":"+resultSet.getString(6)+":"+resultSet.getString(7)+":"+resultSet.getString(8)+":"+resultSet.getFloat(9)+":"+resultSet.getFloat(10)+":"+resultSet.getFloat(11)+":"+resultSet.getFloat(12)+":"+resultSet.getFloat(13));
            } else {
                int count = statement.getUpdateCount();
                System.out.println(count);
            }
            return response;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void getPayrollDataByName(String name) {
        try {
            Connection connection =DriverManager.getConnection(url,userName,password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee_payroll WHERE name = ?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1)+":"+resultSet.getString(2)+":"+resultSet.getString(3)+":"+resultSet.getDouble(4)+":"+resultSet.getDate(5).toLocalDate()+":"+resultSet.getString(6)+":"+resultSet.getString(7)+":"+resultSet.getString(8)+":"+resultSet.getFloat(9)+":"+resultSet.getFloat(10)+":"+resultSet.getFloat(11)+":"+resultSet.getFloat(12)+":"+resultSet.getFloat(13));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getPayrollDataByDate() {

        try {
            Connection connection =DriverManager.getConnection(url,userName,password);
            PreparedStatement preparedStatement  = connection.prepareStatement("SELECT * FROM employee_payroll WHERE start between cast('2022-07-12' as date) and cast('2022-08-22' as date)");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1)+":"+resultSet.getString(2)+":"+resultSet.getString(3)+":"+resultSet.getDouble(4)+":"+resultSet.getDate(5).toLocalDate()+":"+resultSet.getString(6)+":"+resultSet.getString(7)+":"+resultSet.getString(8)+":"+resultSet.getFloat(9)+":"+resultSet.getFloat(10)+":"+resultSet.getFloat(11)+":"+resultSet.getFloat(12)+":"+resultSet.getFloat(13));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void sumAvgMinMaxCount() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_service", "root", "");
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT " +
                            "    gender, " +
                            "    COUNT(*) AS total_employees, " +
                            "    SUM(salary) AS total_salary, " +
                            "    AVG(salary) AS average_salary, " +
                            "    MIN(salary) AS min_salary, " +
                            "    MAX(salary) AS max_salary " +
                            "FROM employee_payroll " +
                            "GROUP BY gender");

            // Execute the query
            ResultSet rs = stmt.executeQuery();

            // Process the results
            while (rs.next()) {
                String gender = rs.getString("gender");
                double totalEmployees = rs.getDouble("total_employees");
                double totalSalary = rs.getDouble("total_salary");
                double averageSalary = rs.getDouble("average_salary");
                double minimumSalary = rs.getDouble("min_salary");
                double maximumSalary = rs.getDouble("max_salary");

                // Print the results
                System.out.println(gender + " Total Employess: " + totalEmployees);
                System.out.println(gender + " Total Salary: " + totalSalary);
                System.out.println(gender + " Average  Salary: " + averageSalary);
                System.out.println(gender + " Minimum Salary: " + minimumSalary);
                System.out.println(gender + " Maximum Salary: " + maximumSalary);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) throws Exception {
        EmployeePayRollService employeePayRollService = new EmployeePayRollService();
        employeePayRollService.retrieveDataFromDatabase("select * from employee_payroll");
        employeePayRollService.updateDatabase("update employee_payroll set salary = 3000000.0 where name = 'Terissa' ");
        employeePayRollService.retrieveDataFromDatabase("select * from employee_payroll");
        employeePayRollService.updateUsingPreparedStatement("update employee_payroll set salary = 3000000.0 where name = 'Terissa'");
        employeePayRollService.retrieveDataFromDatabase("select * from employee_payroll");
        employeePayRollService.getPayrollDataByName("sreeja");
        employeePayRollService.getPayrollDataByDate();
        employeePayRollService.retrieveDataFromDatabase("select * from employee_payroll");
        employeePayRollService.sumAvgMinMaxCount();

    }
}
