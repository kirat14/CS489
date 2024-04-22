package online.tarikmoumini.cs489.model;

import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;


    @Column(name = "firstname", nullable = false)
    @NonNull
    private String firstname;


    @Column(name = "lastname", nullable = false)
    @NonNull
    private String lastname;


    private double yearly_salary;


/*     @OneToOne(mappedBy = "employee")
    private RetirementPlan retirement_plan; */


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;


   /* @ManyToMany
    @JoinTable(
            name = "employee_projects",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projects; */
}
