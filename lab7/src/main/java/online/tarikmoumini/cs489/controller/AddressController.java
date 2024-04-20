package online.tarikmoumini.cs489.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.web.bind.annotation.*;


import online.tarikmoumini.cs489.dto.AddressDTO;
import online.tarikmoumini.cs489.service.impl.AddressServiceImpl;

@RestController
@RequestMapping(value = "adsweb/api/v1/address")
public class AddressController {

    @Autowired
    private AddressServiceImpl address_service;

    @GetMapping(value = "/list")
    public ResponseEntity<List<AddressDTO>> listAddresss() {
        return ResponseEntity.ok(address_service.getAllAddresses());
    }
}
