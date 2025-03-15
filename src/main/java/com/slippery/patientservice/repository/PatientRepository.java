package com.slippery.patientservice.repository;

import com.slippery.patientservice.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    boolean existsByEmail(String email);
}
