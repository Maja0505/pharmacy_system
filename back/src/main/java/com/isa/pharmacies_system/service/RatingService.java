package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.DTO.RatingMedicineDTO;
import com.isa.pharmacies_system.DTO.RatingPharmacyDTO;
import com.isa.pharmacies_system.DTO.RatingStaffDTO;
import com.isa.pharmacies_system.converter.RatingConverter;
import com.isa.pharmacies_system.domain.medicine.Medicine;
import com.isa.pharmacies_system.domain.medicine.StatusOfMedicineReservation;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.domain.rating.Rating;
import com.isa.pharmacies_system.domain.rating.RatingScale;
import com.isa.pharmacies_system.domain.rating.TypeOfRating;
import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
import com.isa.pharmacies_system.domain.schedule.StatusOfAppointment;
import com.isa.pharmacies_system.domain.user.Dermatologist;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.domain.user.Pharmacist;
import com.isa.pharmacies_system.repository.*;
import com.isa.pharmacies_system.service.iService.IRatingService;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class RatingService implements IRatingService {

    private RatingConverter ratingConverter;
    private IRatingRepository ratingRepository;
    private IPatientRepository patientRepository;
    private IDermatologistRepository dermatologistRepository;
    private IPharmacistRepository pharmacistRepository;
    private IMedicineRepository medicineRepository;
    private IPharmacyRepository pharmacyRepository;

    public RatingService(IPatientRepository patientRepository, IDermatologistRepository dermatologistRepository, IRatingRepository ratingRepository, IPatientRepository patientRepository1, IPharmacistRepository pharmacistRepository, IMedicineRepository medicineRepository, IPharmacyRepository pharmacyRepository) {
        this.ratingRepository = ratingRepository;
        this.patientRepository = patientRepository;
        this.dermatologistRepository = dermatologistRepository;
        this.pharmacistRepository = pharmacistRepository;
        this.medicineRepository = medicineRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.ratingConverter = new RatingConverter(dermatologistRepository, patientRepository, pharmacistRepository, medicineRepository, pharmacyRepository);
    }

    //#1
    @Override
    public Boolean setStaffRating(RatingStaffDTO ratingStaffDTO) {
        if (didPatientHaveAppointmentByStaff(ratingStaffDTO)) {
            Rating rating = getStaffRatingInPatientRatings(ratingStaffDTO);
            if (rating != null) {
                rating.setGrade(ratingStaffDTO.getGrade());
                ratingRepository.save(rating);
                return true;
            }
            if (ratingStaffDTO.getTypeOfRating().equals(TypeOfRating.Dermatologist_rating)) {
                Dermatologist dermatologist = dermatologistRepository.findById(ratingStaffDTO.getStaffId()).orElse(null);
                if(dermatologist!= null){
                    Integer countOfRating = dermatologist.getDermatologistRatings().size();
                    dermatologist.setDermatologistAverageRating(((dermatologist.getDermatologistAverageRating()*countOfRating) + (convertRatingScaleToNumber(ratingStaffDTO.getGrade())))/(countOfRating + 1));
                    ratingRepository.save(ratingConverter.convertRatingDermatologistDTOToDermatologistRating(ratingStaffDTO));
                    dermatologistRepository.save(dermatologist);
                    return true;
                }

            } else if(ratingStaffDTO.getTypeOfRating().equals(TypeOfRating.Pharmacist_rating)) {
                Pharmacist pharmacist = pharmacistRepository.findById(ratingStaffDTO.getStaffId()).orElse(null);
                if(pharmacist != null){
                    Integer countOfRating = pharmacist.getPharmacistAppointments().size();
                    pharmacist.setPharmacistAverageRating((((pharmacist.getPharmacistAverageRating()*countOfRating) + (convertRatingScaleToNumber(ratingStaffDTO.getGrade())))/(countOfRating + 1)));
                    ratingRepository.save(ratingConverter.convertRatingPharmacistDTOToPharmacistRating(ratingStaffDTO));
                    pharmacistRepository.save(pharmacist);
                    return true;
                }

            }

        }
        return false;

    }

    private Integer convertRatingScaleToNumber(RatingScale scale){
        switch (scale){
            case One: return 1;
            case Two: return 2;
            case Three:return 3;
            case Four:return 4;
            case Five:return 5;
        }
        return 0;
    }

    //#1
    private Rating getStaffRatingInPatientRatings(RatingStaffDTO ratingStaffDTO) {
        if (ratingStaffDTO.getTypeOfRating().equals(TypeOfRating.Dermatologist_rating))
            return ratingRepository.getRatingForSelectedDermatologistAndPatient(ratingStaffDTO.getPatientId(), ratingStaffDTO.getStaffId());
        if (ratingStaffDTO.getTypeOfRating().equals(TypeOfRating.Pharmacist_rating))
            return ratingRepository.getRatingForSelectedPharmacistAndPatient(ratingStaffDTO.getPatientId(), ratingStaffDTO.getStaffId());
        return null;
    }


    //#1
    private Boolean didPatientHaveAppointmentByStaff(RatingStaffDTO ratingStaffDTO) {
        Patient patient = patientRepository.findById(ratingStaffDTO.getPatientId()).orElse(null);
        if(patient != null){
            if(ratingStaffDTO.getTypeOfRating().equals(TypeOfRating.Dermatologist_rating))
                return patient.getDermatologistAppointment().stream().filter(dermatologistAppointment -> dermatologistAppointment.getDermatologistForAppointment().getId()==ratingStaffDTO.getStaffId() && dermatologistAppointment.getStatusOfAppointment().equals(StatusOfAppointment.Expired)).count() > 0;
            if(ratingStaffDTO.getTypeOfRating().equals(TypeOfRating.Pharmacist_rating)){
                Set<PharmacistAppointment> ratingSet = patient.getPharmacistAppointments();
                return patient.getPharmacistAppointments().stream().filter(pharmacistAppointment -> pharmacistAppointment.getPharmacistForAppointment().getId()==ratingStaffDTO.getStaffId() && pharmacistAppointment.getStatusOfAppointment().equals(StatusOfAppointment.Expired)).count() > 0;
            }
        }
        return false;
    }

    //#1
    @Override
    public Boolean setMedicineRating(RatingMedicineDTO ratingMedicineDTO){
        if(didPatientReserveSelectedMedicine(ratingMedicineDTO) || doesPatientHaveSelectedMedicineInEPrescription(ratingMedicineDTO)){
            Rating rating = getMedicineRatingFromPatientRatings(ratingMedicineDTO);
            if(rating != null){
                rating.setGrade(ratingMedicineDTO.getGrade());
                ratingRepository.save(rating);
                return true;
            }
            Medicine medicine = medicineRepository.findById(ratingMedicineDTO.getMedicineId()).orElse(null);
            if(medicine != null){
                Integer countOfRating = medicine.getMedicineRatings().size();
                medicine.setMedicineAverageRating(((medicine.getMedicineAverageRating()*countOfRating) + (convertRatingScaleToNumber(ratingMedicineDTO.getGrade())))/(countOfRating + 1));
                ratingRepository.save(ratingConverter.convertRatingMedicineDTOToRatingMedicine(ratingMedicineDTO));
                medicineRepository.save(medicine);
                return true;
            }


        }
        return false;
    }

    //#1
    private boolean doesPatientHaveSelectedMedicineInEPrescription(RatingMedicineDTO ratingMedicineDTO) {
        Patient patient = patientRepository.findById(ratingMedicineDTO.getPatientId()).orElse(null);
        if(patient != null){
            return patient.getPatientEPrescriptions().stream().filter(ePrescription ->
                    ePrescription.getePrescriptionItems().stream().filter(ePrescriptionItem -> ePrescriptionItem.getMedicineItem().getId() == ratingMedicineDTO.getMedicineId()).count()>0).count()>0;
        }
        return false;
    }

    //#1
    private boolean didPatientReserveSelectedMedicine(RatingMedicineDTO ratingMedicineDTO) {
        Patient patient = patientRepository.findById(ratingMedicineDTO.getPatientId()).orElse(null);
        if(patient != null){
            return patient.getPatientMedicineReservations().stream().filter(medicineReservation -> medicineReservation.getReservedMedicine().getId() == ratingMedicineDTO.getMedicineId() && medicineReservation.getStatusOfMedicineReservation().equals(StatusOfMedicineReservation.FINISHED)).count()>0;
        }
        return false;
    }

    //#1
    private Rating getMedicineRatingFromPatientRatings(RatingMedicineDTO ratingMedicineDTO) {
        if (ratingMedicineDTO.getTypeOfRating().equals(TypeOfRating.Medicine_rating))
            return ratingRepository.getRatingForSelectedMedicineAndPatient(ratingMedicineDTO.getPatientId(), ratingMedicineDTO.getMedicineId());
        return null;
    }

    //#1
    @Override
    public Boolean setPharmacyRating(RatingPharmacyDTO ratingPharmacyDTO){
        if(didPatientHaveAppointmentInSelectedPharmacy(ratingPharmacyDTO) || doesPatientHaveMedicineReservationOrERecipeInSelectedPharmacy(ratingPharmacyDTO)){
            Rating rating = getPharmacyRatingFromPharmacyRatings(ratingPharmacyDTO);
            if(rating != null){
                rating.setGrade(ratingPharmacyDTO.getGrade());
                ratingRepository.save(rating);
                return true;
            }
            Pharmacy pharmacy = pharmacyRepository.findById(ratingPharmacyDTO.getPharmacyId()).orElse(null);
            if(pharmacy != null){
                Integer countOfRating = pharmacy.getPharmacyRatings().size();
                pharmacy.setPharmacyAverageRating(((pharmacy.getPharmacyAverageRating()*countOfRating) + (convertRatingScaleToNumber(ratingPharmacyDTO.getGrade())))/(countOfRating + 1));
                ratingRepository.save(ratingConverter.convertRatingPharmacyDTOToPharmacyRating(ratingPharmacyDTO));
                pharmacyRepository.save(pharmacy);
                return true;
            }


        }
        return false;
    }

    //#1
    private Rating getPharmacyRatingFromPharmacyRatings(RatingPharmacyDTO ratingPharmacyDTO) {
        if (ratingPharmacyDTO.getTypeOfRating().equals(TypeOfRating.Pharmacy_rating))
            return ratingRepository.getRatingForSelectedPharmacyAndPatient(ratingPharmacyDTO.getPatientId(), ratingPharmacyDTO.getPatientId());
        return null;
    }

    //#1
    private boolean doesPatientHaveMedicineReservationOrERecipeInSelectedPharmacy(RatingPharmacyDTO ratingPharmacyDTO) {
        Patient patient = patientRepository.findById(ratingPharmacyDTO.getPatientId()).orElse(null);
        if(patient != null){
            return patient.getPatientMedicineReservations().stream().filter(medicineReservation -> medicineReservation.getPharmacyForMedicineReservation().getId() == ratingPharmacyDTO.getPharmacyId() && medicineReservation.getStatusOfMedicineReservation().equals(StatusOfMedicineReservation.FINISHED)).count()>0;
        }
        return false;
    }

    //#1
    private boolean didPatientHaveAppointmentInSelectedPharmacy(RatingPharmacyDTO ratingPharmacyDTO) {
        Patient patient = patientRepository.findById(ratingPharmacyDTO.getPatientId()).orElse(null);
        if(patient != null){
            if(ratingPharmacyDTO.getTypeOfRating().equals(TypeOfRating.Pharmacy_rating))
                return patient.getDermatologistAppointment().stream().filter(dermatologistAppointment -> dermatologistAppointment.getPharmacyForDermatologistAppointment().getId() == ratingPharmacyDTO.getPharmacyId() && dermatologistAppointment.getStatusOfAppointment().equals(StatusOfAppointment.Expired)).count() > 0
                        || patient.getPharmacistAppointments().stream().filter(pharmacistAppointment -> pharmacistAppointment.getPharmacistForAppointment().getPharmacyForPharmacist().getId() == ratingPharmacyDTO.getPharmacyId() && pharmacistAppointment.getStatusOfAppointment().equals(StatusOfAppointment.Expired)).count() > 0;
        }
        return false;
    }


}
