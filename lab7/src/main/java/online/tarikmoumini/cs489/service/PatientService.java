package online.tarikmoumini.cs489.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import online.tarikmoumini.cs489.exception.PatientNotFoundException;
import online.tarikmoumini.cs489.dto.AddressDTO;
import online.tarikmoumini.cs489.dto.PatientDto;
import online.tarikmoumini.cs489.model.Address;
import online.tarikmoumini.cs489.model.Patient;
import online.tarikmoumini.cs489.repository.PatientRepository;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<PatientDto> getAllPatients() {
        return patientRepository.findAll(Sort.by("lastname").ascending())
                .stream()
                // .sorted(Comparator.comparing(Patient::getName))
                .map(p -> new PatientDto(
                        p.getFirstname(),
                        p.getLastname(),
                        p.getEmail(),
                        (p.getAddress() != null) ? new AddressDTO(
                                p.getAddress().getStreet(),
                                p.getAddress().getCity(),
                                p.getAddress().getState(),
                                p.getAddress().getZip_code(),
                                p.getFirstname()) : null))
                .toList();
    }

    public PatientDto getPatientId(Integer patient_id) throws PatientNotFoundException {
        return patientRepository.findById(patient_id)
                .map(this::mapToPatientDTO)
                .orElseThrow(() -> new PatientNotFoundException(
                        String.format("Error: Patient with Id, %d, is not found", patient_id)));
    }

    private PatientDto mapToPatientDTO(Patient patient) {
        AddressDTO address = null;
        if (patient.getAddress() != null) {
            address = new AddressDTO(patient.getAddress().getStreet(), patient.getAddress().getCity(),
                    patient.getAddress().getState(), patient.getAddress().getZip_code(), patient.getFirstname());
        }
        PatientDto patientDTO = new PatientDto(patient.getFirstname(), patient.getLastname(), patient.getEmail(),
                    address);
        return patientDTO;
    }

    public PatientDto addNewPatient(Patient patientRequest) {
        var savedPatient = patientRepository.save(patientRequest);
        return new PatientDto(
                savedPatient.getFirstname(), savedPatient.getLastname(), savedPatient.getEmail(),
                new AddressDTO(savedPatient.getAddress().getStreet(), savedPatient.getAddress().getCity(),
                        savedPatient.getAddress().getState(), savedPatient.getAddress().getZip_code(), savedPatient.getFirstname()));
    }

    public Patient updatePatient(Integer patient_id, Patient editedPatient) throws PatientNotFoundException {
        Optional<Patient> optional_patient = patientRepository.findById(patient_id);
        Patient patient;
        if (optional_patient.isPresent()) {
            patient = optional_patient.get();
            patient.setFirstname(editedPatient.getFirstname());
            patient.setLastname(editedPatient.getLastname());
            patient.setEmail(editedPatient.getEmail());
            patient.setDob(editedPatient.getDob());
            if (patient.getAddress() != null)
                patient.setAddress(new Address());

            var address = patient.getAddress();
            address.setStreet(editedPatient.getAddress().getStreet());
            address.setCity(editedPatient.getAddress().getCity());
            address.setState(editedPatient.getAddress().getState());
            address.setZip_code(editedPatient.getAddress().getZip_code());
            return patientRepository.save(patient);
        } else {
            throw new PatientNotFoundException(
                        String.format("Error: Patient with Id, %d, is not found", patient_id)); 
        }
    }


    public void deletePatientById(Integer patient_id) {
        patientRepository.deleteById(patient_id);
    }


    public List<PatientDto> searchPatient(String searchString) {
        return patientRepository.findPatientsByFirstnameContainingOrAddress_StreetContainingOrAddress_CityContainingOrAddress_StateContaining(
                searchString, searchString, searchString, searchString
        )
        .stream()
        .map(this::mapToPatientDTO)
        .collect(Collectors.toList());
    }
}
