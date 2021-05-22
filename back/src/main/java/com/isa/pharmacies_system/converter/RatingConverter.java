package com.isa.pharmacies_system.converter;

import com.isa.pharmacies_system.DTO.RatingMedicineDTO;
import com.isa.pharmacies_system.DTO.RatingPharmacyDTO;
import com.isa.pharmacies_system.DTO.RatingStaffDTO;
import com.isa.pharmacies_system.domain.rating.DermatologistRating;
import com.isa.pharmacies_system.domain.rating.MedicineRating;
import com.isa.pharmacies_system.domain.rating.PharmacistRating;
import com.isa.pharmacies_system.domain.rating.PharmacyRating;
import com.isa.pharmacies_system.repository.*;

public class RatingConverter {

    private IDermatologistRepository dermatologistRepository;
    private IPatientRepository patientRepository;
    private IPharmacistRepository pharmacistRepository;
    private IMedicineRepository medicineRepository;
    private IPharmacyRepository pharmacyRepository;

    public RatingConverter(IDermatologistRepository dermatologistRepository, IPatientRepository patientRepository, IPharmacistRepository pharmacistRepository, IMedicineRepository medicineRepository, IPharmacyRepository pharmacyRepository) {
        this.dermatologistRepository = dermatologistRepository;
        this.patientRepository = patientRepository;
        this.pharmacistRepository = pharmacistRepository;
        this.medicineRepository = medicineRepository;
        this.pharmacyRepository = pharmacyRepository;
    }

    public DermatologistRating convertRatingDermatologistDTOToDermatologistRating(RatingStaffDTO ratingStaffDTO){

        DermatologistRating dermatologistRating = new DermatologistRating();
        dermatologistRating.setDermatologistForRating(dermatologistRepository.findById(ratingStaffDTO.getStaffId()).orElse(null));
        dermatologistRating.setPatientWithRating(patientRepository.findById(ratingStaffDTO.getPatientId()).orElse(null));
        dermatologistRating.setGrade(ratingStaffDTO.getGrade());
        dermatologistRating.setTypeOfRating(ratingStaffDTO.getTypeOfRating());
        return dermatologistRating;
    }


    public PharmacistRating convertRatingPharmacistDTOToPharmacistRating(RatingStaffDTO ratingStaffDTO){

        PharmacistRating pharmacistRating = new PharmacistRating();
        pharmacistRating.setPharmacistForRating(pharmacistRepository.findById(ratingStaffDTO.getStaffId()).orElse(null));
        pharmacistRating.setPatientWithRating(patientRepository.findById(ratingStaffDTO.getPatientId()).orElse(null));
        pharmacistRating.setGrade(ratingStaffDTO.getGrade());
        pharmacistRating.setTypeOfRating(ratingStaffDTO.getTypeOfRating());
        return pharmacistRating;
    }

    public MedicineRating convertRatingMedicineDTOToRatingMedicine(RatingMedicineDTO ratingMedicineDTO) {

        MedicineRating medicineRating = new MedicineRating();
        medicineRating.setMedicineForRating(medicineRepository.findById(ratingMedicineDTO.getMedicineId()).orElse(null));
        medicineRating.setPatientWithRating(patientRepository.findById(ratingMedicineDTO.getPatientId()).orElse(null));
        medicineRating.setGrade(ratingMedicineDTO.getGrade());
        medicineRating.setTypeOfRating(ratingMedicineDTO.getTypeOfRating());
        return medicineRating;
    }

    public PharmacyRating convertRatingPharmacyDTOToPharmacyRating(RatingPharmacyDTO ratingPharmacyDTO) {

        PharmacyRating pharmacyRating = new PharmacyRating();
        pharmacyRating.setPharmacyForRating(pharmacyRepository.findById(ratingPharmacyDTO.getPharmacyId()).orElse(null));
        pharmacyRating.setPatientWithRating(patientRepository.findById(ratingPharmacyDTO.getPatientId()).orElse(null));
        pharmacyRating.setGrade(ratingPharmacyDTO.getGrade());
        pharmacyRating.setTypeOfRating(ratingPharmacyDTO.getTypeOfRating());
        return  pharmacyRating;
    }
}
