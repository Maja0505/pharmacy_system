package com.isa.pharmacies_system.service;

import static com.isa.pharmacies_system.prototype.ProtoClass.protoDermatologistAppointment;
import static com.isa.pharmacies_system.prototype.ProtoClass.protoDermatologistAppointmentOpen;
import static com.isa.pharmacies_system.prototype.ProtoClass.protoPatient;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.schedule.StatusOfAppointment;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.repository.IDermatologistAppointmentRepository;
import com.isa.pharmacies_system.repository.IDermatologistRepository;
import com.isa.pharmacies_system.repository.IPatientRepository;
import com.isa.pharmacies_system.repository.IPharmacyRepository;
import com.isa.pharmacies_system.repository.IPriceListRepository;
import com.isa.pharmacies_system.repository.ISystemLoyaltyRepository;
import com.isa.pharmacies_system.service.iService.ISystemLoyaltyService;

@RunWith(SpringRunner.class)
@SpringBootTest
class DermatologistAppointmentServiceTest {

    private IDermatologistAppointmentRepository dermatologistAppointmentRepository;
    private IPatientRepository patientRepository;
    private IDermatologistRepository dermatologistRepository;
    private IPharmacyRepository pharmacyRepository;
    private IPriceListRepository priceListRepository;
    private ISystemLoyaltyService systemLoyaltyService;
    private DermatologistAppointmentService dermatologistAppointmentService;

    @BeforeEach
    void setUp(){
        dermatologistAppointmentRepository = mock(IDermatologistAppointmentRepository.class);
        patientRepository = mock(IPatientRepository.class);
        dermatologistRepository = mock(IDermatologistRepository.class);
        pharmacyRepository = mock(IPharmacyRepository.class);
        priceListRepository = mock(IPriceListRepository.class);
        systemLoyaltyService = mock(ISystemLoyaltyService.class);
        dermatologistAppointmentService = new DermatologistAppointmentService(dermatologistAppointmentRepository,patientRepository,dermatologistRepository,pharmacyRepository,priceListRepository, systemLoyaltyService);
    }

    @Test
    @Transactional
    @Rollback()
    void changeDermatologistAppointmentStatusToMissed() {
        DermatologistAppointment da = protoDermatologistAppointment(new DermatologistAppointment());
        when(dermatologistAppointmentRepository.save(da)).thenReturn(da);

        Boolean retVal = dermatologistAppointmentService.changeDermatologistAppointmentStatusToMissed(da);

        assertThat(da).isNotNull();
        assertThat(retVal).isEqualTo(true);
        verify(dermatologistAppointmentRepository,times(1)).save(da);
        assertThat(da.getStatusOfAppointment()).isEqualTo(StatusOfAppointment.Missed);
        assertThat(da.getPatientWithDermatologistAppointment().getPenalty()).isEqualTo(1);
    }

    @Test
    @Transactional
    @Rollback()
    void bookDermatologistAppointment(){
        DermatologistAppointment dermatologistAppointment = protoDermatologistAppointmentOpen(new DermatologistAppointment());
        Patient patient = protoPatient(new Patient());
        when(dermatologistAppointmentRepository.findById(1l)).thenReturn(java.util.Optional.of(dermatologistAppointment));
        when(patientRepository.findById(1l)).thenReturn(java.util.Optional.of(patient));

        assertThat(patient.getPenalty()).isLessThan(3);
        assertThat(dermatologistAppointment.getStatusOfAppointment()).isEqualTo(StatusOfAppointment.Open);
        assertThat(dermatologistAppointment.getPatientWithDermatologistAppointment()).isNull();

        Boolean retVal = dermatologistAppointmentService.bookDermatologistAppointment(patient.getId(),dermatologistAppointment.getId());

        assertThat(retVal).isEqualTo(true);
        assertThat(dermatologistAppointment.getPatientWithDermatologistAppointment()).isEqualTo(patient);
        assertThat(dermatologistAppointment.getStatusOfAppointment()).isEqualTo(StatusOfAppointment.Reserved);
        verify(dermatologistAppointmentRepository,times(1)).save(dermatologistAppointment);
    }
}