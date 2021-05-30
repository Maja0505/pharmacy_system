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
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;

import java.util.List;
import java.util.Locale;
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
    @Transactional
    @Override
    public Boolean bookDermatologistAppointment(Long patientId,Long appointmentId){

        DermatologistAppointment dermatologistAppointment = findOne(appointmentId);
        Patient patient = patientRepository.findById(patientId).orElse(null);
        if(patient != null && patient.getPenalty() < 3){
            if(isAppointmentOpen(dermatologistAppointment)
                    && !doesPatientHaveAnotherAppointmentInSameTime(patient,dermatologistAppointment)){
                dermatologistAppointment.setPatientWithDermatologistAppointment(patient);
                dermatologistAppointment.setStatusOfAppointment(StatusOfAppointment.Reserved);
                dermatologistAppointmentRepository.save(dermatologistAppointment);
                return true;
            }
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
        return utilityMethods.checkDoesHaveAnyOtherDermatologistAppointmentWithSameTime(list,startTime,endTime);
    }

    //Nemanja
    private Boolean checkDoesPatientHavePharmacistAppointmentWithSameTime(DermatologistAppointment dermatologistAppointment,List<PharmacistAppointment> list){
        LocalDateTime firstStartTime = dermatologistAppointment.getDermatologistAppointmentStartTime();
        LocalDateTime firstEndTime = dermatologistAppointment.getDermatologistAppointmentEndTime();
        return utilityMethods.checkDoesPatientHavePharmacistAppointmentWithSameTime(list,firstStartTime,firstEndTime);
    }

    //Nemanja
    @Override
    public List<DermatologistAppointment> getAllPastDermatologistAppointmentByDermatologist(Long id) {
        return dermatologistAppointmentRepository.findAllPastDermatologistAppointment(id);
    }

    //Nemanja
    @Override
    public List<DermatologistAppointment> getAllPastDermatologistAppointmentByDermatologistAndPharmacy(Long idDermatologist, Long idPharmacy) {
        return dermatologistAppointmentRepository.findAllPastDermatologistAppointmentByPharmacy(idDermatologist,idPharmacy);
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
    public List<DermatologistAppointment> searchAllFutureReservedByPatientFirstAndLastName(Long dermatologistId, String firstName, String lastName) {
        List<DermatologistAppointment> futureDermatologistAppointment = dermatologistAppointmentRepository.findAllFutureReservedDermatologistAppointmentByDermatologist(dermatologistId);
        return futureDermatologistAppointment.stream()
                .filter(d -> ( (d.getPatientWithDermatologistAppointment().getFirstName().toLowerCase().contains(firstName.toLowerCase())
                        && d.getPatientWithDermatologistAppointment().getLastName().toLowerCase().contains(lastName.toLowerCase()))
                        || (d.getPatientWithDermatologistAppointment().getFirstName().toLowerCase().contains(lastName.toLowerCase())
                        && d.getPatientWithDermatologistAppointment().getLastName().toLowerCase().contains(firstName.toLowerCase())) )).collect(Collectors.toList());
    }

    //Nemanja
    @Override
    public List<DermatologistAppointment> findAllFutureReservedDermatologistAppointmentByDermatologist(Long dermatologistId) {
        return dermatologistAppointmentRepository.findAllFutureReservedDermatologistAppointmentByDermatologist(dermatologistId);
    }

    //Nemanja
    @Override
    public Boolean bookDermatologistAppointmentByDermatologist(AppointmentScheduleByStaffDTO appointmentScheduleByStaffDTO, DermatologistAppointment dermatologistAppointment) {


        if(!isDermatologistAppointmentInsideDermatologistWorkTime(appointmentScheduleByStaffDTO)
            || !utilityMethods.isTimeBeforeOtherTime(appointmentScheduleByStaffDTO.getAppointmentStartTime(),appointmentScheduleByStaffDTO.getAppointmentEndTime())){
            if(!(appointmentScheduleByStaffDTO.getAppointmentStartTime().equals(appointmentScheduleByStaffDTO.getStaffWorkStartTime())
                || appointmentScheduleByStaffDTO.getAppointmentEndTime().equals(appointmentScheduleByStaffDTO.getStaffWorkEndTime()))){
                return false;
            }
        }

        Patient patient = patientRepository.findById(appointmentScheduleByStaffDTO.getPatientId()).orElse(null);
        Dermatologist dermatologist = dermatologistRepository.findById(appointmentScheduleByStaffDTO.getStaffId()).orElse(null);

        if(!doesPatientHaveAnotherAppointmentInSameTime(patient,dermatologistAppointment)
            && !doesDermatologistHaveAppointmentInSameTime(dermatologist,dermatologistAppointment)){

            Duration appointmentDuration = Duration.between(appointmentScheduleByStaffDTO.getAppointmentStartTime(),appointmentScheduleByStaffDTO.getAppointmentEndTime());
            Pharmacy pharmacy = pharmacyRepository.findById(appointmentScheduleByStaffDTO.getPharmacyId()).orElse(null);
            fillDermatologistAppointmentWithPatientPharmacyAndDermatologist(patient,dermatologist,pharmacy,dermatologistAppointment);
            fillDermatologistAppointmentWithPrice(dermatologistAppointment,appointmentDuration.toMinutes());
            dermatologistAppointmentRepository.save(dermatologistAppointment);
            return true;
        }
        return false;
    }

    //Nemanja
    private void fillDermatologistAppointmentWithPrice(DermatologistAppointment dermatologistAppointment, Long duration) {
        PriceListForAppointmentDTO priceListForAppointmentDTO = priceListRepository.getPriceListForAppointmentByPharmacyId(dermatologistAppointment.getPharmacyForDermatologistAppointment().getId());
        dermatologistAppointment.setAppointmentPrice(priceListForAppointmentDTO.getDermatologistAppointmentPricePerHour() * duration/60);
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
        patient.setPenalty(patient.getPenalty() + 1);
    }

    //Nemanja
    @Transactional
    @Override
    public Boolean bookDermatologistAppointmentTest(Long patientId,Long appointmentId,Long milliseconds){

        DermatologistAppointment dermatologistAppointment = findOne(appointmentId);
        Patient patient = patientRepository.findById(patientId).orElse(null);
        if(patient != null && patient.getPenalty() < 3){
            if(isAppointmentOpen(dermatologistAppointment)
                    && !doesPatientHaveAnotherAppointmentInSameTime(patient,dermatologistAppointment)){
                dermatologistAppointment.setPatientWithDermatologistAppointment(patient);
                dermatologistAppointment.setStatusOfAppointment(StatusOfAppointment.Reserved);
                try { Thread.sleep(milliseconds); } catch (InterruptedException e) {}
                dermatologistAppointmentRepository.save(dermatologistAppointment);
                return true;
            }
        }

        return false;
    }

}
