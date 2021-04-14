package com.isa.pharmacies_system.repository;

import com.isa.pharmacies_system.domain.schedule.WorkingHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Set;

public interface IWorkingHoursRepository extends JpaRepository<WorkingHours, Long> {

    @Query(value = "select w from WorkingHours w where w.workerSchedule.pharmacist.id = ?1 and w.statusOfWorkingHours = 1 and w.workingStartTime <= ?2 and w.workingEndTime >= ?3")
    Set<WorkingHours> getWorkingHourByDate(Long pharmacistId, LocalDateTime start, LocalDateTime end);
}
