package com.isa.pharmacies_system.repository;

import com.isa.pharmacies_system.domain.user.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IPharmacistRepository extends JpaRepository<Pharmacist,Long> {

}
