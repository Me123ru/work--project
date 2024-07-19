package com.hospital.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.hospital.model.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    
}
