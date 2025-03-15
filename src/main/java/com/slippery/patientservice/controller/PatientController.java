package com.slippery.patientservice.controller;

import com.slippery.patientservice.dto.PatientDto;
import com.slippery.patientservice.models.Patient;
import com.slippery.patientservice.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController{
    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }
    @GetMapping("/running")
    public String checkStatus(){
        return "status: up";
    }
    @PostMapping("/create")
    public ResponseEntity<PatientDto> createPatient(@Valid @RequestBody Patient patient) {
        var createdPatient =service.createPatient(patient);
        return ResponseEntity.status(HttpStatusCode.valueOf(createdPatient.getStatusCode())).body(createdPatient);
    }

    @PutMapping("/{id}/update-record")
    public ResponseEntity<PatientDto> updatePatient(@Valid @RequestBody Patient patient,@PathVariable Long id) {
        var updatedPatient=service.updatePatient(patient, id);
        return ResponseEntity.status(HttpStatusCode.valueOf(updatedPatient.getStatusCode())).body(updatedPatient);
    }
    @GetMapping("/{id}/get")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable Long id) {
        var patientWithId =service.getPatientById(id);
        return ResponseEntity.status(patientWithId.getStatusCode()).body(patientWithId);
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<PatientDto> deletePatientById(@PathVariable Long id) {
        var deletePatient =service.deletePatientById(id);
        return ResponseEntity.status(HttpStatusCode.valueOf(deletePatient.getStatusCode())).body(deletePatient);
    }
    @DeleteMapping("/delete/all")
    public ResponseEntity<PatientDto> deleteAllPatient() {
        var deleteAll =service.deleteAllPatient();
        return ResponseEntity.status(HttpStatusCode.valueOf(deleteAll.getStatusCode())).body(deleteAll);
    }

    @GetMapping("/all")
    public ResponseEntity<PatientDto> getAllPatient() {
        var allPatients =service.getAllPatient();

        return ResponseEntity.status(HttpStatusCode.valueOf(allPatients.getStatusCode())).body(allPatients);
    }
}
