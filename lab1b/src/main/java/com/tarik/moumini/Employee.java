package com.tarik.moumini;

import java.time.LocalDate;

public class Employee {
    public long employeeId;
    public String firstName;
    public String lastName;
    public LocalDate employmentDate;
    public double yearlySalary;
    private static int employeeIdCounter = 0;

    // Constructor
    public Employee(String firstName, String lastName, LocalDate employmentDate, double yearlySalary) {
        this.employeeId = getNextEmployeeId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.employmentDate = employmentDate;
        this.yearlySalary = yearlySalary;
    }

    // Method to generate auto-incremented employeeId
    private static long getNextEmployeeId() {
        return ++employeeIdCounter;
    }

}
