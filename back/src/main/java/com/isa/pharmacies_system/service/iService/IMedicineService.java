package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.domain.medicine.Medicine;

public interface IMedicineService {
    Medicine findOne(Long id);
}
