package com.isa.pharmacies_system.service.iService;

import java.util.List;

import com.isa.pharmacies_system.DTO.DermatologistNewDTO;
import com.isa.pharmacies_system.DTO.UserPasswordDTO;
import com.isa.pharmacies_system.domain.user.Dermatologist;

public interface IDermatologistService {

    Dermatologist getDermatologist(Long id);
    Dermatologist getById(Long id) throws Exception;
    List<Dermatologist> getAll();
    void saveDermatologist(Dermatologist dermatologist);
    Dermatologist create(DermatologistNewDTO dermatologistNewDTO);
    Boolean changePassword(UserPasswordDTO dermatologistPasswordDTO);
    Boolean checkPassword(String firstPassword,String secondPassword);
}
