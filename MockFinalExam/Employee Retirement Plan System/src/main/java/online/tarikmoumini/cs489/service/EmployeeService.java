package online.tarikmoumini.cs489.service;

import java.util.List;

import org.springframework.stereotype.Service;

import online.tarikmoumini.cs489.model.Employee;

@Service
public interface EmployeeService {
    List<Employee> getAllEmployees();
}

