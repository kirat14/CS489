package online.tarikmoumini.cs489.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "appointment_date", nullable = false)
    private LocalDateTime appointmentDate;

    @Column(name = "status", nullable = false)
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "surgery_id", referencedColumnName = "ID", nullable = false)
    private Surgery surgery;

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

    public Appointment(int id, LocalDateTime appointmentDate, boolean status, Surgery surgery, Patient patient, Dentist dentist, Manager manager) {
        this.id = id;
        this.appointmentDate = appointmentDate;
        this.status = status;
        this.surgery = surgery;
        this.patient = patient;
        this.dentist = dentist;
        this.manager = manager;
    }
}

