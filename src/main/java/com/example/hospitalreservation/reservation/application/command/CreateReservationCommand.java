package com.example.hospitalreservation.reservation.application.command;

import com.example.hospitalreservation.reservation.domain.entity.Reason;
import com.example.hospitalreservation.reservation.domain.entity.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public record CreateReservationCommand(
        Long doctorId,
        Long patientId,
        LocalDateTime startTime,
        LocalDateTime endTime,
        List<Reason> reasons
) {

    public Reservation toReservation() {
        Reservation reservation = new Reservation(null, doctorId, patientId, startTime, endTime, reasons);
        reservation.validatePastTime(startTime);
        reservation.validateWithinBusinessHours(startTime);
        reservation.validateHourlySlot(startTime, endTime);
        return reservation;
    }
}
