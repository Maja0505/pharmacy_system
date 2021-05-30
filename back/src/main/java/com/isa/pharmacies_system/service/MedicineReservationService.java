package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.DTO.MedicineReservationInfoDTO;
import com.isa.pharmacies_system.domain.medicine.MedicineReservation;
import com.isa.pharmacies_system.domain.medicine.StatusOfMedicineReservation;
import com.isa.pharmacies_system.domain.storage.PharmacyStorageItem;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.repository.IMedicineReservationRepository;
import com.isa.pharmacies_system.repository.IPatientRepository;
import com.isa.pharmacies_system.repository.IPharmacyStorageItemRepository;
import com.isa.pharmacies_system.service.iService.IMedicineReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class MedicineReservationService implements IMedicineReservationService {

    private IMedicineReservationRepository medicineReservationRepository;
    private IPharmacyStorageItemRepository pharmacyStorageItemRepository;
    private UtilityMethods utilityMethods;
    @Autowired
    private IPatientRepository patientRepository;


    public MedicineReservationService(IMedicineReservationRepository medicineReservationRepository, IPharmacyStorageItemRepository pharmacyStorageItemRepository) {
        this.medicineReservationRepository = medicineReservationRepository;
        this.pharmacyStorageItemRepository = pharmacyStorageItemRepository;
        this.utilityMethods = new UtilityMethods();

    }

    //#1[3.19]
    @Transactional
    @Override
    public Boolean createMedicineReservation(MedicineReservation medicineReservation){
        //uraditi provere
        PharmacyStorageItem pharmacyStorageItem = pharmacyStorageItemRepository.getSelectedMedicineFromPharmacyStorage(medicineReservation.getReservedMedicine().getId(),medicineReservation.getPharmacyForMedicineReservation().getId());
        System.out.println(pharmacyStorageItem != null);
        System.out.println(doesPharmacyHaveSelectedMedicineInStorage(pharmacyStorageItem));
        System.out.println(medicineReservation.getPatientForMedicineReservation().getPenalty() < 3);


        if(pharmacyStorageItem != null
                && doesPharmacyHaveSelectedMedicineInStorage(pharmacyStorageItem)
                && medicineReservation.getPatientForMedicineReservation().getPenalty() < 3){
            medicineReservationRepository.save(medicineReservation);
            pharmacyStorageItem.setMedicineAmount(pharmacyStorageItem.getMedicineAmount() - 1);
            pharmacyStorageItemRepository.save(pharmacyStorageItem);
            return true;
        }
        return false;
    }

    private Boolean doesPharmacyHaveSelectedMedicineInStorage(PharmacyStorageItem pharmacyStorageItem){
        return pharmacyStorageItem.getMedicineAmount() > 0;
    }

    //#1[3.20]
    @Override
    public Boolean cancelMedicineReservation(MedicineReservationInfoDTO medicineReservationInfoDTO){
        if(utilityMethods.isCancellationPossible(medicineReservationInfoDTO.getTakingDate().atTime(20,00, 00))){
            MedicineReservation medicineReservation = medicineReservationRepository.findById(medicineReservationInfoDTO.getReservationId()).orElse(null);
            if(medicineReservation != null){
                PharmacyStorageItem pharmacyStorageItem = pharmacyStorageItemRepository.getSelectedMedicineFromPharmacyStorage(medicineReservation.getReservedMedicine().getId(),medicineReservation.getPharmacyForMedicineReservation().getId());
                medicineReservation.setStatusOfMedicineReservation(StatusOfMedicineReservation.CANCELED);
                pharmacyStorageItem.setMedicineAmount(pharmacyStorageItem.getMedicineAmount() + 1);
                pharmacyStorageItemRepository.save(pharmacyStorageItem);
                medicineReservationRepository.save(medicineReservation);
                return true;
            }


        }
        return false;
    }

    //Nemanja
    @Override
    public MedicineReservation getMedicineReservationInPharmacy(Long medicineReservationId, Long pharmacyId) {
        List<MedicineReservation> medicineReservationList = medicineReservationRepository.findMedicineReservationByIdAndByPharmacy(medicineReservationId,pharmacyId);
        if(medicineReservationList.isEmpty()){
            return null;
        }
        MedicineReservation medicineReservation = medicineReservationList.get(0);
        Duration duration = Duration.between(LocalDateTime.now(),medicineReservation.getDateOfTakingMedicine().atTime(20,00, 00));
        if(duration.toHours() < 24){
            return null;
        }
        return medicineReservation;
    }

    //Nemanja
    @Transactional
    @Override
    public void finishMedicineReservation(MedicineReservation medicineReservation) {
        if(medicineReservation != null){
            if(medicineReservation.getStatusOfMedicineReservation().equals(StatusOfMedicineReservation.CREATED)){
                medicineReservation.setStatusOfMedicineReservation(StatusOfMedicineReservation.FINISHED);
                medicineReservationRepository.save(medicineReservation);
            }
        }
    }

    //Nemanja
    @Override
    public MedicineReservation getMedicineReservationById(Long medicineReservationId) {
        return medicineReservationRepository.findById(medicineReservationId).orElse(null);
    }

    //Nemanja
    @Transactional
    @Override
    public void finishMedicineReservationTest(MedicineReservation medicineReservation,Long milliseconds) {
        if(medicineReservation != null){
            if(medicineReservation.getStatusOfMedicineReservation().equals(StatusOfMedicineReservation.CREATED)){
                medicineReservation.setStatusOfMedicineReservation(StatusOfMedicineReservation.FINISHED);
                try { Thread.sleep(milliseconds); } catch (InterruptedException e) {}
                medicineReservationRepository.save(medicineReservation);
            }
        }
    }


    @Transactional
    @Override
    public Boolean createMedicineReservationTest(MedicineReservation medicineReservation,Long milliseconds){
        //uraditi provere
        PharmacyStorageItem pharmacyStorageItem = pharmacyStorageItemRepository.getSelectedMedicineFromPharmacyStorage(medicineReservation.getReservedMedicine().getId(),medicineReservation.getPharmacyForMedicineReservation().getId());
        if(pharmacyStorageItem != null
                && doesPharmacyHaveSelectedMedicineInStorage(pharmacyStorageItem)
                && medicineReservation.getPatientForMedicineReservation().getPenalty() < 3){
            medicineReservationRepository.save(medicineReservation);
            pharmacyStorageItem.setMedicineAmount(pharmacyStorageItem.getMedicineAmount() - 1);
            try { Thread.sleep(milliseconds); } catch (InterruptedException e) {}
            pharmacyStorageItemRepository.save(pharmacyStorageItem);
            return true;
        }
        return false;
    }


    @Transactional
    @Override
    public void increasePenaltyForMissedMedicineReservation() {
        List<MedicineReservation> medicineReservations = medicineReservationRepository.getAllMedicineReservationsWhoseDateIsInThePast();
        for (MedicineReservation medicineReservation: medicineReservations) {
            medicineReservation.setStatusOfMedicineReservation(StatusOfMedicineReservation.MISSED);
            Patient patient = medicineReservation.getPatientForMedicineReservation();
            patient.setPenalty(patient.getPenalty() + 1);
            PharmacyStorageItem pharmacyStorageItem = pharmacyStorageItemRepository.getSelectedMedicineFromPharmacyStorage(medicineReservation.getReservedMedicine().getId(),medicineReservation.getPharmacyForMedicineReservation().getId());
            pharmacyStorageItem.setMedicineAmount(pharmacyStorageItem.getMedicineAmount() + 1);
            pharmacyStorageItemRepository.save(pharmacyStorageItem);
            medicineReservationRepository.save(medicineReservation);
            patientRepository.save(patient);

        }
    }

    @Transactional
    @Override
    public void increasePenaltyForMissedMedicineReservationTest(Long milliseconds) {
        List<MedicineReservation> medicineReservations = medicineReservationRepository.getAllMedicineReservationsWhoseDateIsInThePast();
        for (MedicineReservation medicineReservation: medicineReservations) {
            medicineReservation.setStatusOfMedicineReservation(StatusOfMedicineReservation.MISSED);
            Patient patient = medicineReservation.getPatientForMedicineReservation();
            patient.setPenalty(patient.getPenalty() + 1);
            PharmacyStorageItem pharmacyStorageItem = pharmacyStorageItemRepository.getSelectedMedicineFromPharmacyStorage(medicineReservation.getReservedMedicine().getId(),medicineReservation.getPharmacyForMedicineReservation().getId());
            pharmacyStorageItem.setMedicineAmount(pharmacyStorageItem.getMedicineAmount() + 1);
            try { Thread.sleep(milliseconds); } catch (InterruptedException e) {}
            pharmacyStorageItemRepository.save(pharmacyStorageItem);
            medicineReservationRepository.save(medicineReservation);
            patientRepository.save(patient);

        }
    }

}
