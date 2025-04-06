package com.example.hospitalreservation.reservation.presentation.dto.response;

import com.example.hospitalreservation.reservation.domain.entity.Reservation;

import java.time.LocalDateTime;

public record GetReservationResponse(
        Long id,
        Long doctorId,
        Long patientId,
        LocalDateTime reservationStartTime,
        LocalDateTime reservationEndTime,
        Integer fee
) {

    public static GetReservationResponse of(Reservation reservation) {
        return new GetReservationResponse(
                reservation.getId(),
                reservation.getDoctorId(),
                reservation.getPatientId(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                reservation.getTreatmentPurposeType().getFee()
        );
    }
}
