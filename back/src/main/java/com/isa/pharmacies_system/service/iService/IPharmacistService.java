package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.DTO.PharmacistPasswordDTO;
import com.isa.pharmacies_system.domain.user.Pharmacist;

import java.util.List;

public interface IPharmacistService {

    Pharmacist getPharmacist(Long id);
    void savePharmacist(Pharmacist pharmacist);
    Boolean changePassword(PharmacistPasswordDTO pharmacistPasswordDTO);
    Boolean checkPassword(String firstPassword,String secondPassword);

}
