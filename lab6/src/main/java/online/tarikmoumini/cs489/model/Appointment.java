package online.tarikmoumini.cs489.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "appointment_date", nullable = false)
    private LocalDateTime appointmentDate;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "surgery_id", nullable = false)
    private int surgeryId;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "ID", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "dentist_id", referencedColumnName = "ID", nullable = false)
    private Dentist dentist;

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "ID", nullable = false)
    private Manager manager;

    // Constructors, getters, and setters

    public Appointment() {
    }

    public Appointment(int id, LocalDateTime appointmentDate, boolean status, int surgeryId, Patient patient, Dentist dentist, Manager manager) {
        this.id = id;
        this.appointmentDate = appointmentDate;
        this.status = status;
        this.surgeryId = surgeryId;
        this.patient = patient;
        this.dentist = dentist;
        this.manager = manager;
    }

    // Getters and Setters (omitted for brevity)
}

