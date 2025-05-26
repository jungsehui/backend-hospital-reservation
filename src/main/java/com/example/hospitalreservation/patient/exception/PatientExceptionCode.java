package com.example.hospitalreservation.patient.exception;

import com.example.hospitalreservation.common.exception.ExceptionCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum PatientExceptionCode implements ExceptionCode {

    PATIENT_NOT_FOUND(HttpStatus.NO_CONTENT, "R003", "존재하지 않는 환자입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    PatientExceptionCode(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
