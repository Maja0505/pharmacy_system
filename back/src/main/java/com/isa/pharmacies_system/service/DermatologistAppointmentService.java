package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.DTO.PatientAppointmentInfoDTO;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
import com.isa.pharmacies_system.repository.IDermatologistAppointmentRepository;
import com.isa.pharmacies_system.repository.IPatientRepository;
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
    private UtilityMethods utilityMethods;

    @Autowired
    public DermatologistAppointmentService(IDermatologistAppointmentRepository dermatologistAppointmentRepository, IPatientRepository patientRepository) {
        this.dermatologistAppointmentRepository = dermatologistAppointmentRepository;
        this.patientRepository = patientRepository;
        this.utilityMethods = new UtilityMethods();
    }

    @Override
    public DermatologistAppointment findOne(Long id){
        return dermatologistAppointmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<DermatologistAppointment> getOpenDermatologistAppointment(){
        return dermatologistAppointmentRepository.getOpenDermatologistAppointment();
    }

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

    @Override
    public Boolean cancelDermatologistAppointment(DermatologistAppointment dermatologistAppointment){

        if(isCancellationPossible(dermatologistAppointment.getDermatologistAppointmentStartTime()) && isAppointmentReserved(dermatologistAppointment)){
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

    public Boolean isCancellationPossible(LocalDateTime start){

        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(now,start);
        return duration.toHours() >= 24;
    }

    //Nemanja
    private Boolean doesPatientHaveAnotherAppointmentInSameTime(Patient patient,DermatologistAppointment dermatologistAppointment){
        List<DermatologistAppointment> dermatologistAppointmentList = getAllFutureReservedDermatologistAppointmentByPatient(patient);
        List<PharmacistAppointment> pharmacistAppointmentList = getAllFutureReservedPharmacistAppointmentByPatient(patient);

        if(checkDoesPatientHaveDermatologistAppointmentWithSameTime(dermatologistAppointment,dermatologistAppointmentList)
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
    private Boolean checkDoesPatientHaveDermatologistAppointmentWithSameTime(DermatologistAppointment dermatologistAppointment,List<DermatologistAppointment> list){
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

}
