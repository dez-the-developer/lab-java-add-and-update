package com.example.hospitalmanagement.controller;

import com.example.hospitalmanagement.model.Employee;
import com.example.hospitalmanagement.model.Patient;
import com.example.hospitalmanagement.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hospital")
public class HospitalController {

    private final HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @PostMapping("/patients")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        Patient newPatient = hospitalService.addPatient(patient);
        return new ResponseEntity<>(newPatient, HttpStatus.CREATED);
    }

    @PostMapping("/doctors")
    public ResponseEntity<Employee> addDoctor(@RequestBody Employee doctor) {
        Employee newDoctor = hospitalService.addDoctor(doctor);
        return new ResponseEntity<>(newDoctor, HttpStatus.CREATED);
    }

    @PutMapping("/doctors/{doctorId}/status")
    public ResponseEntity<Employee> changeDoctorStatus(@PathVariable int doctorId, @RequestBody Employee.Status status) {
        Employee updatedDoctor = hospitalService.updateDoctorStatus(doctorId, status);
        return new ResponseEntity<>(updatedDoctor, HttpStatus.OK);
    }

    @PutMapping("/doctors/{doctorId}/department")
    public ResponseEntity<Employee> updateDoctorDepartment(@PathVariable int doctorId, @RequestBody String department) {
        Employee updatedDoctor = hospitalService.updateDoctorDepartment(doctorId, department);
        return new ResponseEntity<>(updatedDoctor, HttpStatus.OK);
    }

    @PutMapping("/patients/{patientId}")
    public ResponseEntity<Patient> updatePatient(@PathVariable int patientId, @RequestBody Patient patientDetails) {
        Patient updatedPatient = hospitalService.updatePatient(patientId, patientDetails);
        return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
    }
}
