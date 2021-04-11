package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.domain.medicine.Medicine;
import com.isa.pharmacies_system.repository.IMedicineRepository;
import com.isa.pharmacies_system.service.iService.IMedicineService;
import org.springframework.stereotype.Service;

@Service
public class MedicineService implements IMedicineService {

    private IMedicineRepository medicineRepository;

    public MedicineService(IMedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    public Medicine findOne(Long id){
      return medicineRepository.findById(id).orElse(null);
    }
}
