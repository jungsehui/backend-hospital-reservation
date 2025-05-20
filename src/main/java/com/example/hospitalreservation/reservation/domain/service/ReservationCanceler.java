package com.example.hospitalreservation.reservation.domain.service;

import com.example.hospitalreservation.common.exception.ApplicationException;
import com.example.hospitalreservation.reservation.application.command.DeleteReservationCommand;
import com.example.hospitalreservation.reservation.domain.Reservation;
import com.example.hospitalreservation.reservation.domain.ReservationRepository;
import com.example.hospitalreservation.reservation.exception.ReservationExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ReservationCanceler {

    private final ReservationRepository reservationRepository;

    public void cancel(Reservation reservation) {
        try {
            reservationRepository.delete(reservation);
            log.info("예약 ID {} 취소됨. 사유: {}", reservation.getId(), reservation.getReason());
        } catch (ApplicationException e) {
            log.warn("예약 취소 실패 - ID {}: {}", reservation.getId(), ReservationExceptionCode.RESERVATION_NOT_FOUND.getMessage());
        }
    }
}
