package com.example.hospitalreservation.reservation.presentation.dto.request;

import com.example.hospitalreservation.reservation.application.command.CreateReservationCommand;
import com.example.hospitalreservation.reservation.domain.entity.Reason;

import java.time.LocalDateTime;
import java.util.List;

public record CreateReservationRequest(
        Long doctorId,
        Long patientId,
        LocalDateTime reservationStartTime,
        LocalDateTime reservationEndTime,
        List<String> requestedReasons
) {

    public CreateReservationCommand toCommand() {
        List<Reason> reasons = requestedReasons.stream()
                .map(Reason::new)
                .toList();
        return new CreateReservationCommand(doctorId, patientId, reservationStartTime, reservationEndTime, reasons);
    }
}
