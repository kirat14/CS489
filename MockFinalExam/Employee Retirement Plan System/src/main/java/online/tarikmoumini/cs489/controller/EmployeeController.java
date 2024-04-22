package online.tarikmoumini.cs489.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.validation.Valid;
import online.tarikmoumini.cs489.dto.EmployeeDTO;
import online.tarikmoumini.cs489.service.impl.EmployeeServiceImpl;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "ersweb/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employee_service;

    @GetMapping(value = "")
    public ResponseEntity<List<EmployeeDTO>> list_employees() {
        return ResponseEntity.ok(employee_service.getAllEmployees());
    }

    @GetMapping(value = "/{employee_id}")
    public ResponseEntity<?> getPatientById(@PathVariable Integer employee_id) {
        Optional<EmployeeDTO> employeeDTO = employee_service.getEmployeeById(employee_id);
        
        if (employeeDTO.isPresent()) {
            return ResponseEntity.ok(employeeDTO.get()); // Employee found, return 200 OK
        } else {
            String errorMessage = "Employee with ID " + employee_id + " not found";
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(errorMessage);
        }
    }

    @GetMapping(value = "/upcoming-retirees")
    public ResponseEntity<List<EmployeeDTO>> getUpcomingRetirees() {
        return ResponseEntity.ok(employee_service.getUpcomingRetirees());
    }
 
    @PostMapping(value = "/add")
    public ResponseEntity<EmployeeDTO> registerNewPatient(@Valid @RequestBody EmployeeDTO employeeRequest) {
        return ResponseEntity.ok(employee_service.addNewEmployee(employeeRequest));
    }

    /*@PutMapping(value = "/update/{patientId}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Integer patientId, @RequestBody Patient editedPatient) throws PatientNotFoundException {
        return new ResponseEntity<>(employee_service.updatePatient(patientId, editedPatient), HttpStatus.OK);
    }


    @DeleteMapping(value = "/delete/{patientId}")
    public ResponseEntity<Void> deletePatient(@PathVariable Integer patientId) {
        employee_service.deletePatientById(patientId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping(value = "/search/{searchString}")
    public ResponseEntity<List<PatientDto>> searchPatient(@PathVariable String searchString) {
        return ResponseEntity.ok(employee_service.searchPatient(searchString));
    } */

}
