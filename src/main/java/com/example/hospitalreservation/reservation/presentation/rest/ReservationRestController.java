package com.example.hospitalreservation.reservation.presentation.rest;

import com.example.hospitalreservation.reservation.application.ReservationService;
import com.example.hospitalreservation.reservation.application.command.CreateReservationCommand;
import com.example.hospitalreservation.reservation.application.command.DeleteReservationCommand;
import com.example.hospitalreservation.reservation.exception.ReservationExceptionCode;
import com.example.hospitalreservation.reservation.presentation.dto.request.CreateReservationRequest;
import com.example.hospitalreservation.reservation.presentation.dto.request.DeleteReservationRequest;
import com.example.hospitalreservation.reservation.presentation.dto.response.CreateReservationResponse;
import com.example.hospitalreservation.reservation.presentation.dto.response.GetReservationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/reservations")
@RestController
public class ReservationRestController {

    private final ReservationService reservationService;

    public ReservationRestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<CreateReservationResponse> createReservation(@RequestBody CreateReservationRequest createReservationRequest) {
        CreateReservationCommand createReservationCommand = createReservationRequest.toCommand();
        CreateReservationResponse createReservationResponse = reservationService.createReservation(createReservationCommand);
        return ResponseEntity.ok(createReservationResponse);
    }

    @GetMapping
    public ResponseEntity<List<GetReservationResponse>> getAllReservations() {
        List<GetReservationResponse> getAllReservationsResponse = reservationService.getAllReservations();
        return ResponseEntity.ok(getAllReservationsResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id, @RequestBody DeleteReservationRequest deleteReservationRequest) {
        DeleteReservationCommand deleteReservationCommand = DeleteReservationRequest.toCommand(id, deleteReservationRequest);
        reservationService.cancelReservation(deleteReservationCommand);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
