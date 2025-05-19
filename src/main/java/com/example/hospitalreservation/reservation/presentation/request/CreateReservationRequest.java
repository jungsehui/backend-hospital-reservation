package com.example.hospitalreservation.reservation.presentation.request;

import com.example.hospitalreservation.reservation.application.command.CreateReservationCommand;

import java.time.LocalDateTime;

public record CreateReservationRequest(
        Long doctorId,
        Long patientId,
        LocalDateTime reservationStartTime,
        LocalDateTime reservationEndTime,
        String reason
) {

    public CreateReservationCommand toCommand() {
        return new CreateReservationCommand(doctorId, patientId, reservationStartTime, reservationEndTime, reason);
    }
}
