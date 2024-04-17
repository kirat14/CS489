package online.tarikmoumini.cs489.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("manager")
public class Manager extends User {
    
    @OneToMany(mappedBy = "manager")
    private List<Appointment> appointments = new ArrayList<>();


    public Manager(){}
    public Manager(String username, String password, String firstname, String lastname, String email, Address address){
        super(username, password, firstname, lastname, email, address);
    }

}
