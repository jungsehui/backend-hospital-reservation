package com.example.hospitalreservation.reservation.domain;

import com.example.hospitalreservation.common.exception.ApplicationException;
import com.example.hospitalreservation.reservation.exception.ReservationExceptionCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    default Reservation getById(Long id) {
        return findById(id).orElseThrow(() -> new ApplicationException(ReservationExceptionCode.RESERVATION_NOT_FOUND));
    }
}
