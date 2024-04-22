package online.tarikmoumini.cs489.service.mappers;

import online.tarikmoumini.cs489.dto.EmployeeDTO;
import online.tarikmoumini.cs489.model.Department;
import online.tarikmoumini.cs489.model.Employee;
import online.tarikmoumini.cs489.model.RetirementPlan;

public final class EmployeeMapper {
    /* Mappers */
    public static EmployeeDTO mapToEmployeeDTO(Employee employee) {
        RetirementPlan retirement_plan = null;
        /* if (employee.getRetirement_plan() != null) {
            retirement_plan = employee.getRetirement_plan();
        } */
        EmployeeDTO employeeDTO = new EmployeeDTO(employee.getFirstname(), employee.getLastname(), employee.getYearly_salary(), retirement_plan.getReference_number(), retirement_plan.getEnrollment_date(), retirement_plan.getRetirement_date(), retirement_plan.getMonthly_contribution(), employee.getDepartment().getName());
        return employeeDTO;
    }

    public static Employee mapToEmployee(EmployeeDTO employeeDTO) {
        RetirementPlan retirement_plan = new RetirementPlan(employeeDTO.referenceNumber(), employeeDTO.enrollmentDate(), employeeDTO.retirementDate(), employeeDTO.monthlyContribution());

        Employee employee = new Employee(employeeDTO.firstName(), employeeDTO.lastName());
        employee.setYearly_salary(employeeDTO.yearlySalary());
        //employee.setRetirement_plan(retirement_plan);
        employee.setDepartment(new Department(employeeDTO.departmentName()));
        return employee;
    }
}
