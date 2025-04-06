package com.example.hospitalreservation.common.message;

public enum Success implements Message {

    CREATE_RESERVATION("진료 예약 완료"),
    ;

    private final String message;

    Success(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
