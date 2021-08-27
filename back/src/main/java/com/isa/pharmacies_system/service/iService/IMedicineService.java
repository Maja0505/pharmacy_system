package com.isa.pharmacies_system.service.iService;

import java.util.List;

import com.isa.pharmacies_system.DTO.MedicineNewDTO;
import com.isa.pharmacies_system.DTO.MedicineReviewDTO;
import com.isa.pharmacies_system.domain.medicine.Medicine;
import com.isa.pharmacies_system.domain.medicine.MedicineInfo;

public interface IMedicineService {
    Medicine findOne(Long id);
    List<Medicine> getAll();
    Medicine create(MedicineNewDTO medicineNewDTO) throws Exception;
    List<MedicineReviewDTO> getMedicinesAndPharmacyWithMedicines();
    Medicine getMedicineByName(String name);
	MedicineInfo getMedicineInfoByMedicineName(String medicineName);
}
