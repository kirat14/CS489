package online.tarikmoumini.cs489.model;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;


@Entity
@DiscriminatorValue("dentist")
@RequiredArgsConstructor
public class Dentist extends User {
    @Column(name = "specialization")
    private String specialization;

    public Dentist(int id, String username, String password, String userType, String firstname, String lastname,
            String email, Address address, String specialization) {
        super(id, username, password, userType, firstname, lastname, email, address);
        this.specialization = specialization;
    }

    public Dentist(String specialization) {
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
