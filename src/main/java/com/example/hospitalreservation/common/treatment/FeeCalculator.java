package com.example.hospitalreservation.common.treatment;

@FunctionalInterface
public interface FeeCalculator {

    int calculate(TreatmentPurpose... purposes);
}
