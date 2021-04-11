package com.isa.pharmacies_system.repository;

import com.isa.pharmacies_system.domain.schedule.DermatologistVacationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDermatologistVacationRequestRepository extends JpaRepository<DermatologistVacationRequest,Long> {

    List<DermatologistVacationRequest> findAllByVacationRequestDermatologistId(Long id);
}
