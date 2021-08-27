package com.isa.pharmacies_system.repository;

import com.isa.pharmacies_system.DTO.PriceListForAppointmentDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.pharmacies_system.domain.pharmacy.PriceList;
import org.springframework.data.jpa.repository.Query;

public interface IPriceListRepository extends JpaRepository<PriceList, Long> {

    //#1
    @Query("select new com.isa.pharmacies_system.DTO.PriceListForAppointmentDTO(p.dermatologistAppointmentPricePerHour,p.pharmacistAppointmentPricePerHour) from PriceList p where p.pharmacy.id = ?1")
    PriceListForAppointmentDTO getPriceListForAppointmentByPharmacyId(Long pharmacyId);
}
