package com.example.hospitalreservation.reservation.presentation.rest;

import com.example.hospitalreservation.reservation.application.ReservationService;
import com.example.hospitalreservation.reservation.application.command.CreateReservationCommand;
import com.example.hospitalreservation.reservation.application.command.DeleteReservationCommand;
import com.example.hospitalreservation.reservation.domain.Reservation;
import com.example.hospitalreservation.reservation.domain.service.DefaultFeeCalculator;
import com.example.hospitalreservation.reservation.presentation.request.CreateReservationRequest;
import com.example.hospitalreservation.reservation.presentation.request.DeleteReservationRequest;
import com.example.hospitalreservation.reservation.presentation.response.CreateReservationResponse;
import com.example.hospitalreservation.reservation.presentation.response.GetReservationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
@RestController
public class ReservationRestController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<CreateReservationResponse> createReservation(@RequestBody CreateReservationRequest request) {
        CreateReservationCommand command = request.toCommand();
        Long id = reservationService.createReservation(command);
        int fee = DefaultFeeCalculator.calculate(command.toPurpose());
        return ResponseEntity.ok(CreateReservationResponse.of(id, fee));
    }

    @GetMapping
    public ResponseEntity<List<GetReservationResponse>> getAllReservations() {
        List<GetReservationResponse> response = reservationService.getAllReservations().stream()
                .map(GetReservationResponse::from)
                .toList();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id, @RequestBody DeleteReservationRequest request) {
        DeleteReservationCommand command = DeleteReservationRequest.toCommand(id, request);
        reservationService.cancelReservation(command);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
