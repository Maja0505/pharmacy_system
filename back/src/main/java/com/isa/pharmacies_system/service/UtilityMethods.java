package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.DTO.PharmacistAppointmentTimeDTO;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
import com.isa.pharmacies_system.domain.schedule.StatusOfAppointment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

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
    //da li je rezervisan
    public Boolean isSelectedDateReserved(PharmacistAppointmentTimeDTO timeDTO, PharmacistAppointment pharmacistAppointment){
        LocalDateTime appointmentStartTime = pharmacistAppointment.getPharmacistAppointmentStartTime();
        LocalDateTime appintmentEndTime = pharmacistAppointment.getPharmacistAppointmentStartTime().plusMinutes((long)pharmacistAppointment.getPharmacistAppointmentDuration());
        return  (isSelectedStartBetweenTwoDates(timeDTO.getStartTime(),appointmentStartTime,appintmentEndTime)
                || isSelectedEndBetweenTwoDates(timeDTO.getStartTime().plusMinutes((long)timeDTO.getDuration()),appointmentStartTime,appintmentEndTime)
                || isSelectedStartBetweenTwoDates(appointmentStartTime,timeDTO.getStartTime(),timeDTO.getStartTime().plusMinutes((long)timeDTO.getDuration()))
                || isSelectedEndBetweenTwoDates(appintmentEndTime,timeDTO.getStartTime(),timeDTO.getStartTime().plusMinutes((long)timeDTO.getDuration())))
                && pharmacistAppointment.getStatusOfAppointment().equals(StatusOfAppointment.Reserved);
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

    public Boolean isCancellationPossible(LocalDateTime start){

        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(now,start);
        return duration.toHours() >= 24;
    }

    public Boolean checkDoesHaveAnyOtherDermatologistAppointmentWithSameTime(List<DermatologistAppointment> list, LocalDateTime startTime, LocalDateTime endTime) {
        for (DermatologistAppointment a: list
        ) {
            if( isTimeBetweenTwoOtherTime(startTime,a.getDermatologistAppointmentStartTime(),a.getDermatologistAppointmentEndTime())
                    || isTimeBetweenTwoOtherTime(endTime,a.getDermatologistAppointmentStartTime(),a.getDermatologistAppointmentEndTime())
                    || isTimeIntervalOutsideSecondTimeInterval(startTime,endTime,a.getDermatologistAppointmentStartTime(),a.getDermatologistAppointmentEndTime())
                    || isTimeIntervalEqualsWithSecondTimeInterval(startTime,endTime,a.getDermatologistAppointmentStartTime(),a.getDermatologistAppointmentEndTime())){

                return true;
            }
        }
        return false;

    }

    public Boolean checkDoesPatientHavePharmacistAppointmentWithSameTime(List<PharmacistAppointment> list, LocalDateTime firstStartTime, LocalDateTime firstEndTime) {
        for (PharmacistAppointment a: list
        ) {
            LocalDateTime secondStartTime = a.getPharmacistAppointmentStartTime();
            LocalDateTime secondEndTime = secondStartTime.plusMinutes(a.getPharmacistAppointmentDuration());
            if(isTimeBetweenTwoOtherTime(firstStartTime,secondStartTime,secondEndTime)
                    || isTimeBetweenTwoOtherTime(firstEndTime,secondStartTime,secondEndTime)
                    || isTimeIntervalOutsideSecondTimeInterval(firstStartTime,firstEndTime,secondStartTime,secondEndTime)
                    || isTimeIntervalEqualsWithSecondTimeInterval(firstStartTime,firstEndTime,secondStartTime,secondEndTime)){

                return true;
            }
        }

        return false;
    }
}
