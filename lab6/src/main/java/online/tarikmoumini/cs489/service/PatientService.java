package online.tarikmoumini.cs489.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import online.tarikmoumini.cs489.model.Patient;
import online.tarikmoumini.cs489.repository.PatientRepository;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient addNewPatient(Patient newPatient) {
        return patientRepository.save(newPatient);
    }

    // Add more service methods as needed
}
