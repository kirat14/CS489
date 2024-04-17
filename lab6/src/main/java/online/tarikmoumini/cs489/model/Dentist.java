package online.tarikmoumini.cs489.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Entity
@DiscriminatorValue("dentist")
@Getter
@Setter
@RequiredArgsConstructor
public class Dentist extends User {
    @Column(name = "specialization")
    private String specialization;
}
