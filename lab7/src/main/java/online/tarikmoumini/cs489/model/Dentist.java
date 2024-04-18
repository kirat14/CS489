package online.tarikmoumini.cs489.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@Entity
@DiscriminatorValue("dentist")
@Getter
@Setter
public class Dentist extends User {
    @Column(name = "specialization")
    private String specialization;

    @OneToMany(mappedBy = "dentist")
    private List<Appointment> appointments = new ArrayList<>();

    public Dentist(){}
    public Dentist(String username, String password, String firstname, String lastname, String email, Address address, String specialization){
        super(username, password, firstname, lastname, email, address);
        this.specialization = specialization;
    }

}
