package online.tarikmoumini.cs489.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;
import online.tarikmoumini.cs489.dto.DepartmentDTO;
import online.tarikmoumini.cs489.service.impl.DepartmentServiceImpl;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "ersweb/api/v1/departments")
public class DepartmentController {

    @Autowired
    private DepartmentServiceImpl departmentService;

 
    @PostMapping(value = "/add")
    public ResponseEntity<DepartmentDTO> registerNewPatient(@Valid @RequestBody DepartmentDTO departmentRequest) {
        return ResponseEntity.ok(departmentService.addNewDepartment(departmentRequest));
    }


}
