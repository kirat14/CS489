package online.tarikmoumini.cs489.model;

import lombok.*;

import jakarta.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
/* @Entity */
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(nullable = false)
    @NonNull
    private String name;

    @ManyToMany(mappedBy = "projects")
    private List<Employee> employees;
}
