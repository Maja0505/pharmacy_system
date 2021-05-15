package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.DTO.MedicineReservationInfoDTO;
import com.isa.pharmacies_system.domain.medicine.MedicineReservation;

public interface IMedicineReservationService {

    Boolean createMedicineReservation(MedicineReservation medicineReservation);
    Boolean cancelMedicineReservation(MedicineReservationInfoDTO medicineReservationInfoDTO);
}
