package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.DTO.AppointmentScheduleByStaffDTO;
import com.isa.pharmacies_system.DTO.PatientAppointmentInfoDTO;
import com.isa.pharmacies_system.DTO.PriceListForAppointmentDTO;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
import com.isa.pharmacies_system.domain.user.Dermatologist;
import com.isa.pharmacies_system.repository.*;
import com.isa.pharmacies_system.service.iService.IDermatologistAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.isa.pharmacies_system.domain.schedule.StatusOfAppointment;
import com.isa.pharmacies_system.domain.user.Patient;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DermatologistAppointmentService implements IDermatologistAppointmentService {

    private IDermatologistAppointmentRepository dermatologistAppointmentRepository;
    private IPatientRepository patientRepository;
    private IDermatologistRepository dermatologistRepository;
    private IPharmacyRepository pharmacyRepository;
    private UtilityMethods utilityMethods;
    private IPriceListRepository priceListRepository;

    @Autowired
    public DermatologistAppointmentService(IDermatologistAppointmentRepository dermatologistAppointmentRepository, IPatientRepository patientRepository, IDermatologistRepository dermatologistRepository, IPharmacyRepository pharmacyRepository, IPriceListRepository priceListRepository) {
        this.dermatologistAppointmentRepository = dermatologistAppointmentRepository;
        this.patientRepository = patientRepository;
        this.dermatologistRepository = dermatologistRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.priceListRepository = priceListRepository;
        this.utilityMethods = new UtilityMethods();
    }

    @Override
    public DermatologistAppointment findOne(Long id){
        return dermatologistAppointmentRepository.findById(id).orElse(null);
    }

    //#1[3.13]
    @Override
    public List<DermatologistAppointment> getOpenDermatologistAppointment(Long pharmacyId){
        return dermatologistAppointmentRepository.getOpenDermatologistAppointment(pharmacyId);
    }

    //#1[3.13]
    @Override
    public Boolean bookDermatologistAppointment(Long patientId,Long appointmentId){

        DermatologistAppointment dermatologistAppointment = findOne(appointmentId);
        Patient patient = patientRepository.findById(patientId).orElse(null);
        if(isAppointmentOpen(dermatologistAppointment) && !doesPatientHaveAnotherAppointmentInSameTime(patient,dermatologistAppointment)){
            dermatologistAppointment.setPatientWithDermatologistAppointment(patient);
            dermatologistAppointment.setStatusOfAppointment(StatusOfAppointment.Reserved);
            dermatologistAppointmentRepository.save(dermatologistAppointment);
            return true;
        }
        return false;
    }

    //#1[3.15]
    @Override
    public Boolean cancelDermatologistAppointment(DermatologistAppointment dermatologistAppointment){

        if(utilityMethods.isCancellationPossible(dermatologistAppointment.getDermatologistAppointmentStartTime()) && isAppointmentReserved(dermatologistAppointment)){
            dermatologistAppointment.setStatusOfAppointment(StatusOfAppointment.Open);
            dermatologistAppointment.setPatientWithDermatologistAppointment(null);
            dermatologistAppointmentRepository.save(dermatologistAppointment);
            return true;
        }
        return false;
    }

    private Boolean isAppointmentReserved(DermatologistAppointment dermatologistAppointment){
       return dermatologistAppointment.getStatusOfAppointment().equals(StatusOfAppointment.Reserved);
    }

    private Boolean isAppointmentOpen(DermatologistAppointment dermatologistAppointment){
        return dermatologistAppointment.getStatusOfAppointment().equals(StatusOfAppointment.Open);
    }

    //Nemanja
    private Boolean doesPatientHaveAnotherAppointmentInSameTime(Patient patient,DermatologistAppointment dermatologistAppointment){
        List<DermatologistAppointment> dermatologistAppointmentList = getAllFutureReservedDermatologistAppointmentByPatient(patient);
        List<PharmacistAppointment> pharmacistAppointmentList = getAllFutureReservedPharmacistAppointmentByPatient(patient);

        if(checkDoesHaveAnyOtherDermatologistAppointmentWithSameTime(dermatologistAppointment,dermatologistAppointmentList)
            || checkDoesPatientHavePharmacistAppointmentWithSameTime(dermatologistAppointment,pharmacistAppointmentList)){
            return true;
        }
        return false;
    }

    //Nemanja
    private List<DermatologistAppointment> getAllFutureReservedDermatologistAppointmentByPatient(Patient patient) {
        return patient.getDermatologistAppointment().stream()
                .filter(d -> (d.getStatusOfAppointment().equals(StatusOfAppointment.Reserved) && d.getDermatologistAppointmentStartTime().isAfter(LocalDateTime.now())))
                .collect(Collectors.toList());
    }

    //Nemanja
    private List<PharmacistAppointment> getAllFutureReservedPharmacistAppointmentByPatient(Patient patient) {
        return patient.getPharmacistAppointments().stream()
                .filter(p -> (p.getStatusOfAppointment().equals(StatusOfAppointment.Reserved) && p.getPharmacistAppointmentStartTime().isAfter(LocalDateTime.now())))
                .collect(Collectors.toList());
    }

    //Nemanja
    private Boolean checkDoesHaveAnyOtherDermatologistAppointmentWithSameTime(DermatologistAppointment dermatologistAppointment,List<DermatologistAppointment> list){
        LocalDateTime startTime = dermatologistAppointment.getDermatologistAppointmentStartTime();
        LocalDateTime endTime = dermatologistAppointment.getDermatologistAppointmentEndTime();

        for (DermatologistAppointment a: list
        ) {
            if( utilityMethods.isTimeBetweenTwoOtherTime(startTime,a.getDermatologistAppointmentStartTime(),a.getDermatologistAppointmentEndTime())
                || utilityMethods.isTimeBetweenTwoOtherTime(endTime,a.getDermatologistAppointmentStartTime(),a.getDermatologistAppointmentEndTime())
                || utilityMethods.isTimeIntervalOutsideSecondTimeInterval(startTime,endTime,a.getDermatologistAppointmentStartTime(),a.getDermatologistAppointmentEndTime())
                || utilityMethods.isTimeIntervalEqualsWithSecondTimeInterval(startTime,endTime,a.getDermatologistAppointmentStartTime(),a.getDermatologistAppointmentEndTime())){

                return true;
            }
        }
        return false;
    }

    //Nemanja
    private Boolean checkDoesPatientHavePharmacistAppointmentWithSameTime(DermatologistAppointment dermatologistAppointment,List<PharmacistAppointment> list){
        LocalDateTime firstStartTime = dermatologistAppointment.getDermatologistAppointmentStartTime();
        LocalDateTime firstEndTime = dermatologistAppointment.getDermatologistAppointmentEndTime();

        for (PharmacistAppointment a: list
        ) {
            LocalDateTime secondStartTime = a.getPharmacistAppointmentStartTime();
            LocalDateTime secondEndTime = secondStartTime.plusMinutes(a.getPharmacistAppointmentDuration());
            if( utilityMethods.isTimeBetweenTwoOtherTime(firstStartTime,secondStartTime,secondEndTime)
                    || utilityMethods.isTimeBetweenTwoOtherTime(firstEndTime,secondStartTime,secondEndTime)
                    || utilityMethods.isTimeIntervalOutsideSecondTimeInterval(firstStartTime,firstEndTime,secondStartTime,secondEndTime)
                    || utilityMethods.isTimeIntervalEqualsWithSecondTimeInterval(firstStartTime,firstEndTime,secondStartTime,secondEndTime)){

                return true;
            }
        }

        return false;
    }

    //Nemanja
    @Override
    public Page<DermatologistAppointment> getAllPastDermatologistAppointmentByDermatologist(Long id, int page) {
        return dermatologistAppointmentRepository.findAllPastDermatologistAppointment(id, PageRequest.of(page,10));
    }

    //Nemanja
    @Override
    public Page<DermatologistAppointment> getAllPastDermatologistAppointmentByDermatologistAndPharmacy(Long idDermatologist, Long idPharmacy, int page) {
        return dermatologistAppointmentRepository.findAllPastDermatologistAppointmentByPharmacy(idDermatologist,idPharmacy,PageRequest.of(page,10));
    }

    //Nemanja
    @Override
    public List<PatientAppointmentInfoDTO> sortByAppointmentEndTime(List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList, Boolean asc) {
        if(asc){
            Collections.sort(patientAppointmentInfoDTOList, Comparator.comparing(PatientAppointmentInfoDTO::getAppointmentEndTime));
        }else{
            Collections.sort(patientAppointmentInfoDTOList, Comparator.comparing(PatientAppointmentInfoDTO::getAppointmentEndTime).reversed());
        }
        return patientAppointmentInfoDTOList;
    }

    //Nemanja
    @Override
    public List<DermatologistAppointment> getAllFutureOpenDermatologistAppointmentForDermatologistInPharmacy(Long dermatologistId, Long pharmacyId) {
        return dermatologistAppointmentRepository.findAllFutureOpenDermatologistAppointmentByDermatologistAndPharmacy(dermatologistId,pharmacyId);
    }

    //Nemanja
    @Override
    public List<DermatologistAppointment> findAllFutureReservedDermatologistAppointmentByDermatologistAndPharmacy(Long dermatologistId, Long pharmacyId) {
        return dermatologistAppointmentRepository.findAllFutureReservedDermatologistAppointmentByDermatologistAndPharmacy(dermatologistId,pharmacyId);
    }

    //Nemanja
    @Override
    public List<DermatologistAppointment> getAllMissedDermatologistAppointmentByDermatologistAndPharmacyId(Long dermatologistId, Long pharmacyId) {
        return dermatologistAppointmentRepository.findAllMissedDermatologistAppointmentByDermatologistAndPharmacy(dermatologistId,pharmacyId);
    }

    //Nemanja
    @Override
    public List<DermatologistAppointment> getAllExpiredDermatologistAppointmentByDermatologistAndPharmacyId(Long dermatologistId, Long pharmacyId) {
        return dermatologistAppointmentRepository.findAllExpiredDermatologistAppointmentByDermatologistAndPharmacy(dermatologistId,pharmacyId);
    }

    //Nemanja
    @Override
    public List<DermatologistAppointment> getAllReservedDermatologistAppointmentByDermatologistAndPharmacyId(Long dermatologistId, Long pharmacyId) {
        return dermatologistAppointmentRepository.findAllReservedDermatologistAppointmentByDermatologistAndPharmacy(dermatologistId,pharmacyId);
    }

    //Nemanja
    @Override
    public Boolean bookDermatologistAppointmentByDermatologist(AppointmentScheduleByStaffDTO appointmentScheduleByStaffDTO, DermatologistAppointment dermatologistAppointment) {

        if(!isDermatologistAppointmentInsideDermatologistWorkTime(appointmentScheduleByStaffDTO)
            || !utilityMethods.isTimeBeforeOtherTime(appointmentScheduleByStaffDTO.getAppointmentStartTime(),appointmentScheduleByStaffDTO.getAppointmentEndTime())){
            return false;
        }

        Patient patient = patientRepository.findById(appointmentScheduleByStaffDTO.getPatientId()).orElse(null);
        Dermatologist dermatologist = dermatologistRepository.findById(appointmentScheduleByStaffDTO.getStaffId()).orElse(null);

        if(!doesPatientHaveAnotherAppointmentInSameTime(patient,dermatologistAppointment)
            && !doesDermatologistHaveAppointmentInSameTime(dermatologist,dermatologistAppointment)){

            Pharmacy pharmacy = pharmacyRepository.findById(appointmentScheduleByStaffDTO.getPharmacyId()).orElse(null);
            fillDermatologistAppointmentWithPatientPharmacyAndDermatologist(patient,dermatologist,pharmacy,dermatologistAppointment);
            fillDermatologistAppointmentWithPrice(dermatologistAppointment);
            dermatologistAppointmentRepository.save(dermatologistAppointment);
            return true;
        }
        return false;
    }

    //Nemanja
    private void fillDermatologistAppointmentWithPrice(DermatologistAppointment dermatologistAppointment) {
        PriceListForAppointmentDTO priceListForAppointmentDTO = priceListRepository.getPriceListForAppointmentByPharmacyId(dermatologistAppointment.getPharmacyForDermatologistAppointment().getId());
        dermatologistAppointment.setAppointmentPrice(priceListForAppointmentDTO.getDermatologistAppointmentPricePerHour());
    }

    //Nemanja
    private void fillDermatologistAppointmentWithPatientPharmacyAndDermatologist(Patient patient,Dermatologist dermatologist,Pharmacy pharmacy,DermatologistAppointment dermatologistAppointment){
        dermatologistAppointment.setDermatologistForAppointment(dermatologist);
        dermatologistAppointment.setPharmacyForDermatologistAppointment(pharmacy);
        dermatologistAppointment.setPatientWithDermatologistAppointment(patient);
    }

    //Nemanja
    private Boolean isDermatologistAppointmentInsideDermatologistWorkTime(AppointmentScheduleByStaffDTO appointmentScheduleByStaffDTO){
        LocalDateTime appointmentStartTime = appointmentScheduleByStaffDTO.getAppointmentStartTime();
        LocalDateTime appointmentEndTime = appointmentScheduleByStaffDTO.getAppointmentEndTime();
        LocalDateTime workStartTime = appointmentScheduleByStaffDTO.getStaffWorkStartTime();
        LocalDateTime workEndTime = appointmentScheduleByStaffDTO.getStaffWorkEndTime();
        if(workStartTime == null || workEndTime == null){
            return false;
        }
        return utilityMethods.isTimeIntervalOutsideSecondTimeInterval(workStartTime,workEndTime,appointmentStartTime,appointmentEndTime);
    }

    //Nemanja
    private Boolean doesDermatologistHaveAppointmentInSameTime(Dermatologist dermatologist, DermatologistAppointment dermatologistAppointment) {
        List<DermatologistAppointment> dermatologistAppointmentList = getAllFutureReservedAndOpenDermatologistAppointmentByDermatologist(dermatologist);
        return checkDoesHaveAnyOtherDermatologistAppointmentWithSameTime(dermatologistAppointment,dermatologistAppointmentList);
    }

    //Nemanja
    private List<DermatologistAppointment> getAllFutureReservedAndOpenDermatologistAppointmentByDermatologist(Dermatologist dermatologist){
        return dermatologist.getDermatologistAppointments().stream()
                .filter(d -> ((d.getStatusOfAppointment().equals(StatusOfAppointment.Reserved) || d.getStatusOfAppointment().equals(StatusOfAppointment.Open)) && d.getDermatologistAppointmentStartTime().isAfter(LocalDateTime.now())))
                .collect(Collectors.toList());
    }

    //Nemanja
    @Override
    public Boolean changeDermatologistAppointmentStatusToMissed(DermatologistAppointment dermatologistAppointment) {
        if(dermatologistAppointment.getStatusOfAppointment().equals(StatusOfAppointment.Reserved) && dermatologistAppointment.getDermatologistAppointmentStartTime().isBefore(LocalDateTime.now())){
            dermatologistAppointment.setStatusOfAppointment(StatusOfAppointment.Missed);
            addPatientPoint(dermatologistAppointment.getPatientWithDermatologistAppointment());
            dermatologistAppointmentRepository.save(dermatologistAppointment);
            return true;
        }
        return false;
    }

    //Nemanja
    private void addPatientPoint(Patient patient) {
        patient.setPatientPoints(patient.getPatientPoints() + 1);
    }
}
