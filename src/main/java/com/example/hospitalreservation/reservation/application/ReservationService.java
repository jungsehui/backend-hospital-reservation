package com.example.hospitalreservation.reservation.application;

import com.example.hospitalreservation.reservation.domain.service.DefaultFeeCalculator;
import com.example.hospitalreservation.reservation.application.command.CreateReservationCommand;
import com.example.hospitalreservation.reservation.application.command.DeleteReservationCommand;
import com.example.hospitalreservation.reservation.domain.entity.Reservation;
import com.example.hospitalreservation.reservation.domain.repository.ReservationRepository;
import com.example.hospitalreservation.reservation.domain.service.ReservationCanceler;
import com.example.hospitalreservation.reservation.domain.service.ReservationRegister;
import com.example.hospitalreservation.reservation.presentation.dto.response.CreateReservationResponse;
import com.example.hospitalreservation.reservation.presentation.dto.response.GetReservationResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationRegister reservationRegister;
    private final ReservationCanceler reservationCanceler;

    public ReservationService(
            ReservationRepository reservationRepository,
            ReservationRegister reservationRegister,
            ReservationCanceler reservationCanceler
    ) {
        this.reservationRepository = reservationRepository;
        this.reservationRegister = reservationRegister;
        this.reservationCanceler = reservationCanceler;
    }

    public CreateReservationResponse createReservation(CreateReservationCommand createReservationCommand) {
        Reservation reservation = createReservationCommand.toReservation();
        Reservation registeredReservation = reservationRegister.register(reservation);
        int fee = DefaultFeeCalculator.calculate(createReservationCommand.toPurposes());
        return CreateReservationResponse.of(registeredReservation, fee);
    }

    public List<GetReservationResponse> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(GetReservationResponse::from)
                .toList();
    }

    public void cancelReservation(DeleteReservationCommand deleteReservationCommand) {
        reservationCanceler.cancel(deleteReservationCommand);
    }
}
