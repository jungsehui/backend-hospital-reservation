package com.example.hospitalreservation.reservation.domain.service;

import com.example.hospitalreservation.reservation.domain.TreatmentPurpose;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultFeeCalculator {

    public static int calculate(TreatmentPurpose purpose) {
        return purpose.getFee();
    }
}
