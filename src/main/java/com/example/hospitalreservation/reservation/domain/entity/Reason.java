package com.example.hospitalreservation.reservation.domain.entity;

import lombok.Getter;

@Getter
public class Reason {

    private String reason;

    public Reason(String reason) {
        this.reason = reason;
    }
}
