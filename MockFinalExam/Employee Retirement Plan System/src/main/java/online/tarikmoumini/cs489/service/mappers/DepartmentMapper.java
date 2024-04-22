package online.tarikmoumini.cs489.service.mappers;

import java.util.List;
import java.util.stream.Collectors;

import online.tarikmoumini.cs489.dto.DepartmentDTO;
import online.tarikmoumini.cs489.dto.EmployeeDTO;
import online.tarikmoumini.cs489.model.Department;
import online.tarikmoumini.cs489.model.Employee;

public final class DepartmentMapper {
    /* Mappers */
    public static DepartmentDTO mapToDepartmentDTO(Department department) {
        List<EmployeeDTO> employeeDTOList = department.getEmployees().stream().map(EmployeeMapper::mapToEmployeeDTO)
                .collect(Collectors.toList());

        DepartmentDTO departmentDTO = new DepartmentDTO(department.getName(), employeeDTOList);
        return departmentDTO;
    }

    public static Department mapToDepartment(DepartmentDTO departmentDTO) {
        Department department = new Department(departmentDTO.name());
        List<Employee> employees = departmentDTO.employees().stream().map(EmployeeMapper::mapToEmployee).collect(Collectors.toList());
        department.setEmployees(employees);
        return department;
    }
}
