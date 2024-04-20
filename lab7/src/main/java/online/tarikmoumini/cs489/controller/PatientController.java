package online.tarikmoumini.cs489.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import online.tarikmoumini.cs489.exception.PatientNotFoundException;
import online.tarikmoumini.cs489.model.Patient;
import online.tarikmoumini.cs489.dto.PatientDto;
import online.tarikmoumini.cs489.service.PatientService;

@RestController
@RequestMapping(value = "adsweb/api/v1/patient")
public class PatientController {

    @Autowired
    private PatientService patient_service;

    @GetMapping(value = "/list")
    public ResponseEntity<List<PatientDto>> listPatients() {
        return ResponseEntity.ok(patient_service.getAllPatients());
    }

    @GetMapping(value = "/get/{patientId}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable Integer patientId) throws PatientNotFoundException {
        return ResponseEntity.ok(patient_service.getPatientId(patientId));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<PatientDto> registerNewPatient(@Valid @RequestBody Patient patientRequest) {
        return new ResponseEntity<>(patient_service.addNewPatient(patientRequest), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{patientId}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Integer patientId, @RequestBody Patient editedPatient) throws PatientNotFoundException {
        return new ResponseEntity<>(patient_service.updatePatient(patientId, editedPatient), HttpStatus.OK);
    }


    @DeleteMapping(value = "/delete/{patientId}")
    public ResponseEntity<Void> deletePatient(@PathVariable Integer patientId) {
        patient_service.deletePatientById(patientId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping(value = "/search/{searchString}")
    public ResponseEntity<List<PatientDto>> searchPatient(@PathVariable String searchString) {
        return ResponseEntity.ok(patient_service.searchPatient(searchString));
    }

}
