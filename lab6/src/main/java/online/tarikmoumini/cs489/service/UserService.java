package online.tarikmoumini.cs489.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import online.tarikmoumini.cs489.model.Dentist;
import online.tarikmoumini.cs489.model.Manager;
import online.tarikmoumini.cs489.model.Patient;
import online.tarikmoumini.cs489.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Dentist addDentist(Dentist dentist) {
        // Set user type for Dentist
        dentist.setUserType("Dentist");
        return (Dentist) userRepository.save(dentist);
    }

    public Patient addPatient(Patient patient) {
        // Set user type for Patient
        patient.setUserType("Patient");
        return (Patient) userRepository.save(patient);
    }

    public Manager addManager(Manager manager) {
        // Set user type for Manager
        manager.setUserType("Manager");
        return (Manager) userRepository.save(manager);
    }

    // Other methods for user-related operations (update, delete, find by id, etc.) can be added here
}

