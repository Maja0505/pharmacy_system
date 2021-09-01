package com.isa.pharmacies_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.pharmacies_system.domain.pharmacy.Offer;

public interface IOfferRepository extends JpaRepository<Offer, Long> {

}
