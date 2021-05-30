package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.DTO.AppointmentScheduleByStaffDTO;
import com.isa.pharmacies_system.DTO.PatientAppointmentInfoDTO;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.user.Patient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


public interface IDermatologistAppointmentService{

    DermatologistAppointment findOne(Long id);
    Boolean bookDermatologistAppointment(Long patientId, Long appointmentId);
    List<DermatologistAppointment> getOpenDermatologistAppointment(Long pharmacyId);
    List<DermatologistAppointment> getAllPastDermatologistAppointmentByDermatologist(Long id);
    List<DermatologistAppointment> getAllPastDermatologistAppointmentByDermatologistAndPharmacy(Long idDermatologist,Long idPharmacy);
    List<PatientAppointmentInfoDTO> sortByAppointmentEndTime(List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList, Boolean asc);
    Boolean cancelDermatologistAppointment(DermatologistAppointment dermatologistAppointment);
    List<DermatologistAppointment> getAllFutureOpenDermatologistAppointmentForDermatologistInPharmacy(Long dermatologistId,Long pharmacyId);
    List<DermatologistAppointment> findAllFutureReservedDermatologistAppointmentByDermatologistAndPharmacy(Long dermatologistId,Long pharmacyId);
    List<DermatologistAppointment> getAllMissedDermatologistAppointmentByDermatologistAndPharmacyId(Long dermatologistId, Long pharmacyId);
    List<DermatologistAppointment> getAllExpiredDermatologistAppointmentByDermatologistAndPharmacyId(Long dermatologistId, Long pharmacyId);
    List<DermatologistAppointment> getAllReservedDermatologistAppointmentByDermatologistAndPharmacyId(Long dermatologistId, Long pharmacyId);
    List<DermatologistAppointment> searchAllFutureReservedByPatientFirstAndLastName(Long dermatologistId,String firstName, String lastName);
    List<DermatologistAppointment> findAllFutureReservedDermatologistAppointmentByDermatologist(Long dermatologistId);
    Boolean bookDermatologistAppointmentByDermatologist(AppointmentScheduleByStaffDTO appointmentScheduleByStaffDTO,DermatologistAppointment dermatologistAppointment);
    Boolean changeDermatologistAppointmentStatusToMissed(DermatologistAppointment dermatologistAppointment);
    Boolean bookDermatologistAppointmentTest(Long patientId,Long appointmentId,Long milliseconds);
    void setMissedDermatologistAppointmentEveryDayOnRightStatusAndIncreasePenaltyForPatient();
}
