package com.example.hospitalreservation.reservation.application.command;

import com.example.hospitalreservation.reservation.domain.TreatmentPurpose;

import java.time.LocalDateTime;

public record CreateReservationCommand(
        Long doctorId,
        Long patientId,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String reason
) {

    public TreatmentPurpose toPurpose() {
        return TreatmentPurpose.from(reason);
    }
}
