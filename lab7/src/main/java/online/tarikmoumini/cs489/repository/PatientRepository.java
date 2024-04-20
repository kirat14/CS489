package online.tarikmoumini.cs489.repository;

import online.tarikmoumini.cs489.model.Patient;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    public List<Patient> findPatientsByFirstnameContainingOrAddress_StreetContainingOrAddress_CityContainingOrAddress_StateContaining(
            String name, String street, String city, String state
    );
}
