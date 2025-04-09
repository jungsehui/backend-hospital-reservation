package com.example.hospitalreservation.common.message;

import lombok.Getter;

@Getter
public enum Success {

    CREATE_RESERVATION("진료 예약 완료"),
    ;

    private final String message;

    Success(String message) {
        this.message = message;
    }
}
