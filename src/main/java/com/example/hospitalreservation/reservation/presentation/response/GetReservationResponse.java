package com.example.hospitalreservation.reservation.presentation.response;

import com.example.hospitalreservation.reservation.domain.Reservation;
import com.example.hospitalreservation.reservation.domain.TreatmentPurpose;

import java.time.LocalDateTime;

public record GetReservationResponse(
        Long id,
        Long doctorId,
        Long patientId,
        LocalDateTime reservationStartTime,
        LocalDateTime reservationEndTime,
        int fee
) {

    public static GetReservationResponse from(Reservation reservation) {
        return new GetReservationResponse(
                reservation.getId(),
                reservation.getDoctor().getId(),
                reservation.getPatient().getId(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                TreatmentPurpose.from(reservation.getReason()).getFee()
        );
    }
}
