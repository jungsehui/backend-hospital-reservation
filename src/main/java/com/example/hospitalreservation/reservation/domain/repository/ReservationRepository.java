package com.example.hospitalreservation.reservation.domain.repository;

import com.example.hospitalreservation.common.exception.ApplicationException;
import com.example.hospitalreservation.reservation.domain.entity.Reservation;
import com.example.hospitalreservation.reservation.exception.ReservationExceptionCode;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ReservationRepository {

    private final List<Reservation> reservations = new ArrayList<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public synchronized Reservation save(Reservation reservation) {
        Reservation toSave = new Reservation(
                nextId.getAndIncrement(),
                reservation.getDoctorId(),
                reservation.getPatientId(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                reservation.getReason()
        );
        reservations.add(toSave);
        return toSave;
    }

    public Reservation findById(Long id) {
        return reservations.stream()
                .filter(reservation -> reservation.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ApplicationException(ReservationExceptionCode.RESERVATION_NOT_FOUND));
    }

    public List<Reservation> findAll() {
        return reservations.stream()
                .map(Reservation::copy)
                .toList();
    }

    public void deleteById(Long id) {
        reservations.removeIf(
                reservation -> reservation.getId().equals(id)
        );
    }

    public void clear() {
        reservations.clear();
    }
}
