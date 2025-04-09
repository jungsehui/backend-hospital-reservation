package com.example.hospitalreservation.reservation.presentation.dto.response;

import com.example.hospitalreservation.reservation.domain.entity.Reason;
import com.example.hospitalreservation.reservation.domain.entity.Reservation;
import com.example.hospitalreservation.reservation.domain.entity.TreatmentPurpose;
import com.example.hospitalreservation.reservation.domain.service.DefaultFeeCalculator;

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
                reservation.getDoctorId(),
                reservation.getPatientId(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                DefaultFeeCalculator.calculate(
                        reservation.getReasons().stream()
                                .map(Reason::getReason)
                                .map(TreatmentPurpose::from)
                                .toList()
                )
        );
    }
}
