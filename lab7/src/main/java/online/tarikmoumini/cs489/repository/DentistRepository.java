package online.tarikmoumini.cs489.repository;

import online.tarikmoumini.cs489.model.Dentist;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DentistRepository  extends JpaRepository<Dentist, Integer> {
}
