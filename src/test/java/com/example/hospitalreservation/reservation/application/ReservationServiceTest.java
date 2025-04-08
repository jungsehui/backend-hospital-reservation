package com.example.hospitalreservation.reservation.application;

import com.example.hospitalreservation.reservation.domain.entity.Reservation;
import com.example.hospitalreservation.reservation.domain.repository.ReservationRepository;
import com.example.hospitalreservation.common.treatment.TreatmentPurpose;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ReservationServiceTest {

    private ReservationRepository reservationRepository;

    @BeforeEach
    public void beforeEach() {
        reservationRepository = new ReservationRepository();
    }

    @AfterEach
    public void afterEach() {
        reservationRepository.clear();
    }

    @Test
    public void 깊은_복사_성공_테스트() throws Exception {
        // given
        LocalDateTime now = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);
        Reservation reservation = new Reservation(null, 1L, 1L, now, now.plusHours(1), TreatmentPurpose.GENERAL_CHECKUP);
        Reservation savedReservation = reservationRepository.save(reservation);

        // when
        // findAll() 호출하여 내부 객체까지 복사된 리스트 획득
        List<Reservation> reservationsCopy = reservationRepository.findAll();
        // 저장된 예약 객체를 리스트에서 꺼냄
        Reservation reservationFromList = reservationsCopy.getFirst();

        // Reservation 클래스는 setter가 없으므로 리플렉션을 사용하여 내부 상태 변경
        Field field = Reservation.class.getDeclaredField("startTime");
        field.setAccessible(true);
        LocalDateTime newTime = now.plusHours(1);
        field.set(reservationFromList, newTime);

        // then
        Reservation updatedReservation = reservationRepository.findById(savedReservation.getId());
        assertNotEquals(newTime, updatedReservation.getStartTime());
    }
}
