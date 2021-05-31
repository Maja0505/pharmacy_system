package com.isa.pharmacies_system.repository;

import com.isa.pharmacies_system.domain.rating.DermatologistRating;
import com.isa.pharmacies_system.domain.rating.Rating;
import com.isa.pharmacies_system.domain.rating.RatingScale;
import com.isa.pharmacies_system.domain.rating.TypeOfRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRatingRepository extends JpaRepository<Rating,Long> {

    @Query("select d from DermatologistRating d where d.patientWithRating.id = ?1 and d.dermatologistForRating.id = ?2")
    Rating getRatingForSelectedDermatologistAndPatient(Long patientId, Long dermatologistId);

    @Query("select d from PharmacistRating d where d.patientWithRating.id = ?1 and d.pharmacistForRating.id = ?2")
    Rating getRatingForSelectedPharmacistAndPatient(Long patientId, Long dermatologistId);

    @Query("select d from MedicineRating d where d.patientWithRating.id = ?1 and d.medicineForRating.id = ?2")
    Rating getRatingForSelectedMedicineAndPatient(Long patientId, Long medicineId);

    @Query("select d from PharmacyRating d where d.patientWithRating.id = ?1 and d.pharmacyForRating.id = ?2")
    Rating getRatingForSelectedPharmacyAndPatient(Long patientId, Long patientId1);

    @Query("select count(d) from DermatologistRating  d where  d.patientWithRating.id = ?1 and d.dermatologistForRating.id = ?2")
    Integer getAvgRatingForDermatologist(Long patientId, Long dermatologistId);
}
