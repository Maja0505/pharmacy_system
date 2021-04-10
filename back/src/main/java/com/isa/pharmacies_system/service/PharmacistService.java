package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.DTO.UserPasswordDTO;
import com.isa.pharmacies_system.domain.user.Pharmacist;
import com.isa.pharmacies_system.repository.IPharmacistRepository;
import com.isa.pharmacies_system.service.iService.IPharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PharmacistService implements IPharmacistService {

    private IPharmacistRepository pharmacistRepository;

    @Autowired
    public PharmacistService(IPharmacistRepository pharmacistRepository) {
        this.pharmacistRepository = pharmacistRepository;
    }

    @Override
    public Pharmacist getPharmacist(Long id){
        return pharmacistRepository.findById(id).orElse(null);
    }

    @Override
    public void savePharmacist(Pharmacist pharmacist) {
        pharmacistRepository.save(pharmacist);
    }

    @Override
    public Boolean changePassword(UserPasswordDTO pharmacistPasswordDTO) {
        Pharmacist pharmacist = getPharmacist(pharmacistPasswordDTO.getId());

        if(checkPassword(pharmacistPasswordDTO.getConfirmedPassword(),pharmacist.getPassword()) &&
            checkPassword(pharmacistPasswordDTO.getNewPassword(),pharmacistPasswordDTO.getConfirmedNewPassword())){

            pharmacist.setPassword(pharmacistPasswordDTO.getNewPassword());
            savePharmacist(pharmacist);
            return true;

        }
        return false;
    }

    @Override
    public Boolean checkPassword(String firstPassword,String secondPassword) {
        return firstPassword.equals(secondPassword);
    }
}