package online.tarikmoumini.cs489.model;

import lombok.*;

import java.time.LocalDate;
import jakarta.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "retirement_plans")
public class RetirementPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(nullable = false, unique = true)
    @NonNull
    private String reference_number;

    @Column(nullable = false)
    @NonNull
    private LocalDate enrollment_date;

    @Column(nullable = false)
    @NonNull
    private LocalDate retirement_date;

    @NonNull
    private double monthly_contribution;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "employee_id", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_employee_retirement_plan"), unique = true)
    private Employee employee;
}
