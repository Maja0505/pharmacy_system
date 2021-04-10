package com.isa.pharmacies_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.pharmacies_system.domain.pharmacy.PriceList;

public interface IPriceListRepository extends JpaRepository<PriceList, Long> {

}
