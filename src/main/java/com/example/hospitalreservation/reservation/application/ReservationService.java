package com.example.hospitalreservation.reservation.application;

import com.example.hospitalreservation.doctor.domain.Doctor;
import com.example.hospitalreservation.doctor.domain.DoctorRepository;
import com.example.hospitalreservation.patient.domain.Patient;
import com.example.hospitalreservation.patient.domain.PatientRepository;
import com.example.hospitalreservation.reservation.application.command.CreateReservationCommand;
import com.example.hospitalreservation.reservation.application.command.DeleteReservationCommand;
import com.example.hospitalreservation.reservation.domain.Reservation;
import com.example.hospitalreservation.reservation.domain.ReservationRepository;
import com.example.hospitalreservation.reservation.domain.service.DefaultFeeCalculator;
import com.example.hospitalreservation.reservation.domain.service.ReservationCanceler;
import com.example.hospitalreservation.reservation.domain.service.ReservationRegister;
import com.example.hospitalreservation.reservation.presentation.response.CreateReservationResponse;
import com.example.hospitalreservation.reservation.presentation.response.GetReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRegister reservationRegister;
    private final ReservationCanceler reservationCanceler;
    private final ReservationRepository reservationRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public CreateReservationResponse createReservation(CreateReservationCommand command) {
        Doctor doctor = doctorRepository.getById(command.doctorId());
        Patient patient = patientRepository.getById(command.patientId());
        Reservation reservation = new Reservation(doctor, patient, command.startTime(), command.endTime(), command.reason());
        Reservation registeredReservation = reservationRegister.register(reservation);
        int fee = DefaultFeeCalculator.calculate(command.toPurpose());
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
