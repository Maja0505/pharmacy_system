package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.DTO.PharmacistAppointmentTimeDTO;
import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
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


    //#1[3.16]Korak1/2/3
    //provera da li je selektovan datum izmedju pocetka i kraja appointmenta
    // i provera da li ja appointmnet izmedju pocetka i kraja izabranog datuma
    public Boolean isSelectedDateReserved(PharmacistAppointmentTimeDTO timeDTO, PharmacistAppointment pharmacistAppointment){
        LocalDateTime appointmentStartTime = pharmacistAppointment.getPharmacistAppointmentStartTime();
        LocalDateTime appintmentEndTime = pharmacistAppointment.getPharmacistAppointmentStartTime().plusMinutes((long)pharmacistAppointment.getPharmacistAppointmentDuration());
        return  isSelectedStartBetweenTwoDates(timeDTO.getStartTime(),appointmentStartTime,appintmentEndTime)
                || isSelectedEndBetweenTwoDates(timeDTO.getStartTime().plusMinutes((long)timeDTO.getDuration()),appointmentStartTime,appintmentEndTime)
                || isSelectedStartBetweenTwoDates(appointmentStartTime,timeDTO.getStartTime(),timeDTO.getStartTime().plusMinutes((long)timeDTO.getDuration()))
                || isSelectedEndBetweenTwoDates(appintmentEndTime,timeDTO.getStartTime(),timeDTO.getStartTime().plusMinutes((long)timeDTO.getDuration()));
    }

    //#1[3.16]Korak1/2/3
    //kraj jednog moze biti jednak pocetku drugog
    public Boolean isSelectedEndBetweenTwoDates(LocalDateTime localDateTime, LocalDateTime start, LocalDateTime end){
        return 	(localDateTime.isAfter(start)) && (localDateTime.isEqual(end) || localDateTime.isBefore(end));
    }

    //#1[3.16]Korak1/2/3
    //pocetak jednog moze biti jednak kraju drugog
    public Boolean isSelectedStartBetweenTwoDates(LocalDateTime localDateTime, LocalDateTime start, LocalDateTime end){
        return 	(localDateTime.isEqual(start) || localDateTime.isAfter(start)) && (localDateTime.isBefore(end));
    }
}
