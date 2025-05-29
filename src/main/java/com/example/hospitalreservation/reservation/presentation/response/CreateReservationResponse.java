package com.example.hospitalreservation.reservation.presentation.response;

public record CreateReservationResponse(
        Long reservationId,
        String message,
        int calculatedFee
) {

    public static CreateReservationResponse of(Long reservationId, int calculatedFee) {
        return new CreateReservationResponse(
                reservationId,
                "예약이 완료되었습니다.",
                calculatedFee
        );
    }
}
