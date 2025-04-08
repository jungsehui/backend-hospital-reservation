package com.example.hospitalreservation.patient.domain.entity;

import com.example.hospitalreservation.common.exception.ApplicationException;
import com.example.hospitalreservation.patient.domain.exception.PatientExceptionCode;

import java.util.Objects;

public class Patient {

    private Long id;
    private String name;
    private int age;

    public void validatePositiveId(Long id) {
        if (!(Objects.isNull(id) || id <= 0)) {
            throw new ApplicationException(PatientExceptionCode.INVALID_POSITIVE_PATIENT_ID);
        }
    }
}
