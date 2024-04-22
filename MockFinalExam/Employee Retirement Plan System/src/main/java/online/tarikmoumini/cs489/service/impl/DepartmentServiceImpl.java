package online.tarikmoumini.cs489.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.tarikmoumini.cs489.dto.DepartmentDTO;
import online.tarikmoumini.cs489.model.Department;
import online.tarikmoumini.cs489.model.Employee;
import online.tarikmoumini.cs489.repository.DepartmentRepository;
import online.tarikmoumini.cs489.repository.EmployeeRepository;
import online.tarikmoumini.cs489.service.mappers.DepartmentMapper;
import online.tarikmoumini.cs489.service.mappers.EmployeeMapper;


@Service
public class DepartmentServiceImpl {
    private DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public DepartmentDTO addNewDepartment(DepartmentDTO departmentRequest) {

        Department department = DepartmentMapper.mapToDepartment(departmentRequest);
        final Department savedDepartment = this.departmentRepository.save(department);
        List<Employee> employees = departmentRequest.employees()
        .stream()
        .map(EmployeeMapper::mapToEmployee)
        .peek(employee -> employee.setDepartment(savedDepartment))
        .collect(Collectors.toList());

        this.employeeRepository.saveAll(employees);
        
        return DepartmentMapper.mapToDepartmentDTO(this.departmentRepository.findById(department.getId()).get());
        
    }

}

