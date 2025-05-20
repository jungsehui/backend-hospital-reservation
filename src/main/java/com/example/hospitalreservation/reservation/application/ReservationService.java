package com.example.hospitalreservation.reservation.application;

import com.example.hospitalreservation.common.exception.ApplicationException;
import com.example.hospitalreservation.doctor.domain.Doctor;
import com.example.hospitalreservation.doctor.domain.DoctorRepository;
import com.example.hospitalreservation.patient.domain.Patient;
import com.example.hospitalreservation.patient.domain.PatientRepository;
import com.example.hospitalreservation.reservation.application.command.CreateReservationCommand;
import com.example.hospitalreservation.reservation.application.command.DeleteReservationCommand;
import com.example.hospitalreservation.reservation.domain.Reservation;
import com.example.hospitalreservation.reservation.domain.ReservationRepository;
import com.example.hospitalreservation.reservation.domain.service.DefaultFeeCalculator;
import com.example.hospitalreservation.reservation.domain.service.ReservationRegister;
import com.example.hospitalreservation.reservation.exception.ReservationExceptionCode;
import com.example.hospitalreservation.reservation.presentation.response.CreateReservationResponse;
import com.example.hospitalreservation.reservation.presentation.response.GetReservationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRegister reservationRegister;
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

    public void cancelReservation(DeleteReservationCommand command) {
        Reservation reservation = reservationRepository.getById(command.id());
        reservationRepository.delete(reservation);
        log.info("예약 ID {} 취소됨. 사유: {}", reservation.getId(), reservation.getReason());
    }
}
