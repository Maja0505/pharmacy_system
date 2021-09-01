package com.isa.pharmacies_system.repository;

import com.isa.pharmacies_system.domain.medicine.Medicine;
import com.isa.pharmacies_system.domain.medicine.MedicineReservation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IMedicineRepository extends JpaRepository<Medicine,Long> {
	
	@Query("select m from Medicine as m where m.medicine_name=?1")
    Medicine findMedicineByName(String medicineName);
	
}
