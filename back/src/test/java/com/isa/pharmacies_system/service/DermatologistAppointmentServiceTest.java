package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.schedule.StatusOfAppointment;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.isa.pharmacies_system.prototype.ProtoClass.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class DermatologistAppointmentServiceTest {

    private IDermatologistAppointmentRepository dermatologistAppointmentRepository;
    private IPatientRepository patientRepository;
    private IDermatologistRepository dermatologistRepository;
    private IPharmacyRepository pharmacyRepository;
    private IPriceListRepository priceListRepository;
    private DermatologistAppointmentService dermatologistAppointmentService;

    @BeforeEach
    void setUp(){
        dermatologistAppointmentRepository = mock(IDermatologistAppointmentRepository.class);
        patientRepository = mock(IPatientRepository.class);
        dermatologistRepository = mock(IDermatologistRepository.class);
        pharmacyRepository = mock(IPharmacyRepository.class);
        priceListRepository = mock(IPriceListRepository.class);
        dermatologistAppointmentService = new DermatologistAppointmentService(dermatologistAppointmentRepository,patientRepository,dermatologistRepository,pharmacyRepository,priceListRepository);
    }

    @Test
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