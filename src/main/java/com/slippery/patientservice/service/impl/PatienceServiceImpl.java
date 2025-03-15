package com.slippery.patientservice.service.impl;

import com.slippery.patientservice.dto.PatientDto;
import com.slippery.patientservice.models.Patient;
import com.slippery.patientservice.repository.PatientRepository;
import com.slippery.patientservice.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PatienceServiceImpl implements PatientService {
    private final PatientRepository repository;

    public PatienceServiceImpl(PatientRepository repository) {
        this.repository = repository;
    }

    @Override
    public PatientDto createPatient(Patient patient) {
        PatientDto response =new PatientDto();
        if(repository.existsByEmail(patient.getEmail())){
            response.setStatusCode(409);
            response.setMessage("patient with email "+patient.getEmail()+" already exists");
            return response;
        }
        patient.setRegisteredOn(LocalDate.now());
        repository.save(patient);
        response.setMessage("New patient record created");
        response.setStatusCode(201);
        response.setPatient(patient);
        return response;
    }

    @Override
    public PatientDto updatePatient(Patient patient, Long id) {
        return null;
    }

    @Override
    public PatientDto getPatientById(Long id) {
        PatientDto response =new PatientDto();
        Optional<Patient> findPatient =repository.findById(id);
        if(findPatient.isEmpty()){
            response.setMessage("Patient with id "+id+ "was not found");
            response.setStatusCode(404);
            return response;
        }
        response.setMessage("Patient with id "+id+ ".");
        response.setStatusCode(200);
        response.setPatient(findPatient.get());
        return response;
    }

    @Override
    public PatientDto deletePatientById(Long id) {
        PatientDto response =new PatientDto();
        var existingPatient =getPatientById(id);
        if(existingPatient.getStatusCode() !=200){
            return existingPatient;
        }
        repository.deleteById(id);
        response.setMessage("Patient with id "+id+" deleted successfully");
        response.setStatusCode(204);
        return response;
    }

    @Override
    public PatientDto deleteAllPatient() {
        PatientDto response =new PatientDto();
        var allPatients =getAllPatient();
        if(allPatients.getStatusCode() !=200){
            return allPatients;
        }
        repository.deleteAll();
        response.setMessage("All patient records deleted");
        response.setStatusCode(204);
        return response;
    }

    @Override
    public PatientDto getAllPatient() {
        PatientDto response =new PatientDto();
        var patients =repository.findAll();
        if(patients.isEmpty()){
            response.setMessage("patient list is empty!");
            response.setStatusCode(404);
            return response;
        }
        response.setPatientList(patients);
        response.setMessage("All patients in database");
        response.setStatusCode(200);
        return response;
    }
}
