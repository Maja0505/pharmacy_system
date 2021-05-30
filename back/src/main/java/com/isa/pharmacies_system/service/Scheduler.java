package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.service.iService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    @Autowired
    private IMedicineReservationService medicineReservationService;

    @Autowired
    private IDermatologistAppointmentService dermatologistAppointmentService;

    @Autowired
    private IPharmacistAppointmentService pharmacistAppointmentService;

    @Autowired
    private IPatientService patientService;

    @Scheduled(cron="0 0 7 * * ?")
    public void increasePenaltyForMissedMedicineReservation() {
        medicineReservationService.increasePenaltyForMissedMedicineReservation();
    }

    @Scheduled(cron="0 0 7 1 * ?")
    public void deleteAllPatientsPenalties() {
        patientService.resetAllPenaltiesForPatient();
    }

    @Scheduled(cron = "0 0 12 ? * SUN")
    public void setMissedAppointmentEveryDayOnRightStatusAndIncreasePenaltyForPatient(){
        pharmacistAppointmentService.setMissedPharmacistAppointmentEveryDayOnRightStatusAndIncreasePenaltyForPatient();
        dermatologistAppointmentService.setMissedDermatologistAppointmentEveryDayOnRightStatusAndIncreasePenaltyForPatient();
    }

}
