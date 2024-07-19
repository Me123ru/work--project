package com.hospital.hospital.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.hospital.exception.ResourceNotFoundException;
import com.hospital.hospital.model.Hospital;
import com.hospital.hospital.repository.HospitalRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class HospitalController {

    @Autowired
    private HospitalRepository hospitalRepository;

    // Get All Hospitals

    @GetMapping("/hospitals")
    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    // Create Hospital Rest API

    @PostMapping("/hospitals")
    public Hospital addHospital(@RequestBody Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    // Get Hospital by Id Rest API

    @GetMapping("/hospitals/{id}")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable Long id) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital Not Exist with Id :" + id));
        return ResponseEntity.ok(hospital);
    }

    // Update Hospital Rest API

    @PutMapping("/hospitals/{id}")
    public ResponseEntity<Hospital> updateHospital(@PathVariable Long id, @RequestBody Hospital hospitalDetails) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital Not Exist With Id :" + id));

        hospital.setFirstName(hospitalDetails.getFirstName());
        hospital.setLastName(hospitalDetails.getLastName());
        hospital.setGender(hospitalDetails.getGender());
        hospital.setEmail(hospitalDetails.getEmail());
        hospital.setPhonenumber(hospitalDetails.getPhonenumber());

        Hospital updatedHospital = hospitalRepository.save(hospital);
        return ResponseEntity.ok(updatedHospital);
    }

    // Delete Hospital Rest API

    @DeleteMapping("/hospitals/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteHospital(@PathVariable Long id) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital Not Exist With Id :" + id));

        hospitalRepository.delete(hospital);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}

