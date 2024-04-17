package online.tarikmoumini.cs489.repository;

import online.tarikmoumini.cs489.model.Patient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
