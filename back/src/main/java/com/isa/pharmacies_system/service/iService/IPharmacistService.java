package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.DTO.UserPasswordDTO;
import com.isa.pharmacies_system.domain.user.Pharmacist;

public interface IPharmacistService {

    Pharmacist getPharmacist(Long id);
    void savePharmacist(Pharmacist pharmacist);
    Boolean changePassword(UserPasswordDTO pharmacistPasswordDTO);
    Boolean checkPassword(String firstPassword,String secondPassword);

}
