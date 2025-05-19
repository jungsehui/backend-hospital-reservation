package com.example.hospitalreservation.reservation.presentation.response;

import com.example.hospitalreservation.reservation.domain.Reservation;

public record CreateReservationResponse(
        Long reservationId,
        String message,
        int calculatedFee
) {

    public static CreateReservationResponse of(Reservation reservation, int calculatedFee) {
        return new CreateReservationResponse(
                reservation.getId(),
                "예약이 완료되었습니다.",
                calculatedFee
        );
    }
}
