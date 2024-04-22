package online.tarikmoumini.cs489.dto;

import java.time.LocalDate;

public record RetirementPlanDTO(String reference_number, LocalDate enrollment_date, LocalDate retirement_date, double monthly_contribution) {

}
