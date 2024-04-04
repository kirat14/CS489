package com.tarik.moumini;

import java.time.LocalDate;

public class Enrollment {
    public Employee employee;
    public LocalDate enrollmentDate;

    public Enrollment(Employee employee, LocalDate enrollmentDate) {
        this.employee = employee;
        this.enrollmentDate = enrollmentDate;
    }
}
