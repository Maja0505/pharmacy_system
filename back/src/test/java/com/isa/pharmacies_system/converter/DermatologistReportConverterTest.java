package com.isa.pharmacies_system.converter;

import com.isa.pharmacies_system.DTO.DermatologistReportDTO;
import com.isa.pharmacies_system.domain.report.DermatologistReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static com.isa.pharmacies_system.prototype.ProtoClass.protoDermatologistReport;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
class DermatologistReportConverterTest {
    
    private DermatologistReportConverter dermatologistReportConverter;

    @BeforeEach
    void setUp(){
        dermatologistReportConverter = new DermatologistReportConverter();
    }

    @Test
    void convertDermatologistReportToDermatologistReportDTO() {
        DermatologistReport dr = protoDermatologistReport(new DermatologistReport());
        DermatologistReportDTO dermatologistReportDTO = dermatologistReportConverter.convertDermatologistReportToDermatologistReportDTO(dr);

        assertThat(dermatologistReportDTO).isNotNull();
        assertThat(dermatologistReportDTO.getDermatologistId()).isEqualTo(dr.getId());
        assertThat(dermatologistReportDTO.getReportInfo()).isEqualTo(dr.getReportInfo());
        assertThat(dermatologistReportDTO.getDermatologistAppointmentEndTime()).isEqualTo(dr.getDermatologistAppointment().getDermatologistAppointmentEndTime());
        assertThat(dermatologistReportDTO.getDermatologistAppointmentStartTime()).isEqualTo(dr.getDermatologistAppointment().getDermatologistAppointmentStartTime());
        assertThat(dermatologistReportDTO.getPharmacyName()).isEqualTo(dr.getDermatologistAppointment().getPharmacyForDermatologistAppointment().getPharmacyName());
        assertThat(dermatologistReportDTO.getDermatologistFirstName()).isEqualTo(dr.getDermatologistAppointment().getDermatologistForAppointment().getFirstName());
        assertThat(dermatologistReportDTO.getDermatologistLastName()).isEqualTo(dr.getDermatologistAppointment().getDermatologistForAppointment().getLastName());

    }

    @Test
    void convertDermatologistReportToDermatologistReportDTOThrowsExceptionWhenDermatologistReportIsNull(){
        assertThrows(NullPointerException.class,
                () -> dermatologistReportConverter.convertDermatologistReportToDermatologistReportDTO(null));
    }


}