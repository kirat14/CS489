package online.tarikmoumini.cs489.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String street;

    @NonNull
    private String city;

    @NonNull
    private String state;

    @Column(length = 5)
    @NonNull
    private String zip_code;

    @OneToOne(mappedBy = "address")
    private Surgery surgery;

    @OneToOne(mappedBy = "address")
    private User user;
}
