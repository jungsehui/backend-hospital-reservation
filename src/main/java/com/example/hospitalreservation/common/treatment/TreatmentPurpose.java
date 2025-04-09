package com.example.hospitalreservation.common.treatment;

import com.example.hospitalreservation.common.exception.ApplicationException;
import com.example.hospitalreservation.reservation.exception.ReservationExceptionCode;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum TreatmentPurpose implements Purpose {

    GENERAL_CHECKUP("일반 검진", 10_000),
    COLD_SYMPTOMS("감기 증상", 15_000),
    FATIGUE_RECOVERY_INJECTION("피로 회복 주사", 20_000),
    ;

    private final String type;
    private final int fee;

    TreatmentPurpose(String type, int fee) {
        this.type = type;
        this.fee = fee;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getFee() {
        return fee;
    }

    @JsonCreator
    public static TreatmentPurpose of(String name) {
        for (TreatmentPurpose value : values()) {
            if (value.getType().equals(name)) {
                return value;
            }
        }
        throw new ApplicationException(ReservationExceptionCode.INVALID_RESERVATION_REASON);
    }
}
