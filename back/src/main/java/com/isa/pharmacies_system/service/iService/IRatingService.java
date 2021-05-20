package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.DTO.RatingMedicineDTO;
import com.isa.pharmacies_system.DTO.RatingPharmacyDTO;
import com.isa.pharmacies_system.DTO.RatingStaffDTO;

public interface IRatingService {
    Boolean setStaffRating(RatingStaffDTO ratingStaffDTO);
    Boolean setMedicineRating(RatingMedicineDTO ratingMedicineDTO);
    Boolean setPharmacyRating(RatingPharmacyDTO ratingPharmacyDTO);
}
