package com.example.hospitalreservation.reservation.presentation.rest;

import com.example.hospitalreservation.common.exception.ApplicationException;
import com.example.hospitalreservation.reservation.application.ReservationService;
import com.example.hospitalreservation.reservation.application.command.CreateReservationCommand;
import com.example.hospitalreservation.reservation.application.command.DeleteReservationCommand;
import com.example.hospitalreservation.reservation.domain.repository.ReservationRepository;
import com.example.hospitalreservation.reservation.exception.ReservationExceptionCode;
import com.example.hospitalreservation.reservation.presentation.dto.request.CreateReservationRequest;
import com.example.hospitalreservation.reservation.presentation.dto.request.DeleteReservationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/reservations")
@RestController
public class ReservationRestController {

    private final ReservationService reservationService;

    public ReservationRestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public String createReservation(CreateReservationRequest createReservationRequest, Model model) {
        try {
            CreateReservationCommand createReservationCommand = createReservationRequest.toCommand();
            reservationService.createReservation(createReservationCommand);
            return "redirect:/reservations";
        } catch (ApplicationException e) {
            model.addAttribute("errorMessage", e.getCode().getMessage());
            return "reservation_form";
        }
    }

    @PostMapping("/delete/{id}")
    public String cancelReservation(@PathVariable Long id, @RequestParam String cancelReason, Model model) {
        DeleteReservationCommand deleteReservationCommand = DeleteReservationRequest.toCommand(id, cancelReason);
        if (!reservationService.cancelReservation(deleteReservationCommand)) {
            model.addAttribute("errorMessage", ReservationExceptionCode.RESERVATION_NOT_FOUND);
        }
        model.addAttribute("reservations", reservationService.getAllReservations());
        return "redirect:/reservations";
    }
}
