package com.isa.pharmacies_system.service;

import java.time.LocalDateTime;

public class UtilityMethods {

    public UtilityMethods() {
    }

    public Boolean isTimeBetweenTwoOtherTime(LocalDateTime time,LocalDateTime startTime,LocalDateTime endTime){
        return time.isAfter(startTime) && time.isBefore(endTime);
    }

    public Boolean isTimeIntervalOutsideSecondTimeInterval(LocalDateTime startFirstInterval,LocalDateTime endFirstInterval,LocalDateTime startSecondInterval,LocalDateTime endSecondInterval){
        return startFirstInterval.isBefore(startSecondInterval) && endFirstInterval.isAfter(endSecondInterval);
    }

    public Boolean isTimeIntervalEqualsWithSecondTimeInterval(LocalDateTime startFirstInterval,LocalDateTime endFirstInterval,LocalDateTime startSecondInterval,LocalDateTime endSecondInterval){
        return startFirstInterval.isEqual(startSecondInterval) || endFirstInterval.isEqual(endSecondInterval);
    }

}
