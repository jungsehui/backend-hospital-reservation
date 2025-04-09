package com.example.hospitalreservation.reservation.domain.service;

import com.example.hospitalreservation.reservation.domain.entity.TreatmentPurpose;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultFeeCalculator {

    public static int calculate(List<TreatmentPurpose> purposes) {
        int total = 0;
        for (TreatmentPurpose purpose : purposes) {
            total += purpose.getFee();
        }
        return total;
    }
}
