package com.isa.pharmacies_system.repository;

import com.isa.pharmacies_system.domain.schedule.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointmentRepository extends JpaRepository<Appointment,Long> {
}
