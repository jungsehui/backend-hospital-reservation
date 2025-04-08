package com.example.hospitalreservation.reservation.presentation.dto.response;

import com.example.hospitalreservation.common.message.Success;
import com.example.hospitalreservation.reservation.domain.entity.Reservation;

public record CreateReservationResponse(
        Long reservationId,
        String message,
        int calculatedFee
) {

    public static CreateReservationResponse of(Reservation reservation, int calculatedFee) {
        return new CreateReservationResponse(
                reservation.getId(),
                Success.CREATE_RESERVATION.getMessage(),
                calculatedFee
        );
    }
}
