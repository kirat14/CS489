package online.tarikmoumini.cs489.repository;

import online.tarikmoumini.cs489.model.Department;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
