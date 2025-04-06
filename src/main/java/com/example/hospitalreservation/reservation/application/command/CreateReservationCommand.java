package com.example.hospitalreservation.reservation.application.command;

import com.example.hospitalreservation.reservation.domain.entity.Reservation;
import com.example.hospitalreservation.reservation.domain.treatment.TreatmentPurposeType;

import java.time.LocalDateTime;

public record CreateReservationCommand(
        Long doctorId,
        Long patientId,
        LocalDateTime startTime,
        LocalDateTime endTime,
        TreatmentPurposeType treatmentPurposeType
) {

    public Reservation toReservation() {
        Reservation reservation = new Reservation(null, doctorId, patientId, startTime, endTime, treatmentPurposeType);
        reservation.validateWithinBusinessHours(startTime);
        reservation.validateHourlySlot(startTime, endTime);
        return reservation;
    }
}
