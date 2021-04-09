package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.DTO.StaffPasswordDTO;
import com.isa.pharmacies_system.domain.user.Pharmacist;

public interface IPharmacistService {

    Pharmacist getPharmacist(Long id);
    void savePharmacist(Pharmacist pharmacist);
    Boolean changePassword(StaffPasswordDTO pharmacistPasswordDTO);
    Boolean checkPassword(String firstPassword,String secondPassword);

}
