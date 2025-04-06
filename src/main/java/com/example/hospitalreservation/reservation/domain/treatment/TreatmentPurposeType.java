package com.example.hospitalreservation.reservation.domain.treatment;

import com.example.hospitalreservation.common.exception.ApplicationException;
import com.example.hospitalreservation.common.type.Type;
import com.example.hospitalreservation.reservation.exception.ReservationExceptionCode;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum TreatmentPurposeType implements Type {

    GENERAL_CHECKUP("일반 검진", 10_000),
    COLD_SYMPTOMS("감기 증상", 15_000),
    FATIGUE_RECOVERY_INJECTION("피로 회복 주사", 20_000),
    ;

    private final String type;
    private final int fee;

    TreatmentPurposeType(String type, int fee) {
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
    public static TreatmentPurposeType of(String name) {
        for (TreatmentPurposeType value : values()) {
            if (value.getType().equals(name)) {
                return value;
            }
        }
        throw new ApplicationException(ReservationExceptionCode.INVALID_RESERVATION_REASON);
    }
}
