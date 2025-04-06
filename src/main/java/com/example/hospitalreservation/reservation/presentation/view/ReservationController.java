package com.example.hospitalreservation.reservation.presentation.view;

import com.example.hospitalreservation.common.exception.ApplicationException;
import com.example.hospitalreservation.reservation.presentation.dto.request.CreateReservationRequest;
import com.example.hospitalreservation.reservation.presentation.dto.request.DeleteReservationRequest;
import com.example.hospitalreservation.reservation.domain.entity.Reservation;
import com.example.hospitalreservation.reservation.exception.ReservationExceptionCode;
import com.example.hospitalreservation.reservation.application.ReservationService;
import com.example.hospitalreservation.reservation.application.command.CreateReservationCommand;
import com.example.hospitalreservation.reservation.application.command.DeleteReservationCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/reservations")
@Controller
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public String getReservations(Model model) {
        List<Reservation> reservations = reservationService.getAllReservations();
        model.addAttribute("reservations", reservations);
        return "index";
    }

    @GetMapping("/new")
    public String showReservationForm() {
        return "reservation_form";
    }
}
