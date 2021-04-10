package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.DTO.StaffPasswordDTO;
import com.isa.pharmacies_system.domain.user.Dermatologist;

public interface IDermatologistService {

    Dermatologist getDermatologist(Long id);
    void saveDermatologist(Dermatologist dermatologist);
    Boolean changePassword(StaffPasswordDTO dermatologistPasswordDTO);
    Boolean checkPassword(String firstPassword,String secondPassword);
}
