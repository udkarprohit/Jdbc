package com.bridgelabz;
import java.util.*;
import java.sql.*;

public class EmployeePayRollService {
    List<EmployeePayRollData> employeePayrollDataList = new ArrayList<>();
    public Connection driver() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_service", "root", "");
        return connection;
    }

    public List<EmployeePayRollData> retrieveDataFromDatabase(String query) {
        List<EmployeePayRollData> employeePayrollDataList = new ArrayList<>();
        try {
            Connection connection = driver();
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(query);
            while (resultSet.next()) {
                EmployeePayRollData employeePayRoll = new EmployeePayRollData(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getDouble(4),resultSet.getDate(5).toLocalDate(),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getFloat(9),resultSet.getFloat(10),resultSet.getFloat(11),resultSet.getFloat(12),resultSet.getFloat(13));
                employeePayrollDataList.add(employeePayRoll);
            }
            st.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("The error at the Connection - " + e);
        }
        return employeePayrollDataList;
    }

    public void updateDatabase(String query){
        try {
            Connection connection = driver();
            Statement statement = connection.createStatement();
            int count =statement.executeUpdate(query);
            System.out.println("The no row are updated "+ count);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Exception - "+ e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateUsingPreparedStatement(String query) throws Exception {
        try {
            Connection connection = driver();
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

    public List<EmployeePayRollData> getPayrollDataByName(String name) {
        List<EmployeePayRollData> employeePayRollData = new ArrayList<>();
        try {
            Connection connection = driver();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee_payroll WHERE name = ?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                EmployeePayRollData employeePayRoll = new EmployeePayRollData(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getDouble(4),resultSet.getDate(5).toLocalDate(),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getFloat(9),resultSet.getFloat(10),resultSet.getFloat(11),resultSet.getFloat(12),resultSet.getFloat(13));
                employeePayRollData.add(employeePayRoll);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return employeePayRollData;
    }

    public List<EmployeePayRollData> getPayrollDataByDate() {
        List<EmployeePayRollData> employeePayRollData = new ArrayList<>();
        try {
            Connection connection = driver();
            PreparedStatement preparedStatement  = connection.prepareStatement("SELECT * FROM employee_payroll WHERE start between cast('2005-12-11' as date) and cast('2020-12-06' as date)");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                EmployeePayRollData employeePayRoll = new EmployeePayRollData(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getDouble(4),resultSet.getDate(5).toLocalDate(),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getFloat(9),resultSet.getFloat(10),resultSet.getFloat(11),resultSet.getFloat(12),resultSet.getFloat(13));
                employeePayRollData.add(employeePayRoll);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return employeePayRollData;
    }

    public void sumAvgMinMaxCount() {
        try {
            Connection connection = driver();
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
    public boolean addEMployeePayRollToDataBase() throws Exception {
        try {
            Connection connection = driver();
            PreparedStatement statement = connection.prepareStatement("insert into employee_payroll(name,gender,salary,start,phoneNumber,address,department,basic_pay,deductions,taxable_pay,net_pay) values('Rahiman','M',200000,'2010-12-06','7598130306','Kurnool','HR',25000.0,24650.00,43500.00,76456.00);");
            boolean response = statement.execute();
            if (response) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next())
                    System.out.println(resultSet.getInt(1));
            } else {
                int count = statement.getUpdateCount();
                System.out.println(count);
            }
            return response;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public EmployeePayRollData getLastIdObject() {
        EmployeePayRollData employeePayRoll = null;
        try {
            Connection connection = driver();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee_payroll ORDER BY id desc limit 1;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeePayRoll = new EmployeePayRollData(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getDate(5).toLocalDate(), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getFloat(9), resultSet.getFloat(10), resultSet.getFloat(11), resultSet.getFloat(12), resultSet.getFloat(13));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return employeePayRoll;
    }
    private void addEmployeeToEmployeeObject() {
        EmployeePayRollService employeePayRoll = new EmployeePayRollService();
        EmployeePayRollData newEmployee = getLastIdObject();
        employeePayRoll.employeePayrollDataList.add(newEmployee);
    }
    public static void main(String[] args) throws Exception {
        EmployeePayRollService employeePayRollService = new EmployeePayRollService();
        System.out.println(employeePayRollService.retrieveDataFromDatabase("select * from employee_payroll"));
        employeePayRollService.updateDatabase("update employee_payroll set salary = 3000000.0 where name = 'Terissa' ");
        System.out.println(employeePayRollService.retrieveDataFromDatabase("select * from employee_payroll"));
        employeePayRollService.updateUsingPreparedStatement("update employee_payroll set salary = 3000000.0 where name = 'Terissa'");
        System.out.println(employeePayRollService.retrieveDataFromDatabase("select * from employee_payroll"));
        System.out.println(employeePayRollService.getPayrollDataByName("Prasanth"));
        System.out.println(employeePayRollService.getPayrollDataByDate());
        System.out.println(employeePayRollService.retrieveDataFromDatabase("select * from employee_payroll"));
        employeePayRollService.sumAvgMinMaxCount();
        System.out.println(employeePayRollService.retrieveDataFromDatabase("select * from employee_payroll"));
        boolean addEmployee = employeePayRollService.addEMployeePayRollToDataBase();
        if(!addEmployee){
            employeePayRollService.addEmployeeToEmployeeObject();
        }
        else {
            System.out.println("Employee not added");
        }
        System.out.println(employeePayRollService.retrieveDataFromDatabase("select * from employee_payroll"));
    }
}