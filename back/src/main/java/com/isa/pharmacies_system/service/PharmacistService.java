package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.DTO.PharmacistAppointmentTimeDTO;
import com.isa.pharmacies_system.DTO.UserPasswordDTO;
import com.isa.pharmacies_system.config.WebSecurityConfig;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.domain.schedule.PharmacistVacationRequest;
import com.isa.pharmacies_system.domain.schedule.StatusOfVacationRequest;
import com.isa.pharmacies_system.domain.user.Pharmacist;
import com.isa.pharmacies_system.repository.IPharmacistRepository;
import com.isa.pharmacies_system.repository.IPharmacyRepository;
import com.isa.pharmacies_system.repository.IWorkingHoursRepository;
import com.isa.pharmacies_system.service.iService.IPharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PharmacistService implements IPharmacistService {

    private IPharmacistRepository pharmacistRepository;
    private IPharmacyRepository pharmacyRepository;
    private UtilityMethods utilityMethods;
    private IWorkingHoursRepository workingHoursRepository;

    @Autowired
    public PharmacistService(IPharmacistRepository pharmacistRepository, IPharmacyRepository pharmacyRepository, IWorkingHoursRepository workingHoursRepository) {
        this.pharmacistRepository = pharmacistRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.workingHoursRepository = workingHoursRepository;
        this.utilityMethods = new UtilityMethods();
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
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        Pharmacist pharmacist = getPharmacist(pharmacistPasswordDTO.getId());

        if(b.matches(pharmacistPasswordDTO.getConfirmedPassword(),pharmacist.getPassword()) &&
            checkPassword(pharmacistPasswordDTO.getNewPassword(),pharmacistPasswordDTO.getConfirmedNewPassword())){

            pharmacist.setPassword(b.encode(pharmacistPasswordDTO.getNewPassword()));
            savePharmacist(pharmacist);
            return true;

        }
        return false;
    }

    @Override
    public Boolean checkPassword(String firstPassword,String secondPassword) {
        return firstPassword.equals(secondPassword);
    }


    //#1[3.16]Korak2
    @Override
    public List<Pharmacist> getAllPharmacistsWithOpenAppointmentsByPharmacyId(Long pharmacyId, PharmacistAppointmentTimeDTO timeDTO){
        List<Pharmacist> pharmacists = pharmacyRepository.getAllPharmacistsForPharmacy(pharmacyId);
        return pharmacists.stream().filter(pharmacist -> doesPharmacistWorkInSelectedDate(timeDTO,pharmacist) && doesPharmacistHaveOpenSelectedAppointment(timeDTO, pharmacist)).collect(Collectors.toList());
    }

    @Override
    public List<PharmacistVacationRequest> getAllFuturePharmacistVacationRequest(Long pharmacistId) {
        Pharmacist pharmacist = getPharmacist(pharmacistId);
        return pharmacist.getPharmacistVacationRequests().stream()
                .filter(pvq -> (pvq.getVacationEndDate().isAfter(LocalDate.now()) && !pvq.getStatusOfVacationRequest().equals(StatusOfVacationRequest.Declined)))
                .collect(Collectors.toList());
    }

    //Nemanja
    @Override
    public Long getPharmacyIdWherePharmacistWork(Long pharmacistId) {
        Pharmacist pharmacist = pharmacistRepository.findById(pharmacistId).orElse(null);
        if(pharmacist != null){
            return pharmacist.getPharmacyForPharmacist().getId();
        }
        return null;
    }

    //#1
    private Boolean doesPharmacistWorkInSelectedDate(PharmacistAppointmentTimeDTO timeDTO, Pharmacist pharmacist){
        return (workingHoursRepository.getWorkingHourByDate(pharmacist.getId(),timeDTO.getStartTime(),timeDTO.getStartTime().plusMinutes((long)timeDTO.getDuration()))).stream().count()>0;
    }

    //#1
    private Boolean doesPharmacistHaveOpenSelectedAppointment(PharmacistAppointmentTimeDTO timeDTO, Pharmacist pharmacist){
        return pharmacist.getPharmacistAppointments().stream().filter(pharmacistAppointment -> utilityMethods.isSelectedDateReserved(timeDTO,pharmacistAppointment)).count() == 0;
    }

}
