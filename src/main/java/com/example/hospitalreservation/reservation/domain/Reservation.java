package com.example.hospitalreservation.reservation.domain;

import com.example.hospitalreservation.common.exception.ApplicationException;
import com.example.hospitalreservation.doctor.domain.Doctor;
import com.example.hospitalreservation.patient.domain.Patient;
import com.example.hospitalreservation.reservation.exception.ReservationExceptionCode;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Table(name = "reservation")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String reason;

    public Reservation(
            Doctor doctor,
            Patient patient,
            LocalDateTime startTime,
            LocalDateTime endTime,
            String reason
    ) {
        validatePastTime(startTime);
        validateWithinBusinessHours(startTime);
        validateHourlySlot(startTime, endTime);
        this.doctor = doctor;
        this.patient = patient;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reason = reason;
    }

    public boolean isOverlapping(LocalDateTime existingTime, LocalDateTime newTime) {
        LocalDateTime startTime = existingTime;
        LocalDateTime endTime = startTime.plusHours(1);
        return !newTime.isBefore(startTime) && newTime.isBefore(endTime);
    }

    private void validatePastTime(LocalDateTime startTime) {
        if (startTime.isBefore(LocalDateTime.now())) {
            throw new ApplicationException(ReservationExceptionCode.INVALID_RESERVATION_TIME_PAST);
        }
    }

    private void validateWithinBusinessHours(LocalDateTime startTime) {
        LocalTime localTime = startTime.toLocalTime();
        if (localTime.isBefore(LocalTime.of(9, 0)) || localTime.isAfter(LocalTime.of(16, 0))) {
            throw new ApplicationException(ReservationExceptionCode.OUT_OF_BUSINESS_HOURS);
        }
    }

    private void validateHourlySlot(LocalDateTime startTime, LocalDateTime endTime) {
        if (!(startTime.plusHours(1).equals(endTime))) {
            throw new ApplicationException(ReservationExceptionCode.INVALID_RESERVATION_TIME_RANGE);
        }
    }
}
