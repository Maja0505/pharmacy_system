package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.converter.MedicineConverter;
import com.isa.pharmacies_system.converter.PharmacyConverter;
import com.isa.pharmacies_system.converter.UserConverter;
import com.isa.pharmacies_system.domain.medicine.Medicine;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.repository.IMedicineRepository;
import com.isa.pharmacies_system.repository.IPatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static com.isa.pharmacies_system.prototype.ProtoClass.protoMedicine;
import static com.isa.pharmacies_system.prototype.ProtoClass.protoPatient;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class PatientServiceTest {

    private IPatientRepository patientRepository;
    private IMedicineRepository medicineRepository;
    private ConfirmationTokenService confirmationTokenService;
    private EmailService emailService;
    private PatientService patientService;

    @BeforeEach
    void setUp(){
        patientRepository = mock(IPatientRepository.class);
        medicineRepository = mock(IMedicineRepository.class);
        confirmationTokenService = mock(ConfirmationTokenService.class);
        emailService = mock(EmailService.class);
        patientService = new PatientService(patientRepository,medicineRepository,confirmationTokenService,emailService);
    }


    @Test
    @Transactional
    @Rollback()
    void addMedicineAllergies() {
        Patient patient = protoPatient(new Patient());
        Medicine medicine = protoMedicine(new Medicine());
        medicine.setId(2l);

        when(patientRepository.findById(1l)).thenReturn(java.util.Optional.of(patient));
        when(medicineRepository.findById(2l)).thenReturn(java.util.Optional.of(medicine));

        Boolean retVal = patientService.addMedicineAllergies(1l,2l);

        assertThat(retVal).isEqualTo(true);
        assertThat(patient).isNotNull();
        assertThat(medicine).isNotNull();
        verify(patientRepository,times(1)).save(patient);
    }


}