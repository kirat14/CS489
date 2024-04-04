package com.tarik.moumini;

import java.util.Comparator;

public class EmployeeComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) {
        int lastNameComparison = e1.lastName.compareTo(e2.lastName);
        if (lastNameComparison != 0) {
            return lastNameComparison;
        }
        return Double.compare(e2.yearlySalary, e1.yearlySalary); // Descending order of yearly salary
    }

}
