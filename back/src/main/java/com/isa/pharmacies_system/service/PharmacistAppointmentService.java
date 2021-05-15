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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    public PharmacistAppointment findOne(Long id) {
        return pharmacistAppointmentRepository.findById(id).orElse(null);
    }


    //#1[3.16]Korak3
    @Override
    public Boolean bookPharmacistAppointment(Long patientId, Long pharmacistId, PharmacistAppointmentTimeDTO timeDTO){
        Patient patient = patientRepository.findById(patientId).orElse(null);
        Pharmacist pharmacist = pharmacistRepository.findById(pharmacistId).orElse(null);
        PharmacistAppointment pharmacistAppointment;
        if(patient == null && pharmacist == null){
            return false;
        }else{
            pharmacistAppointment = createNewPharmacistAppointment(patient, pharmacist, timeDTO);
        }
        if(timeDTO.getStartTime().isAfter(LocalDateTime.now())
                && doesPharmacistWorkInSelectedDate(timeDTO, pharmacist)
                && doesPharmacistHaveOpenSelectedAppointment(timeDTO, pharmacist)
                && !doesPatientHaveAnotherAppointmentInSameTime(patient,pharmacistAppointment)){

            pharmacistAppointmentRepository.save(pharmacistAppointment);
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

    //Nemanja
    private Boolean doesPatientHaveAnotherAppointmentInSameTime(Patient patient,PharmacistAppointment pharmacistAppointment){
        List<DermatologistAppointment> dermatologistAppointmentList = getAllFutureReservedDermatologistAppointmentByPatient(patient);
        List<PharmacistAppointment> pharmacistAppointmentList = getAllFutureReservedPharmacistAppointmentByPatient(patient);

        if(checkDoesHaveAnyOtherDermatologistAppointmentWithSameTime(pharmacistAppointment,dermatologistAppointmentList)
                || checkDoesPatientHavePharmacistAppointmentWithSameTime(pharmacistAppointment,pharmacistAppointmentList)){
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
    private Boolean checkDoesHaveAnyOtherDermatologistAppointmentWithSameTime(PharmacistAppointment pharmacistAppointment,List<DermatologistAppointment> list){
        LocalDateTime startTime = pharmacistAppointment.getPharmacistAppointmentStartTime();
        LocalDateTime endTime = pharmacistAppointment.getPharmacistAppointmentStartTime().plusMinutes(pharmacistAppointment.getPharmacistAppointmentDuration());
        return utilityMethods.checkDoesHaveAnyOtherDermatologistAppointmentWithSameTime(list, startTime, endTime);
    }

    //Nemanja
    private Boolean checkDoesPatientHavePharmacistAppointmentWithSameTime(PharmacistAppointment pharmacistAppointment,List<PharmacistAppointment> list){
        LocalDateTime firstStartTime = pharmacistAppointment.getPharmacistAppointmentStartTime();
        LocalDateTime firstEndTime = pharmacistAppointment.getPharmacistAppointmentStartTime().plusMinutes(pharmacistAppointment.getPharmacistAppointmentDuration());
        return utilityMethods.checkDoesPatientHavePharmacistAppointmentWithSameTime(list, firstStartTime, firstEndTime);
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

    //#1[3.18]
    @Override
    public List<PharmacistAppointment> getFutureReservedAppointment(Long patientId){
        Patient patient = patientRepository.findById(patientId).orElse(null);
        if(patient != null){
                return patient.getPharmacistAppointments().stream().filter(pharmacistAppointment -> pharmacistAppointment.getPharmacistAppointmentStartTime().isAfter(LocalDateTime.now()) && pharmacistAppointment.getStatusOfAppointment().equals(StatusOfAppointment.Reserved)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    //#1[3.18]
    @Override
    public Boolean cancelPharmacistAppointment(Long appointmentId){
        PharmacistAppointment appointment = pharmacistAppointmentRepository.findById(appointmentId).orElse(null);
        if(appointment != null && utilityMethods.isCancellationPossible(appointment.getPharmacistAppointmentStartTime())){
            appointment.setPatientWithPharmacistAppointment(null);
            appointment.setPharmacistForAppointment(null);
            appointment.setStatusOfAppointment(StatusOfAppointment.Cancel);
            pharmacistAppointmentRepository.save(appointment);
            return true;
        }
        return false;
    }

    //Nemanja
    @Override
    public Page<PharmacistAppointment> getAllPastPharmacistAppointmentByPharmacist(Long id,int page) {
        return pharmacistAppointmentRepository.findAllPastPharmacistAppointment(id,PageRequest.of(page,10));
    }

    //Nemanja
    @Override
    public List<PatientAppointmentInfoDTO> sortByAppointmentDuration(List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList, Boolean asc) {
        if(asc){
            Collections.sort(patientAppointmentInfoDTOList, Comparator.comparing(PatientAppointmentInfoDTO::getAppointmentDuration));
        }else{
            Collections.sort(patientAppointmentInfoDTOList, Comparator.comparing(PatientAppointmentInfoDTO::getAppointmentDuration).reversed());
        }
        return patientAppointmentInfoDTOList;
    }

    //Nemanja
    @Override
    public List<PharmacistAppointment> getAllMissedPharmacistAppointmentByPharmacist(Long pharmacistId) {
        return pharmacistAppointmentRepository.findAllMissedPharmacistAppointmentByPharmacist(pharmacistId);
    }

    //Nemanja
    @Override
    public List<PharmacistAppointment> getAllExpiredPharmacistAppointmentByPharmacist(Long pharmacistId) {
        return pharmacistAppointmentRepository.findAllExpiredPharmacistAppointmentByPharmacist(pharmacistId);
    }

    //Nemanja
    @Override
    public List<PharmacistAppointment> getAllReservedPharmacistAppointmentByPharmacist(Long pharmacistId) {
        return pharmacistAppointmentRepository.findAllReservedPharmacistAppointmentByPharmacist(pharmacistId);
    }

    //Nemanja
    @Override
    public List<PharmacistAppointment> getAllFutureReservedAppointmentByPharmacist(Long pharmacistId) {
        return pharmacistAppointmentRepository.findAllFutureReservedPharmacistAppointmentByPharmacist(pharmacistId);
    }

    //Nemanja
    @Override
    public Boolean changePharmacistAppointmentStatusToMissed(PharmacistAppointment pharmacistAppointment) {
        if(pharmacistAppointment.getStatusOfAppointment().equals(StatusOfAppointment.Reserved) && pharmacistAppointment.getPharmacistAppointmentStartTime().isBefore(LocalDateTime.now())){
            pharmacistAppointment.setStatusOfAppointment(StatusOfAppointment.Missed);
            addPatientPoint(pharmacistAppointment.getPatientWithPharmacistAppointment());
            pharmacistAppointmentRepository.save(pharmacistAppointment);
            return true;
        }
        return false;
    }

    //Nemanja
    private void addPatientPoint(Patient patient) {
        patient.setPatientPoints(patient.getPatientPoints() + 1);
    }
}
