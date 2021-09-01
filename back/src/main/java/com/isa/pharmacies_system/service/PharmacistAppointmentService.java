package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.DTO.PatientAppointmentInfoDTO;
import com.isa.pharmacies_system.DTO.PharmacistAppointmentTimeDTO;
import com.isa.pharmacies_system.DTO.PriceListForAppointmentDTO;
import com.isa.pharmacies_system.domain.pharmacy.SystemLoyalty;
import com.isa.pharmacies_system.domain.schedule.*;
import com.isa.pharmacies_system.domain.user.CategoryOfPatient;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.domain.user.Pharmacist;
import com.isa.pharmacies_system.repository.*;
import com.isa.pharmacies_system.service.iService.IPharmacistAppointmentService;
import com.isa.pharmacies_system.service.iService.ISystemLoyaltyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private ISystemLoyaltyService iSystemLoyaltyService;

    @Autowired
    public PharmacistAppointmentService(IPharmacistAppointmentRepository pharmacistAppointmentRepository, IPharmacistRepository pharmacistRepository, IPatientRepository patientRepository, IPriceListRepository priceListRepository, IWorkingHoursRepository workingHoursRepository, ISystemLoyaltyService iSystemLoyaltyService) {
        this.pharmacistAppointmentRepository = pharmacistAppointmentRepository;
        this.pharmacistRepository = pharmacistRepository;
        this.patientRepository = patientRepository;
        this.priceListRepository = priceListRepository;
        this.workingHoursRepository = workingHoursRepository;
        this.utilityMethods = new UtilityMethods();
        this.iSystemLoyaltyService = iSystemLoyaltyService;
    }

    @Override
    public PharmacistAppointment findOne(Long id) {
        return pharmacistAppointmentRepository.findById(id).orElse(null);
    }


    //#1[3.16]Korak3
    @Transactional
    @Override
    public Boolean bookPharmacistAppointment(Long patientId, Long pharmacistId, PharmacistAppointmentTimeDTO timeDTO,Boolean isPatient){
    	SystemLoyalty systemLoyalty=iSystemLoyaltyService.get();
        Patient patient = patientRepository.findById(patientId).orElse(null);
        Pharmacist pharmacist = pharmacistRepository.findLockedById(pharmacistId).orElse(null);
        PharmacistAppointment pharmacistAppointment;
        if(patient == null || pharmacist == null){
            return false;
        }else{
            pharmacistAppointment = createNewPharmacistAppointment(patient, pharmacist, timeDTO);
        }
        if(timeDTO.getStartTime().isAfter(LocalDateTime.now())
                && doesPharmacistWorkInSelectedDate(timeDTO, pharmacist)
                && doesPharmacistHaveOpenSelectedAppointment(timeDTO, pharmacist)
                && !doesPatientHaveAnotherAppointmentInSameTime(patient,pharmacistAppointment)
                && ((patient.getPenalty() < 3 && isPatient) || (!isPatient))){
        	
        	//dodati da se pacijentu mijenjaju poeni i provjerava rank
        	patient.setPatientPoints(patient.getPatientPoints()+systemLoyalty.getPointsForDermatologistAppointment());
        	if(patient.getPatientPoints()>=systemLoyalty.getDiscountRegular() && patient.getPatientPoints()<systemLoyalty.getDiscountSilver()) {
				patient.setCategoryOfPatient(CategoryOfPatient.Regular);
				//pharmacistAppointment.setAppointmentPrice(pharmacistAppointment.getAppointmentPrice()*systemLoyalty.getDiscountRegular()/100);
			} else if(patient.getPatientPoints()>=systemLoyalty.getDiscountSilver() && patient.getPatientPoints()<systemLoyalty.getDiscountGold()) {
				patient.setCategoryOfPatient(CategoryOfPatient.Silver);
				//pharmacistAppointment.setAppointmentPrice(pharmacistAppointment.getAppointmentPrice()*systemLoyalty.getDiscountSilver()/100);
			} else {
				patient.setCategoryOfPatient(CategoryOfPatient.Gold);
				//pharmacistAppointment.setAppointmentPrice(dermatologistAppointment.getAppointmentPrice()*systemLoyalty.getDiscountGold()/100);
			}
        	patientRepository.save(patient);
        	
        	fillPriceForPharmacistAppointment(timeDTO,pharmacistAppointment);
            pharmacistAppointmentRepository.save(pharmacistAppointment);
            return true;
        }else{
            return false;
        }
    }
    //dodati provjeru za cijenu i rank
    private void fillPriceForPharmacistAppointment(PharmacistAppointmentTimeDTO timeDTO, PharmacistAppointment pharmacistAppointment) {
    	SystemLoyalty systemLoyalty=iSystemLoyaltyService.get();
    	double coefficient=1.0;
    	if (pharmacistAppointment.getPatientWithPharmacistAppointment().getCategoryOfPatient()==CategoryOfPatient.Regular) {
    		coefficient=systemLoyalty.getDiscountRegular()/100;
    	} else if (pharmacistAppointment.getPatientWithPharmacistAppointment().getCategoryOfPatient()==CategoryOfPatient.Silver) {
    		coefficient=systemLoyalty.getDiscountSilver()/100;
    	} else if (pharmacistAppointment.getPatientWithPharmacistAppointment().getCategoryOfPatient()==CategoryOfPatient.Gold) {
    		coefficient=systemLoyalty.getDiscountGold()/100;
    	}
    	PriceListForAppointmentDTO priceListForAppointmentDTO = priceListRepository.getPriceListForAppointmentByPharmacyId(pharmacistAppointment.getPharmacistForAppointment().getPharmacyForPharmacist().getId());
        pharmacistAppointment.setAppointmentPrice((priceListForAppointmentDTO.getDermatologistAppointmentPricePerHour() * (long)timeDTO.getDuration()/60)*coefficient);

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
    public List<PharmacistAppointment> getAllPastPharmacistAppointmentByPharmacist(Long id) {
        return pharmacistAppointmentRepository.findAllPastPharmacistAppointment(id);
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
    public List<PharmacistAppointment> searchAllFutureReservedByPatientFirstAndLastName(Long pharmacistId, String firstName, String lastName) {
        List<PharmacistAppointment> futurePharmacistAppointment = pharmacistAppointmentRepository.findAllFutureReservedPharmacistAppointmentByPharmacist(pharmacistId);
        return futurePharmacistAppointment.stream()
                .filter(p -> ( (p.getPatientWithPharmacistAppointment().getFirstName().toLowerCase().contains(firstName.toLowerCase())
                        && p.getPatientWithPharmacistAppointment().getLastName().toLowerCase().contains(lastName.toLowerCase()))
                        || (p.getPatientWithPharmacistAppointment().getFirstName().toLowerCase().contains(lastName.toLowerCase())
                        && p.getPatientWithPharmacistAppointment().getLastName().toLowerCase().contains(firstName.toLowerCase())) )).collect(Collectors.toList());
    }

    //Nemanja
    @Transactional
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
    @Transactional
    @Override
    public void setMissedPharmacistAppointmentEveryDayOnRightStatusAndIncreasePenaltyForPatient() {
        List<PharmacistAppointment> allAppointmentsInPast = pharmacistAppointmentRepository.findAllReservedAppointmentsInPast();
        for (PharmacistAppointment pa:
             allAppointmentsInPast) {
            if(pa.getPharmacistAppointmentStartTime().plusMinutes(pa.getPharmacistAppointmentDuration()).isBefore(LocalDateTime.now())){
                addPatientPoint(pa.getPatientWithPharmacistAppointment());
                pa.setStatusOfAppointment(StatusOfAppointment.Missed);
                pharmacistAppointmentRepository.save(pa);
            }
        }
    }

    //Nemanja
    private void addPatientPoint(Patient patient) {
        patient.setPenalty(patient.getPenalty() + 1);
    }

    //Nemanja
    @Transactional
    @Override
    public Boolean bookPharmacistAppointmentTest(Long patientId, Long pharmacistId, PharmacistAppointmentTimeDTO timeDTO,Boolean isPatient,Long milliseconds){

        PharmacistAppointment pharmacistAppointment = fillPatientAndPharmacistForAppointment(patientId,pharmacistId,timeDTO);
        if(pharmacistAppointment == null){
            return false;
        }
        if(timeDTO.getStartTime().isAfter(LocalDateTime.now())
                && doesPharmacistWorkInSelectedDate(timeDTO, pharmacistAppointment.getPharmacistForAppointment())
                && doesPharmacistHaveOpenSelectedAppointment(timeDTO, pharmacistAppointment.getPharmacistForAppointment())
                && !doesPatientHaveAnotherAppointmentInSameTime(pharmacistAppointment.getPatientWithPharmacistAppointment(),pharmacistAppointment)
                && ((pharmacistAppointment.getPatientWithPharmacistAppointment().getPenalty() < 3 && isPatient) || (!isPatient))){

            fillPriceForPharmacistAppointment(timeDTO,pharmacistAppointment);
            try { Thread.sleep(milliseconds); } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            pharmacistAppointmentRepository.save(pharmacistAppointment);

            return true;
        }else{
            return false;
        }
    }

    //Nemanja
    private PharmacistAppointment fillPatientAndPharmacistForAppointment(Long patientId, Long pharmacistId, PharmacistAppointmentTimeDTO timeDTO) {
        Patient patient = patientRepository.findById(patientId).orElse(null);
        Pharmacist pharmacist = pharmacistRepository.findLockedById(pharmacistId).orElse(null);
        if(patient == null || pharmacist == null){
            return null;
        }else{
            return createNewPharmacistAppointment(patient, pharmacist, timeDTO);
        }
    }

}
