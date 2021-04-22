package com.isa.pharmacies_system.service.iService;

import java.util.List;

import com.isa.pharmacies_system.DTO.MedicineNewDTO;
import com.isa.pharmacies_system.domain.medicine.Medicine;

public interface IMedicineService {
    Medicine findOne(Long id);
    List<Medicine> getAll();
    Medicine create(MedicineNewDTO medicineNewDTO) throws Exception;
    Medicine getMedicineByName(String name);
}
