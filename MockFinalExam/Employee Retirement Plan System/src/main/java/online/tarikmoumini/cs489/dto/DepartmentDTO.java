package online.tarikmoumini.cs489.dto;
import java.util.List;

public record DepartmentDTO(String name, List<EmployeeDTO> employees) {

}
