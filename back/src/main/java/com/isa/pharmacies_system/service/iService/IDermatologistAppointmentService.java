package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.DTO.AppointmentScheduleByStaffDTO;
import com.isa.pharmacies_system.DTO.PatientAppointmentInfoDTO;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.user.Patient;
import org.springframework.data.domain.Page;

import java.util.List;


public interface IDermatologistAppointmentService{

    DermatologistAppointment findOne(Long id);
    Boolean bookDermatologistAppointment(Long patientId, Long appointmentId);
    List<DermatologistAppointment> getOpenDermatologistAppointment(Long pharmacyId);
    Page<DermatologistAppointment> getAllPastDermatologistAppointmentByDermatologist(Long id, int page);
    Page<DermatologistAppointment> getAllPastDermatologistAppointmentByDermatologistAndPharmacy(Long idDermatologist,Long idPharmacy, int page);
    List<PatientAppointmentInfoDTO> sortByAppointmentEndTime(List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList, Boolean asc);
    Boolean cancelDermatologistAppointment(DermatologistAppointment dermatologistAppointment);
    List<DermatologistAppointment> getAllFutureOpenDermatologistAppointmentForDermatologistInPharmacy(Long dermatologistId,Long pharmacyId);
    List<DermatologistAppointment> findAllFutureReservedDermatologistAppointmentByDermatologistAndPharmacy(Long dermatologistId,Long pharmacyId);
    Boolean bookDermatologistAppointmentByDermatologist(AppointmentScheduleByStaffDTO appointmentScheduleByStaffDTO,DermatologistAppointment dermatologistAppointment);
    Boolean changeDermatologistAppointmentStatusToMissed(DermatologistAppointment dermatologistAppointment);
}
