package com.example.hospitalreservation.reservation.domain.entity;

import com.example.hospitalreservation.common.exception.ApplicationException;
import com.example.hospitalreservation.reservation.exception.ReservationExceptionCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
public class Reservation {

    private Long id;
    private Long doctorId;
    private Long patientId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<Reason> reasons;
    private String reason;

    public Reservation(
            Long id,
            Long doctorId,
            Long patientId,
            LocalDateTime startTime,
            LocalDateTime endTime,
            List<Reason> reasons
    ) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reasons = reasons;
    }

    public void validateWithinBusinessHours(LocalDateTime time) {
        LocalTime localTime = time.toLocalTime();
        if (localTime.isBefore(LocalTime.of(9, 0)) || localTime.isAfter(LocalTime.of(16, 0))) {
            throw new ApplicationException(ReservationExceptionCode.OUT_OF_BUSINESS_HOURS);
        }
    }

    public void validateHourlySlot(LocalDateTime startTime, LocalDateTime endTime) {
        if (!(startTime.plusHours(1).equals(endTime))) {
            throw new ApplicationException(ReservationExceptionCode.INVALID_RESERVATION_TIME_RANGE);
        }
    }

    public void validatePastTime(LocalDateTime startTime) {
        if (startTime.isBefore(LocalDateTime.now())) {
            throw new ApplicationException(ReservationExceptionCode.INVALID_RESERVATION_TIME_PAST);
        }
    }

    public boolean isOverlapping(LocalDateTime existingTime, LocalDateTime newTime) {
        LocalDateTime startTime = existingTime;
        LocalDateTime endTime = startTime.plusHours(1);
        return !newTime.isBefore(startTime) && newTime.isBefore(endTime);
    }

    public Reservation copy() {
        return new Reservation(this.id, this.doctorId, this.patientId, this.startTime, this.endTime, this.reasons);
    }
}
