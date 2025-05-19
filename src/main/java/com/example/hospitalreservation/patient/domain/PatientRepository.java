package com.example.hospitalreservation.patient.domain;

import com.example.hospitalreservation.common.exception.ApplicationException;
import com.example.hospitalreservation.patient.exception.PatientExceptionCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    default Patient getById(Long id) {
        return findById(id).orElseThrow(() -> new ApplicationException(PatientExceptionCode.PATIENT_NOT_FOUND));
    }
}
