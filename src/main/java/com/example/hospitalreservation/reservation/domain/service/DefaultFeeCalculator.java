package com.example.hospitalreservation.reservation.domain.service;

import com.example.hospitalreservation.reservation.domain.entity.TreatmentPurpose;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultFeeCalculator {

    public static int calculate(List<TreatmentPurpose> purposes) {
        return purposes.stream()
                .mapToInt(TreatmentPurpose::getFee)
                .sum();
    }
}
