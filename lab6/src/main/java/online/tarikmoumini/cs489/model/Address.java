package online.tarikmoumini.cs489.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String street;
    private String city;
    private String state;
    @Column(length = 5)
    private String zip_code;

    @OneToOne(mappedBy = "address")
    private Surgery surgery;

    @OneToOne(mappedBy = "address")
    private User user;
}
