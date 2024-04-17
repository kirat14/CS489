package online.tarikmoumini.cs489.service.impl;

import org.springframework.stereotype.Service;

import online.tarikmoumini.cs489.model.Appointment;
import online.tarikmoumini.cs489.repository.AppointmentRepository;
import online.tarikmoumini.cs489.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment addNewAppointment(Appointment newAppointment) {
        return appointmentRepository.save(newAppointment);
    }
}
