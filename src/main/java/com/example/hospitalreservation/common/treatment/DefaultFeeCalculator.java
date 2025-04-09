package com.example.hospitalreservation.common.treatment;

public class DefaultFeeCalculator {

    public static final FeeCalculator SUM = (TreatmentPurpose... purposes) -> {
        int total = 0;
        for (TreatmentPurpose purpose : purposes) {
            total += purpose.getFee();
        }
        return total;
    };
}
