package com.slippery.patientservice.service;

import com.slippery.patientservice.dto.PatientDto;
import com.slippery.patientservice.models.Patient;

public interface PatientService {
    PatientDto createPatient(Patient patient);
    PatientDto updatePatient(Patient patient,Long id);
    PatientDto getPatientById(Long id);
    PatientDto deletePatientById(Long id);
    PatientDto deleteAllPatient();
    PatientDto getAllPatient();
}
