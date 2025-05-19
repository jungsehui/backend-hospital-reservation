package com.example.hospitalreservation.reservation.presentation.request;

import com.example.hospitalreservation.reservation.application.command.DeleteReservationCommand;

public record DeleteReservationRequest(
        Long id,
        String cancelReason
) {

    public static DeleteReservationCommand toCommand(Long id, DeleteReservationRequest deleteReservationRequest) {
        return new DeleteReservationCommand(id, deleteReservationRequest.cancelReason());
    }
}
