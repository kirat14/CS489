package online.tarikmoumini.cs489.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import online.tarikmoumini.cs489.model.Address;
import online.tarikmoumini.cs489.model.Patient;
import online.tarikmoumini.cs489.repository.PatientRepository;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    
    

    public Patient addNewPatient(Patient newPatient) {
        return patientRepository.save(newPatient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll(Sort.by("lastname").ascending())
                .stream()
//                .sorted(Comparator.comparing(Publisher::getName))
                .map(p -> new Patient(
                        p.getUsername(),
                        p.getPassword(), 
                        p.getFirstname(),
                        p.getLastname(),
                        p.getEmail(),
                        (p.getAddress() != null) ? new Address(
                            p.getAddress().getStreet(),
                        p.getAddress().getCity(),
                        p.getAddress().getState(),
                        p.getAddress().getZip_code()
                        ): null,
                        p.getDob()
                        )).toList();
    }
}
