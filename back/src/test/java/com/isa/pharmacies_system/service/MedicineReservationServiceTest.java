package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.domain.medicine.MedicineReservation;
import com.isa.pharmacies_system.domain.medicine.StatusOfMedicineReservation;
import com.isa.pharmacies_system.domain.storage.PharmacyStorageItem;
import com.isa.pharmacies_system.repository.IMedicineReservationRepository;
import com.isa.pharmacies_system.repository.IPharmacyStorageItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static com.isa.pharmacies_system.prototype.ProtoClass.*;
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

    @Test
    void createMedicineReservation(){
        PharmacyStorageItem item = protoPharmacyStorageItem(new PharmacyStorageItem());
        MedicineReservation medicineReservation = protoMedicineReservation(new MedicineReservation());
        when(pharmacyStorageItemRepository.getSelectedMedicineFromPharmacyStorage(1l,1l)).thenReturn(item);

        assertThat(item.getMedicineAmount()).isEqualTo(1);
        assertThat(medicineReservation.getPatientForMedicineReservation().getPenalty()).isLessThan(3);

        Boolean retVal = medicineReservationService.createMedicineReservation(medicineReservation);

        assertThat(retVal).isEqualTo(true);
        assertThat(item).isNotNull();
        assertThat(item.getMedicineAmount()).isEqualTo(0);
        verify(medicineReservationRepository,times(1)).save(medicineReservation);
        verify(pharmacyStorageItemRepository,times(1)).save(item);


    }

}