package com.example.hospitalreservation.reservation.application;

import com.example.hospitalreservation.reservation.domain.entity.Reservation;
import com.example.hospitalreservation.reservation.domain.service.ReservationCanceler;
import com.example.hospitalreservation.reservation.domain.service.ReservationRegister;
import com.example.hospitalreservation.reservation.domain.repository.ReservationRepository;
import com.example.hospitalreservation.reservation.application.command.CreateReservationCommand;
import com.example.hospitalreservation.reservation.application.command.DeleteReservationCommand;
import com.example.hospitalreservation.reservation.presentation.dto.response.CreateReservationResponse;
import com.example.hospitalreservation.reservation.presentation.dto.response.GetReservationResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationCanceler reservationCanceler;
    private final ReservationRegister reservationRegister;
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationCanceler reservationCanceler, ReservationRegister reservationRegister, ReservationRepository reservationRepository) {
        this.reservationCanceler = reservationCanceler;
        this.reservationRegister = reservationRegister;
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    public List<GetReservationResponse> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(GetReservationResponse::of)
                .toList();
    }

    public CreateReservationResponse createReservation(CreateReservationCommand createReservationCommand) {
        Reservation reservation = createReservationCommand.toReservation();
        Reservation registeredReservation = reservationRegister.register(reservation);
        return CreateReservationResponse.of(registeredReservation);
    }

    public void cancelReservation(DeleteReservationCommand deleteReservationCommand) {
        reservationCanceler.cancel(deleteReservationCommand);
    }
}
