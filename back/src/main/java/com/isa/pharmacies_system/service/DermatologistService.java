package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.DTO.StaffPasswordDTO;
import com.isa.pharmacies_system.domain.user.Dermatologist;
import com.isa.pharmacies_system.repository.IDermatologistRepository;
import com.isa.pharmacies_system.service.iService.IDermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DermatologistService implements IDermatologistService {

    private IDermatologistRepository dermatologistRepository;

    @Autowired
    public DermatologistService(IDermatologistRepository dermatologistRepository) {
        this.dermatologistRepository = dermatologistRepository;
    }

    @Override
    public Dermatologist getDermatologist(Long id){
        return dermatologistRepository.findById(id).orElse(null);
    }

    @Override
    public void saveDermatologist(Dermatologist dermatologist) {
        dermatologistRepository.save(dermatologist);
    }

    @Override
    public Boolean changePassword(StaffPasswordDTO dermatologistPasswordDTO) {
        Dermatologist dermatologist = getDermatologist(dermatologistPasswordDTO.getId());

        if(checkPassword(dermatologistPasswordDTO.getConfirmedPassword(),dermatologist.getPassword()) &&
                checkPassword(dermatologistPasswordDTO.getNewPassword(),dermatologistPasswordDTO.getConfirmedNewPassword())){

            dermatologist.setPassword(dermatologistPasswordDTO.getNewPassword());
            saveDermatologist(dermatologist);
            return true;

        }
        return false;
    }

    @Override
    public Boolean checkPassword(String firstPassword,String secondPassword) {
        return firstPassword.equals(secondPassword);
    }

}
