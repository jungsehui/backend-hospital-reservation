package com.example.hospitalreservation.reservation.presentation.dto.request;

import com.example.hospitalreservation.reservation.application.command.CreateReservationCommand;
import com.example.hospitalreservation.reservation.domain.treatment.TreatmentPurpose;

import java.time.LocalDateTime;

public record CreateReservationRequest(
        Long doctorId,
        Long patientId,
        LocalDateTime reservationStartTime,
        LocalDateTime reservationEndTime,
        TreatmentPurpose reason
) {

    public CreateReservationCommand toCommand() {
        return new CreateReservationCommand(doctorId, patientId, reservationStartTime, reservationEndTime, reason);
    }
}
