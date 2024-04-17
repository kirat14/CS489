package online.tarikmoumini.cs489.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("manager")
public class Manager extends User {

}
