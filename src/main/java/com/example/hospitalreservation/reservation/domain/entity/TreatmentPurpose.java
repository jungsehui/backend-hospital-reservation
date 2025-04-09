package com.example.hospitalreservation.reservation.domain.entity;

import com.example.hospitalreservation.common.exception.ApplicationException;
import com.example.hospitalreservation.reservation.exception.ReservationExceptionCode;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum TreatmentPurpose {

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

    @JsonCreator
    public static TreatmentPurpose from(String name) {
        for (TreatmentPurpose value : values()) {
            if (value.getType().equals(name)) {
                return value;
            }
        }
        throw new ApplicationException(ReservationExceptionCode.INVALID_RESERVATION_REASON);
    }
}
