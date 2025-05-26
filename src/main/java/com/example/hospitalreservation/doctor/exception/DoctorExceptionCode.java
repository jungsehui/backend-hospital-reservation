package com.example.hospitalreservation.doctor.exception;

import com.example.hospitalreservation.common.exception.ExceptionCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum DoctorExceptionCode implements ExceptionCode {

    DOCTOR_NOT_FOUND(HttpStatus.NO_CONTENT, "D001", "존재하지 않는 의사입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    DoctorExceptionCode(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
