package online.tarikmoumini.cs489.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import online.tarikmoumini.cs489.model.Patient;
import online.tarikmoumini.cs489.service.PatientService;

@RestController
@RequestMapping(value = "/ads/api/v1/patient")
public class PatientController {
    
    @Autowired
    private PatientService patient_service;

    @GetMapping(value = "/list")
    public ResponseEntity<List<Patient>> listPublishers() {
        return ResponseEntity.ok(patient_service.getAllPatients());
    }

}
