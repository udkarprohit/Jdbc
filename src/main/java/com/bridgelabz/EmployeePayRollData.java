package com.bridgelabz;

import java.time.LocalDate;

public class EmployeePayRollData {
    private int id;
    private String name;
    private String gender;
    private double salary;
    private LocalDate start;
    private String phonenumber;
    private String address;
    private String department;
    private float basicPay;
    private float deductions;
    private float taxablePay;
    private float incomeTax;
    private float netPay;

    public EmployeePayRollData(int id, String name, String gender, double salary, LocalDate start, String phonenumber, String address, String department, float basicPay, float deductions, float taxablePay, float incomeTax, float netPay) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.salary = salary;
        this.start = start;
        this.phonenumber = phonenumber;
        this.address = address;
        this.department = department;
        this.basicPay = basicPay;
        this.deductions = deductions;
        this.taxablePay = taxablePay;
        this.incomeTax = incomeTax;
        this.netPay = netPay;
    }

    public EmployeePayRollData(int id) {
        this.id = id;

    }

    public EmployeePayRollData() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public float getBasicPay() {
        return basicPay;
    }

    public void setBasicPay(float basicPay) {
        this.basicPay = basicPay;
    }

    public float getDeductions() {
        return deductions;
    }

    public void setDeductions(float deductions) {
        this.deductions = deductions;
    }

    public float getTaxablePay() {
        return taxablePay;
    }

    public void setTaxablePay(float taxablePay) {
        this.taxablePay = taxablePay;
    }

    public float getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(float incomeTax) {
        this.incomeTax = incomeTax;
    }

    public float getNetPay() {
        return netPay;
    }

    public void setNetPay(float netPay) {
        this.netPay = netPay;
    }

    @Override
    public String toString() {
        return "EmployeePayRollData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", salary=" + salary +
                ", start=" + start +
                ", phonenumber='" + phonenumber + '\'' +
                ", address='" + address + '\'' +
                ", department='" + department + '\'' +
                ", basicPay=" + basicPay +
                ", deductions=" + deductions +
                ", taxablePay=" + taxablePay +
                ", incomeTax=" + incomeTax +
                ", netPay=" + netPay +
                '}';
    }
}
