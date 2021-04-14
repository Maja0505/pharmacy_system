package com.isa.pharmacies_system.service;

import java.time.LocalDateTime;

public class UtilityMethods {

    public UtilityMethods() {
    }

    //Nemanja
    public Boolean isTimeBetweenTwoOtherTime(LocalDateTime time,LocalDateTime startTime,LocalDateTime endTime){
        return time.isAfter(startTime) && time.isBefore(endTime);
    }

    //Nemanja
    public Boolean isTimeIntervalOutsideSecondTimeInterval(LocalDateTime startFirstInterval,LocalDateTime endFirstInterval,LocalDateTime startSecondInterval,LocalDateTime endSecondInterval){
        return startFirstInterval.isBefore(startSecondInterval) && endFirstInterval.isAfter(endSecondInterval);
    }

    //Nemanja
    public Boolean isTimeIntervalEqualsWithSecondTimeInterval(LocalDateTime startFirstInterval,LocalDateTime endFirstInterval,LocalDateTime startSecondInterval,LocalDateTime endSecondInterval){
        return startFirstInterval.isEqual(startSecondInterval) || endFirstInterval.isEqual(endSecondInterval);
    }

    //Nemanja
    public Boolean isTimeBeforeOtherTime(LocalDateTime startTime,LocalDateTime endTime){
        return startTime.isBefore(endTime);
    }

}
