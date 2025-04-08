package com.example.hospitalreservation.reservation.application.command;

import com.example.hospitalreservation.reservation.domain.entity.Reservation;
import com.example.hospitalreservation.common.treatment.TreatmentPurpose;

import java.time.LocalDateTime;

public record CreateReservationCommand(
        Long doctorId,
        Long patientId,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String reason
) {

    public Reservation toReservation() {
        Reservation reservation = new Reservation(null, doctorId, patientId, startTime, endTime, reason);
        reservation.validatePastTime(startTime);
        reservation.validateWithinBusinessHours(startTime);
        reservation.validateHourlySlot(startTime, endTime);
        return reservation;
    }
}
