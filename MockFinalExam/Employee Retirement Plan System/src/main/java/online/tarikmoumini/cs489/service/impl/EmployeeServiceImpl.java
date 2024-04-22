package online.tarikmoumini.cs489.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import online.tarikmoumini.cs489.dto.EmployeeDTO;
import online.tarikmoumini.cs489.model.Employee;
import online.tarikmoumini.cs489.model.RetirementPlan;
import online.tarikmoumini.cs489.repository.EmployeeRepository;
import online.tarikmoumini.cs489.repository.RetirementPlanRepository;
import online.tarikmoumini.cs489.service.mappers.EmployeeMapper;

@Service
public class EmployeeServiceImpl {

    private final EmployeeRepository employeeRepository;
    private final RetirementPlanRepository retirementPlanRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employee_repository, RetirementPlanRepository retirementPlanRepository) {
        this.employeeRepository = employee_repository;
        this.retirementPlanRepository = retirementPlanRepository;
    }
    public List<EmployeeDTO> getAllEmployees(){
        return employeeRepository.findAll()
        .stream()
        .map(EmployeeMapper::mapToEmployeeDTO)
        .toList();
    }

    public Optional<EmployeeDTO> getEmployeeById(Integer employee_id) {
        return employeeRepository.findById(employee_id)
                .map(EmployeeMapper::mapToEmployeeDTO);
    }

    public List<EmployeeDTO> getUpcomingRetirees(){
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayNextMonth = currentDate.plusMonths(1).withDayOfMonth(1);
        LocalDate lastDayNextMonth = firstDayNextMonth.plusMonths(1).minusDays(1);
        
        return employeeRepository.findUpcomingRetirees(firstDayNextMonth, lastDayNextMonth)
        .stream()
        .map(EmployeeMapper::mapToEmployeeDTO)
        .toList();
    }

    @Transactional
    public EmployeeDTO addNewEmployee(EmployeeDTO employeeRequest) {
        /* RetirementPlan retirementPlan = new RetirementPlan(employeeRequest.referenceNumber(), employeeRequest.enrollmentDate(), employeeRequest.retirementDate(), employeeRequest.monthlyContribution());

        retirementPlan = this.retirementPlanRepository.save(retirementPlan); */

        Employee employee = new Employee(employeeRequest.firstName(), employeeRequest.lastName());
        /* employee.setRetirement_plan(retirementPlan); */

        return EmployeeMapper.mapToEmployeeDTO(this.employeeRepository.save(employee));
        
    }
}

