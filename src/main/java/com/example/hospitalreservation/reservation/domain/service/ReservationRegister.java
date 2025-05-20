package com.example.hospitalreservation.reservation.domain.service;

import com.example.hospitalreservation.common.exception.ApplicationException;
import com.example.hospitalreservation.reservation.domain.Reservation;
import com.example.hospitalreservation.reservation.domain.ReservationRepository;
import com.example.hospitalreservation.reservation.exception.ReservationExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReservationRegister {

    private final ReservationRepository reservationRepository;

    public Reservation register(Reservation reservation) {
        boolean isOverlapping = reservationRepository.findAll().stream()
                .anyMatch(r -> r.isOverlapping(r.getStartTime(), r.getStartTime()));
        if (isOverlapping) {
            throw new ApplicationException(ReservationExceptionCode.DUPLICATE_RESERVATION_TIME);
        }
        return reservationRepository.save(reservation);
    }
}
