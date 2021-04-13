package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.DTO.PatientAppointmentInfoDTO;
import com.isa.pharmacies_system.DTO.PharmacistAppointmentTimeDTO;
import com.isa.pharmacies_system.DTO.PriceListForAppointmentDTO;
import com.isa.pharmacies_system.domain.schedule.*;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.domain.user.Pharmacist;
import com.isa.pharmacies_system.repository.IPatientRepository;
import com.isa.pharmacies_system.repository.IPharmacistAppointmentRepository;
import com.isa.pharmacies_system.repository.IPharmacistRepository;
import com.isa.pharmacies_system.repository.IPriceListRepository;
import com.isa.pharmacies_system.service.iService.IPharmacistAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class PharmacistAppointmentService implements IPharmacistAppointmentService {

    private IPharmacistAppointmentRepository pharmacistAppointmentRepository;
    private IPharmacistRepository pharmacistRepository;
    private IPatientRepository patientRepository;
    private IPriceListRepository priceListRepository;
    private UtilityMethods utilityMethods;

    @Autowired
    public PharmacistAppointmentService(IPharmacistAppointmentRepository pharmacistAppointmentRepository, IPharmacistRepository pharmacistRepository, IPatientRepository patientRepository, IPriceListRepository priceListRepository) {
        this.pharmacistAppointmentRepository = pharmacistAppointmentRepository;
        this.pharmacistRepository = pharmacistRepository;
        this.patientRepository = patientRepository;
        this.priceListRepository = priceListRepository;
        this.utilityMethods = new UtilityMethods();
    }

    @Override
    public Page<PharmacistAppointment> getAllPastPharmacistAppointmentByPharmacist(Long id,int page) {
        return pharmacistAppointmentRepository.findAllPastPharmacistAppointment(id,PageRequest.of(page,10));
    }

    @Override
    public List<PatientAppointmentInfoDTO> sortByAppointmentDuration(List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList, Boolean asc) {
        if(asc){
            Collections.sort(patientAppointmentInfoDTOList, Comparator.comparing(PatientAppointmentInfoDTO::getAppointmentDuration));
        }else{
            Collections.sort(patientAppointmentInfoDTOList, Comparator.comparing(PatientAppointmentInfoDTO::getAppointmentDuration).reversed());
        }
        return patientAppointmentInfoDTOList;
    }

    //#1[3.16]Korak3
    @Override
    public Boolean bookPharmacistAppointment(Long patientId, Long pharmacistId, PharmacistAppointmentTimeDTO timeDTO){
        Patient patient = patientRepository.findById(patientId).orElse(null);
        Pharmacist pharmacist = pharmacistRepository.findById(pharmacistId).orElse(null);
        if((utilityMethods.isPharmacistWorkInSelectedDate(timeDTO,pharmacist)) && (utilityMethods.doesPharmacistHaveOpenSelectedAppoinemnt(timeDTO, pharmacist))){
            pharmacistAppointmentRepository.save(createNewPharmacistAppointment(patient, pharmacist, timeDTO));
            return true;
        }else{
            return false;
        }

    }

    private PharmacistAppointment createNewPharmacistAppointment(Patient patient, Pharmacist pharmacist, PharmacistAppointmentTimeDTO timeDTO){

        PharmacistAppointment pharmacistAppointment = new PharmacistAppointment();
        PriceListForAppointmentDTO priceListForAppointmentDTO = priceListRepository.getPriceListForAppointmentByPharmacyId(pharmacist.getPharmacyForPharmacist().getId());
        pharmacistAppointment.setPharmacistForAppointment(pharmacist);
        pharmacistAppointment.setPatientWithPharmacistAppointment(patient);
        pharmacistAppointment.setStatusOfAppointment(StatusOfAppointment.Reserved);
        pharmacistAppointment.setTypeOfAppointment(TypeOfAppointment.Pharmacist_appointment);
        pharmacistAppointment.setAppointmentPoints(0);
        pharmacistAppointment.setAppointmentPrice(priceListForAppointmentDTO.getPharmacistAppointmentPricePerHour());
        pharmacistAppointment.setPharmacistAppointmentDuration(timeDTO.getDuration());
        pharmacistAppointment.setPharmacistAppointmentStartTime(timeDTO.getStartTime());
        return pharmacistAppointment;

    }




}
