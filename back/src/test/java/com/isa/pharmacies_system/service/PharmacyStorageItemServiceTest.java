package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.domain.storage.PharmacyStorageItem;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.repository.IMedicineRepository;
import com.isa.pharmacies_system.repository.IMedicineRequestRepository;
import com.isa.pharmacies_system.repository.IPatientRepository;
import com.isa.pharmacies_system.repository.IPharmacyStorageItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.isa.pharmacies_system.prototype.ProtoClass.protoPatient;
import static com.isa.pharmacies_system.prototype.ProtoClass.protoPharmacyStorageItem;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PharmacyStorageItemServiceTest {

    private IPharmacyStorageItemRepository pharmacyStorageItemRepository;
    private IMedicineRepository medicineRepository;
    private IPatientRepository patientRepository;
    private IMedicineRequestRepository medicineRequestRepository;
    private PharmacyStorageItemService pharmacyStorageItemService;

    @BeforeEach
    void setUp(){
        pharmacyStorageItemRepository = mock(IPharmacyStorageItemRepository.class);
        medicineRepository = mock(IMedicineRepository.class);
        patientRepository = mock(IPatientRepository.class);
        medicineRequestRepository = mock(IMedicineRequestRepository.class);
        pharmacyStorageItemService = new PharmacyStorageItemService(pharmacyStorageItemRepository,medicineRepository,patientRepository,medicineRequestRepository);
    }


    @Test
    void checkHavePatientAllergiesOnMedicine() {
        PharmacyStorageItem item = protoPharmacyStorageItem(new PharmacyStorageItem());
        Patient patient = protoPatient(new Patient());

        when(pharmacyStorageItemRepository.findById(1l)).thenReturn(java.util.Optional.of(item));
        when(patientRepository.findById(1l)).thenReturn(java.util.Optional.of(patient));

        Boolean retVal = pharmacyStorageItemService.checkHavePatientAllergiesOnMedicine(1l,1l);

        assertThat(item).isNotNull();
        assertThat(patient).isNotNull();
        assertThat(retVal).isEqualTo(true);
        verify(pharmacyStorageItemRepository,times(1)).findById(1l);
        verify(patientRepository,times(1)).findById(1l);

    }
}