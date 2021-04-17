package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.DTO.PatientAppointmentInfoDTO;
import com.isa.pharmacies_system.DTO.PharmacistAppointmentTimeDTO;
import com.isa.pharmacies_system.DTO.PriceListForAppointmentDTO;
import com.isa.pharmacies_system.domain.schedule.*;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.domain.user.Pharmacist;
import com.isa.pharmacies_system.repository.*;
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
    private IWorkingHoursRepository workingHoursRepository;

    @Autowired
    public PharmacistAppointmentService(IPharmacistAppointmentRepository pharmacistAppointmentRepository, IPharmacistRepository pharmacistRepository, IPatientRepository patientRepository, IPriceListRepository priceListRepository, IWorkingHoursRepository workingHoursRepository) {
        this.pharmacistAppointmentRepository = pharmacistAppointmentRepository;
        this.pharmacistRepository = pharmacistRepository;
        this.patientRepository = patientRepository;
        this.priceListRepository = priceListRepository;
        this.workingHoursRepository = workingHoursRepository;
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
        if(patient != null && pharmacist != null && doesPharmacistWorkInSelectedDate(timeDTO, pharmacist)
                && (doesPharmacistHaveOpenSelectedAppointment(timeDTO, pharmacist))){
            pharmacistAppointmentRepository.save(createNewPharmacistAppointment(patient, pharmacist, timeDTO));
            return true;
        }else{
            return false;
        }
    }

    //#1
    private Boolean doesPharmacistWorkInSelectedDate(PharmacistAppointmentTimeDTO timeDTO, Pharmacist pharmacist){
        return (workingHoursRepository.getWorkingHourByDate(pharmacist.getId(),timeDTO.getStartTime(),timeDTO.getStartTime().plusMinutes((long)timeDTO.getDuration()))).stream().count()>0;
    }

    //#1
    private Boolean doesPharmacistHaveOpenSelectedAppointment(PharmacistAppointmentTimeDTO timeDTO, Pharmacist pharmacist){
        return pharmacist.getPharmacistAppointments().stream().filter(pharmacistAppointment -> utilityMethods.isSelectedDateReserved(timeDTO,pharmacistAppointment)).count() == 0;
    }

    //#1
    private PharmacistAppointment createNewPharmacistAppointment(Patient patient, Pharmacist pharmacist, PharmacistAppointmentTimeDTO timeDTO){

        PharmacistAppointment pharmacistAppointment = new PharmacistAppointment();
        PriceListForAppointmentDTO priceListForAppointmentDTO = priceListRepository.getPriceListForAppointmentByPharmacyId(pharmacist.getPharmacyForPharmacist().getId());
        pharmacistAppointment.setPharmacistForAppointment(pharmacist);
        pharmacistAppointment.setPatientWithPharmacistAppointment(patient);
        pharmacistAppointment.setStatusOfAppointment(StatusOfAppointment.Reserved);
        pharmacistAppointment.setTypeOfAppointment(TypeOfAppointment.Pharmacist_appointment);
        pharmacistAppointment.setAppointmentPoints(0);
        pharmacistAppointment.setAppointmentPrice(priceListForAppointmentDTO.getPharmacistAppointmentPricePerHour());
        pharmacistAppointment.setPharmacistAppointmentDuration((long)timeDTO.getDuration());
        pharmacistAppointment.setPharmacistAppointmentStartTime(timeDTO.getStartTime());
        return pharmacistAppointment;

    }




}
