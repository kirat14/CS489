package com.tarik.moumini;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        // Create an array of employees
        Employee[] employees = {
                new Employee("Daniel", "Agar", LocalDate.of(2018, 1, 17), 105945.50),
                new Employee("Benard", "Shaw", LocalDate.of(2019, 4, 3), 197750.00),
                new Employee("Carly", "Agar", LocalDate.of(2014, 5, 16), 842000.75),
                new Employee("Wesley", "Schneider", LocalDate.of(2019, 5, 2), 74500.00)
        };

        // Create an array of pension plans
        PensionPlan[] pensionPlans = {
                new PensionPlan("EX1089", 100),
                new PensionPlan("SM2307", 1555.50),
        };

        pensionPlans[0].enrollEmployee(new Enrollment(employees[0], LocalDate.of(2023, 1, 17)));
        pensionPlans[1].enrollEmployee(new Enrollment(employees[2], LocalDate.of(2023, 1, 17)));

        Arrays.sort(employees, new EmployeeComparator());

        // Print employees in JSON format
        System.out.println("List of Employees in JSON format:");
        for (Employee employee : employees) {
            System.out.println(employeeToJson(employee));
        }

    }

    private static String employeeToJson(Employee employee) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        appendJsonKeyValue(json, "firstName", employee.firstName);
        json.append(",");
        appendJsonKeyValue(json, "lastName", employee.lastName);
        json.append(",");
        appendJsonKeyValue(json, "employmentDate", employee.employmentDate.toString());
        json.append(",");
        appendJsonKeyValue(json, "yearlySalary", String.valueOf(employee.yearlySalary));
        json.append("}");
        return json.toString();
    }

    private static void appendJsonKeyValue(StringBuilder json, String key, String value) {
        json.append("\"").append(key).append("\":\"").append(value).append("\"");
    }
}
