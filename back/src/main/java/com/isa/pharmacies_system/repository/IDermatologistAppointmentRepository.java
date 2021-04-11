package com.isa.pharmacies_system.repository;

import com.isa.pharmacies_system.DTO.DermatologistAppointmentDTO;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDermatologistAppointmentRepository extends JpaRepository<DermatologistAppointment, Long> {

    @Query(value = "select d  from DermatologistAppointment as d where d.statusOfAppointment = 0 and d.dermatologistAppointmentStartTime > CURRENT_DATE")
    List<DermatologistAppointment> getOpenDermatologistAppointment();

}
