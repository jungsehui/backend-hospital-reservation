package com.example.hospitalreservation.doctor.domain;

import com.example.hospitalreservation.common.exception.ApplicationException;
import com.example.hospitalreservation.doctor.exception.DoctorExceptionCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    default Doctor getById(Long id) {
        return findById(id).orElseThrow(() -> new ApplicationException(DoctorExceptionCode.DOCTOR_NOT_FOUND));
    }
}
