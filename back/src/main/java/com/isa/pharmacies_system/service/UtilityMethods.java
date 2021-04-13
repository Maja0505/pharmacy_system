package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.DTO.PharmacistAppointmentTimeDTO;
import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
import com.isa.pharmacies_system.domain.schedule.StatusOfWorkingHours;
import com.isa.pharmacies_system.domain.schedule.WorkingHours;
import com.isa.pharmacies_system.domain.user.Pharmacist;

import java.time.LocalDateTime;

public class UtilityMethods {

    public UtilityMethods() {
    }

    //#1[3.16]Korak1/2/3
    //da li je selektovan datum u okviru radnog vremena farmaceuta
    public Boolean isPharmacistWorkInSelectedDate(PharmacistAppointmentTimeDTO timeDTO, Pharmacist pharmacist){
        return pharmacist.getPharmacistSchedule().getWorkingHours().stream().filter(workingHours -> isSelectedDateInWorkingHoursOfPharmacist(timeDTO,workingHours)).count()>0;
    }
    //#1[3.16]Korak1/2/3
    //provera za radno vreme
    public Boolean isSelectedDateInWorkingHoursOfPharmacist(PharmacistAppointmentTimeDTO timeDTO, WorkingHours workingHours){
        return (workingHours.getWorkingStartTime().isBefore(timeDTO.getStartTime()) || workingHours.getWorkingStartTime().isEqual(timeDTO.getStartTime())) && (workingHours.getWorkingEndTime().isAfter(timeDTO.getStartTime().plusMinutes((long)timeDTO.getDuration())) || workingHours.getWorkingEndTime().isEqual(timeDTO.getStartTime().plusMinutes((long)timeDTO.getDuration()))) && workingHours.getStatusOfWorkingHours().equals(StatusOfWorkingHours.Not_Vacation);
    }


    //#1[3.16]Korak1/2/3
    //da li je selektovan datum slobodan kod odredjenog farmaceuta
    public Boolean doesPharmacistHaveOpenSelectedAppoinemnt(PharmacistAppointmentTimeDTO timeDTO, Pharmacist pharmacist){
        return pharmacist.getPharmacistAppointments().stream().filter(pharmacistAppointment -> isSelectedDateReserved(timeDTO,pharmacistAppointment)).count() == 0;
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
