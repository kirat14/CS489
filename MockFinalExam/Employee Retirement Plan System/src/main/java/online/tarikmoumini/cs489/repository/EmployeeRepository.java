package online.tarikmoumini.cs489.repository;

import online.tarikmoumini.cs489.model.Employee;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
/*     @Query("SELECT e FROM Employee e inner join e.retirement_plan rp WHERE rp.retirement_date >= :firstDayNextMonth AND rp.retirement_date <= :lastDayNextMonth")
    List<Employee> findUpcomingRetirees(@Param("firstDayNextMonth") LocalDate firstDayNextMonth,
                                                 @Param("lastDayNextMonth") LocalDate lastDayNextMonth); */
}
