package online.tarikmoumini.cs489.dto;
import java.time.LocalDate;

public record EmployeeDTO(String firstName, String lastName, double yearlySalary, String referenceNumber, LocalDate enrollmentDate, LocalDate retirementDate, double monthlyContribution, String departmentName) {

}
