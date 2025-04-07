package com.example.hospitalreservation.reservation.domain.entity;

import com.example.hospitalreservation.common.exception.ApplicationException;
import com.example.hospitalreservation.reservation.domain.treatment.TreatmentPurpose;
import com.example.hospitalreservation.reservation.exception.ReservationExceptionCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
public class Reservation {

    private Long id;
    private Long doctorId;
    private Long patientId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private TreatmentPurpose treatmentPurpose;

    public Reservation(
            Long id,
            Long doctorId,
            Long patientId,
            LocalDateTime startTime,
            LocalDateTime endTime,
            TreatmentPurpose treatmentPurpose
    ) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.treatmentPurpose = treatmentPurpose;
    }

    public void validateWithinBusinessHours(LocalDateTime time) {
        LocalTime localTime = time.toLocalTime();
        if (localTime.isBefore(LocalTime.of(9, 0)) || localTime.isAfter(LocalTime.of(16, 00))) {
            throw new ApplicationException(ReservationExceptionCode.OUT_OF_BUSINESS_HOURS);
        }
    }

    public void validateHourlySlot(LocalDateTime startTime, LocalDateTime endTime) {
        if (!(startTime.plusHours(1).equals(endTime))) {
            throw new ApplicationException(ReservationExceptionCode.INVALID_RESERVATION_TIME_RANGE);
        }
    }

    public boolean isOverlapping(LocalDateTime existingTime, LocalDateTime newTime) {
        LocalDateTime startTime = existingTime;
        LocalDateTime endTime = startTime.plusHours(1);
        return !newTime.isBefore(startTime) && newTime.isBefore(endTime);
    }

    public Reservation copy() {
        return new Reservation(this.id, this.doctorId, this.patientId, this.startTime, this.endTime, this.treatmentPurpose);
    }
}
