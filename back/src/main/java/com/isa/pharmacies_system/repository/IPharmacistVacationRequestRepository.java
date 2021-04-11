package com.isa.pharmacies_system.repository;

import com.isa.pharmacies_system.domain.schedule.PharmacistVacationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPharmacistVacationRequestRepository extends JpaRepository<PharmacistVacationRequest,Long> {

    List<PharmacistVacationRequest> findAllByVacationRequestPharmacistId(Long id);

}
