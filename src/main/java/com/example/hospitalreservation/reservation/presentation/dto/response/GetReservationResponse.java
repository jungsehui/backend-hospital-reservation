package com.example.hospitalreservation.reservation.presentation.dto.response;

import com.example.hospitalreservation.common.treatment.DefaultFeeCalculator;
import com.example.hospitalreservation.common.treatment.TreatmentPurpose;
import com.example.hospitalreservation.reservation.domain.entity.Reason;
import com.example.hospitalreservation.reservation.domain.entity.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public record GetReservationResponse(
        Long id,
        Long doctorId,
        Long patientId,
        LocalDateTime reservationStartTime,
        LocalDateTime reservationEndTime,
        int fee
) {

    public static GetReservationResponse from(Reservation reservation) {
        List<String> str = reservation.getReasons().stream()
                .map(Reason::getReason)
                .toList();

        TreatmentPurpose[] purposes = str.stream()
                .map(TreatmentPurpose::from)
                .toArray(TreatmentPurpose[]::new);

        int fee = DefaultFeeCalculator.SUM.calculate(purposes);

        return new GetReservationResponse(
                reservation.getId(),
                reservation.getDoctorId(),
                reservation.getPatientId(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                fee
        );
    }
}
