package online.tarikmoumini.cs489.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Surgery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotBlank(message = "Surgery Name is required and cannot be null, empty string or blank spaces")
    @NonNull
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Surgery phone number is required and cannot be null, empty string or blank spaces")
    @NonNull
    private String phone_number;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", nullable = false)
    @NonNull
    private Address address;

    @OneToMany(mappedBy = "surgery")
    private List<Appointment> appointments = new ArrayList<>();

}
