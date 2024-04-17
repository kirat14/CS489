package online.tarikmoumini.cs489.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("patient")
@Getter
@Setter
@RequiredArgsConstructor
public class Patient extends User {
    @Column(name = "DOB")
    @NonNull
    private LocalDate dob;
}
