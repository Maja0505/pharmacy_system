package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.DTO.AppointmentScheduleByStaffDTO;
import com.isa.pharmacies_system.DTO.PatientAppointmentInfoDTO;
import com.isa.pharmacies_system.DTO.PharmacistAppointmentTimeDTO;
import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IPharmacistAppointmentService{

    PharmacistAppointment findOne(Long id);
    List<PharmacistAppointment> getAllPastPharmacistAppointmentByPharmacist(Long id);
    List<PatientAppointmentInfoDTO> sortByAppointmentDuration(List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList,Boolean asc);
    Boolean bookPharmacistAppointment(Long patientId, Long pharmacistId, PharmacistAppointmentTimeDTO timeDTO, Boolean isPatient);
    List<PharmacistAppointment> getFutureReservedAppointment(Long patientId);
    List<PharmacistAppointment> getAllMissedPharmacistAppointmentByPharmacist(Long pharmacistId);
    List<PharmacistAppointment> getAllExpiredPharmacistAppointmentByPharmacist(Long pharmacistId);
    List<PharmacistAppointment> getAllReservedPharmacistAppointmentByPharmacist(Long pharmacistId);
    List<PharmacistAppointment> getAllFutureReservedAppointmentByPharmacist(Long pharmacistId);
    List<PharmacistAppointment> searchAllFutureReservedByPatientFirstAndLastName(Long pharmacistId, String firstName, String lastName);
    Boolean cancelPharmacistAppointment(Long appointmentId);
    Boolean changePharmacistAppointmentStatusToMissed(PharmacistAppointment pharmacistAppointment);
    Boolean bookPharmacistAppointmentTest(Long patientId, Long pharmacistId, PharmacistAppointmentTimeDTO timeDTO,Boolean isPatient,Long milliseconds);
    void setMissedPharmacistAppointmentEveryDayOnRightStatusAndIncreasePenaltyForPatient();
}
