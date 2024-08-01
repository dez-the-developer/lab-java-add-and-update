package com.example.hospitalmanagement.service;

import com.example.hospitalmanagement.model.Employee;
import com.example.hospitalmanagement.model.Patient;
import com.example.hospitalmanagement.repository.EmployeeRepository;
import com.example.hospitalmanagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalService {

    private final EmployeeRepository employeeRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public HospitalService(EmployeeRepository employeeRepository, PatientRepository patientRepository) {
        this.employeeRepository = employeeRepository;
        this.patientRepository = patientRepository;
    }

    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Employee addDoctor(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateDoctorStatus(int doctorId, Employee.Status status) {
        Employee doctor = employeeRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        doctor.setStatus(status);
        return employeeRepository.save(doctor);
    }

    public Employee updateDoctorDepartment(int doctorId, String department) {
        Employee doctor = employeeRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        doctor.setDepartment(department);
        return employeeRepository.save(doctor);
    }

    public Patient updatePatient(int patientId, Patient patientDetails) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        patient.setName(patientDetails.getName());
        patient.setDateOfBirth(patientDetails.getDateOfBirth());
        patient.setAdmittedBy(patientDetails.getAdmittedBy());
        return patientRepository.save(patient);
    }
}
