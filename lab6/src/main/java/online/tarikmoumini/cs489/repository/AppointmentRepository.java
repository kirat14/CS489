package online.tarikmoumini.cs489.repository;

import online.tarikmoumini.cs489.model.Appointment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
}
