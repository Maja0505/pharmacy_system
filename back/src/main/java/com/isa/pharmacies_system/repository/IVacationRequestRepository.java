package com.isa.pharmacies_system.repository;

import com.isa.pharmacies_system.domain.schedule.VacationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVacationRequestRepository extends JpaRepository<VacationRequest,Long> {
}
