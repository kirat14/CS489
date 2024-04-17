package online.tarikmoumini.cs489.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@DiscriminatorValue("patient")
@RequiredArgsConstructor
public class Patient extends User {
    @Column(name = "DOB")
    private LocalDate dob;

    public Patient(int id, String username, String password, String userType, String firstname, String lastname,
            String email, Address address, LocalDate dob) {
        super(id, username, password, userType, firstname, lastname, email, address);
        this.dob = dob;
    }

    public Patient(LocalDate dob) {
        this.dob = dob;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
