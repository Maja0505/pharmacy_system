package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.service.iService.IAppointmentService;
import com.isa.pharmacies_system.service.iService.IMedicineReservationService;
import com.isa.pharmacies_system.service.iService.IPatientService;
import com.isa.pharmacies_system.service.iService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    @Autowired
    private IMedicineReservationService medicineReservationService;

    @Autowired
    private IAppointmentService appointmentService;

    @Autowired
    private IPatientService patientService;

    @Scheduled(cron="0 0 7 1/1 * ? *")
    public void increasePenaltyForMissedMedicineReservation() {
        medicineReservationService.increasePenaltyForMissedMedicineReservation();
    }

    @Scheduled(cron="0 0 7 1 1/1 ? *")
    public void deleteAllPatientsPenalties() {
        patientService.resetAllPenaltiesForPatient();
    }
}
