package com.slippery.patientservice.controller;

import com.slippery.patientservice.dto.PatientDto;
import com.slippery.patientservice.models.Patient;
import com.slippery.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patients")
@Tag(name ="Patient",description = "API for managing patients")
public class PatientController{
    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }
    @GetMapping(path = "/running",produces = "application/text")
    @Operation(summary = "health confirmation")
    public String checkStatus(){
        return "status: up";
    }
    @PostMapping(path = "/create",consumes = "application/json",produces = "application/json")
    @Operation(summary = "creating a new patient")
    public ResponseEntity<PatientDto> createPatient(@Valid @RequestBody Patient patient) {
        var createdPatient =service.createPatient(patient);
        return ResponseEntity.status(HttpStatusCode.valueOf(createdPatient.getStatusCode())).body(createdPatient);
    }

    @PutMapping(path="/{id}/update-record",consumes = "application/json",produces = "application/json")
    @Operation(summary = "updating a patient")
    public ResponseEntity<PatientDto> updatePatient(@Valid @RequestBody Patient patient,@PathVariable Long id) {
        var updatedPatient=service.updatePatient(patient, id);
        return ResponseEntity.status(HttpStatusCode.valueOf(updatedPatient.getStatusCode())).body(updatedPatient);
    }
    @Operation(summary = "Getting a patient with id")
    @GetMapping(path = "/{id}/get",produces = "application/json")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable Long id) {
        var patientWithId =service.getPatientById(id);
        return ResponseEntity.status(patientWithId.getStatusCode()).body(patientWithId);
    }
    @DeleteMapping("/{id}/delete")
    @Operation(summary = "deleting a patient with id")
    public ResponseEntity<PatientDto> deletePatientById(@PathVariable Long id) {
        var deletePatient =service.deletePatientById(id);
        return ResponseEntity.status(HttpStatusCode.valueOf(deletePatient.getStatusCode())).body(deletePatient);
    }
    @DeleteMapping("/delete/all")
    @Operation(summary = "deleting all patients")
    public ResponseEntity<PatientDto> deleteAllPatient() {
        var deleteAll =service.deleteAllPatient();
        return ResponseEntity.status(HttpStatusCode.valueOf(deleteAll.getStatusCode())).body(deleteAll);
    }

    @GetMapping("/all")
    @Operation(summary = "Getting all patients")
    public ResponseEntity<PatientDto> getAllPatient() {
        var allPatients =service.getAllPatient();
        return ResponseEntity.status(HttpStatusCode.valueOf(allPatients.getStatusCode())).body(allPatients);
    }
}
