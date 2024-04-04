package com.tarik.moumini;

import java.util.ArrayList;
import java.util.List;

public class PensionPlan {
    public String planReferenceNumber;
    public double monthlyContribution;
    public List<Enrollment> enrolledEmployees;

    public PensionPlan(String planReferenceNumber,  double monthlyContribution) {
        this.planReferenceNumber = planReferenceNumber;
        this.monthlyContribution = monthlyContribution;
        this.enrolledEmployees = new ArrayList<>();
    }

    public PensionPlan() {
        this.enrolledEmployees = new ArrayList<>();
    }

    public void enrollEmployee(Enrollment enrollment) {
        enrolledEmployees.add(enrollment);
    }
}
