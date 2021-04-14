package com.isa.pharmacies_system.repository;

import com.isa.pharmacies_system.domain.schedule.WorkingHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IWorkingHoursRepository extends JpaRepository<WorkingHours,Long> {

    //Nemanja
    @Query("select wh from WorkingHours wh where wh.workerSchedule.dermatologist.id = ?1 and wh.workerSchedule.pharmacy.id = ?2 and wh.statusOfWorkingHours = 1 and wh.workingStartTime > CURRENT_TIMESTAMP")
    List<WorkingHours> findAllFutureWorkingHoursForDermatologistInPharmacy(Long dermatologistId,Long pharmacyId);

}
