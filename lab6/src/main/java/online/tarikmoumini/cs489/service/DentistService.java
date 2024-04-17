package online.tarikmoumini.cs489.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import online.tarikmoumini.cs489.model.Dentist;
import online.tarikmoumini.cs489.repository.DentistRepository;

@Service
public class DentistService {

    private DentistRepository dentistRepository;

    @Autowired
    public DentistService(DentistRepository dentistRepository) {
        this.dentistRepository = dentistRepository;
    }

    public Dentist addNewDentist(Dentist newDentist) {
        return dentistRepository.save(newDentist);
    }

    // Add more service methods as needed
}
