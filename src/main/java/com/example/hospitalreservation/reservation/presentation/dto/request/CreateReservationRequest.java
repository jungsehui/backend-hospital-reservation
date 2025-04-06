package com.example.hospitalreservation.reservation.presentation.dto.request;

import com.example.hospitalreservation.common.exception.ApplicationException;
import com.example.hospitalreservation.patient.domain.exception.PatientExceptionCode;
import com.example.hospitalreservation.reservation.application.command.CreateReservationCommand;
import com.example.hospitalreservation.reservation.domain.treatment.TreatmentPurpose;
import com.example.hospitalreservation.reservation.exception.ReservationExceptionCode;

import java.time.LocalDateTime;
import java.util.Objects;

public record CreateReservationRequest(
        Long doctorId,
        Long patientId,
        LocalDateTime reservationStartTime,
        LocalDateTime reservationEndTime,
        TreatmentPurpose reason
) {

    public CreateReservationCommand toCommand() {
        validatePositiveId(patientId);
        validatePastTime(reservationStartTime);
        return new CreateReservationCommand(doctorId, patientId, reservationStartTime, reservationEndTime, reason);
    }

    private void validatePositiveId(Long id) {
        if (!isPositiveId(id)) {
            throw new ApplicationException(PatientExceptionCode.INVALID_POSITIVE_PATIENT_ID);
        }
    }

    private void validatePastTime(LocalDateTime startTime) {
        if (!isTimeBeforeNow(startTime)) {
            throw new ApplicationException(ReservationExceptionCode.INVALID_RESERVATION_TIME_PAST);
        }
    }

    private boolean isPositiveId(Long id) {
        return !(Objects.isNull(id) || id <= 0);
    }

    private boolean isTimeBeforeNow(LocalDateTime time) {
        return !time.isBefore(LocalDateTime.now());
    }
}
