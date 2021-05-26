package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.domain.medicine.MedicineReservation;
import com.isa.pharmacies_system.domain.medicine.StatusOfMedicineReservation;
import com.isa.pharmacies_system.repository.IMedicineReservationRepository;
import com.isa.pharmacies_system.repository.IPharmacyStorageItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static com.isa.pharmacies_system.prototype.ProtoClass.protoMedicineReservations;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MedicineReservationServiceTest {

    private IMedicineReservationRepository medicineReservationRepository;
    private IPharmacyStorageItemRepository pharmacyStorageItemRepository;
    private MedicineReservationService medicineReservationService;

    @BeforeEach
    void setUp(){
        medicineReservationRepository = mock(IMedicineReservationRepository.class);
        pharmacyStorageItemRepository = mock(IPharmacyStorageItemRepository.class);
        medicineReservationService = new MedicineReservationService(medicineReservationRepository,pharmacyStorageItemRepository);
    }

    @Test
    void getMedicineReservationInPharmacy() {
        List<MedicineReservation> list = protoMedicineReservations(new MedicineReservation());
        when(medicineReservationRepository.findMedicineReservationByIdAndByPharmacy(1l,1l)).thenReturn(list);

        MedicineReservation medicineReservation = medicineReservationService.getMedicineReservationInPharmacy(1l,1l);

        assertThat(medicineReservation).isNotNull();
        assertThat(medicineReservation.getDateOfTakingMedicine()).isAfter(LocalDate.now().plusDays(1));
        verify(medicineReservationRepository,times(1)).findMedicineReservationByIdAndByPharmacy(1l,1l);
    }
}