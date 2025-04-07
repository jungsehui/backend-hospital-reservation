package com.example.hospitalreservation.reservation.presentation.view;

import com.example.hospitalreservation.reservation.application.ReservationService;
import com.example.hospitalreservation.reservation.domain.entity.Reservation;
import com.example.hospitalreservation.reservation.presentation.dto.response.GetReservationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@RequestMapping("/reservations")
@Controller
public class ReservationViewController {

    private final ReservationService reservationService;

    public ReservationViewController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public String getReservations(Model model) {
        List<GetReservationResponse> reservations = reservationService.getAllReservations();
        model.addAttribute("reservations", reservations);
        return "index";
    }

    @GetMapping("/new")
    public String showReservationForm() {
        return "reservation_form";
    }
}
