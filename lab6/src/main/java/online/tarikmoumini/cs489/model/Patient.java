package online.tarikmoumini.cs489.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@DiscriminatorValue("patient")
@Getter
@Setter
public class Patient extends User {
    @Column(name = "DOB")
    @NonNull
    private LocalDate dob;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments = new ArrayList<>();

    public Patient(){}
    public Patient(String username, String password, String firstname, String lastname, String email, Address address, LocalDate dob){
        super(username, password, firstname, lastname, email, address);
        this.dob = dob;
    }
}
