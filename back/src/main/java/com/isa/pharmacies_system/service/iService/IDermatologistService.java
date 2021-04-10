package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.DTO.UserPasswordDTO;
import com.isa.pharmacies_system.domain.user.Dermatologist;

public interface IDermatologistService {

    Dermatologist getDermatologist(Long id);
    void saveDermatologist(Dermatologist dermatologist);
    Boolean changePassword(UserPasswordDTO dermatologistPasswordDTO);
    Boolean checkPassword(String firstPassword,String secondPassword);
}
