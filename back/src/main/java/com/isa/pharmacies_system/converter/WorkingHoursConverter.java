package com.isa.pharmacies_system.converter;

import com.isa.pharmacies_system.DTO.WorkingHoursDTO;
import com.isa.pharmacies_system.domain.schedule.WorkingHours;
import com.isa.pharmacies_system.domain.user.Users;

import java.util.ArrayList;
import java.util.List;

public class WorkingHoursConverter {

    public WorkingHoursConverter() {
    }

    //Nemanja
    public List<WorkingHoursDTO> convertWorkingHoursListToWorkingHoursDTOList(List<WorkingHours> workingHoursList){
        List<WorkingHoursDTO> workingHoursDTOList = new ArrayList<>();
        for (WorkingHours workingHour:workingHoursList
             ) {
            workingHoursDTOList.add(convertWorkingHourToWorkingHourDTO(workingHour));
        }
        return workingHoursDTOList;
    }

    //Nemanja
    private WorkingHoursDTO convertWorkingHourToWorkingHourDTO(WorkingHours workingHour){
        WorkingHoursDTO workingHourDTO = new WorkingHoursDTO();
        workingHourDTO.setWorkingStartTime(workingHour.getWorkingStartTime());
        workingHourDTO.setWorkingEndTime(workingHour.getWorkingEndTime());
        workingHourDTO.setId(workingHour.getId());
        workingHourDTO.setStaffId(workingHour.getWorkerSchedule().getDermatologist().getId());
        return workingHourDTO;
    }

}
