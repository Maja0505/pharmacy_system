package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.PatientAppointmentInfoDTO;
import com.isa.pharmacies_system.service.iService.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("api/appointment")
public class AppointmentController {

    private IAppointmentService appointmentService;

    @Autowired
    public AppointmentController(IAppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PutMapping(value = "/sortByPatientFirstName/{asc}",consumes = "application/json")
    public ResponseEntity<List<PatientAppointmentInfoDTO>> getSortedPastAppointmentByPatientFistName(@RequestBody List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList, @PathVariable String asc){
        try {
            if(asc.equals("asc")){
                return new ResponseEntity<>(appointmentService.sortByPatientFirstName(patientAppointmentInfoDTOList,true), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(appointmentService.sortByPatientFirstName(patientAppointmentInfoDTOList,false),HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(value = "/sortByPatientLastName/{asc}",consumes = "application/json")
    public ResponseEntity<List<PatientAppointmentInfoDTO>> getSortedPastAppointmentByPatientLastName(@RequestBody List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList, @PathVariable String asc){
        try {
            if(asc.equals("asc")){
                return new ResponseEntity<>(appointmentService.sortByPatientLastName(patientAppointmentInfoDTOList,true),HttpStatus.OK);
            }else{
                return new ResponseEntity<>(appointmentService.sortByPatientLastName(patientAppointmentInfoDTOList,false),HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(value = "/sortByPatientEmail/{asc}",consumes = "application/json")
    public ResponseEntity<List<PatientAppointmentInfoDTO>> getSortedPastAppointmentByPatientEmail(@RequestBody List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList, @PathVariable String asc){
        try {
            if(asc.equals("asc")){
                return new ResponseEntity<>(appointmentService.sortByPatientEmail(patientAppointmentInfoDTOList,true),HttpStatus.OK);
            }else{
                return new ResponseEntity<>(appointmentService.sortByPatientEmail(patientAppointmentInfoDTOList,false),HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(value = "/sortByAppointmentStartTime/{asc}",consumes = "application/json")
    public ResponseEntity<List<PatientAppointmentInfoDTO>> getSortedPastAppointmentByAppointmentStartTime(@RequestBody List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList, @PathVariable String asc){
        try {
            if(asc.equals("asc")){
                return new ResponseEntity<>(appointmentService.sortByAppointmentStartTime(patientAppointmentInfoDTOList,true),HttpStatus.OK);
            }else{
                return new ResponseEntity<>(appointmentService.sortByAppointmentStartTime(patientAppointmentInfoDTOList,false),HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(value = "/sortByAppointmentPrice/{asc}",consumes = "application/json")
    public ResponseEntity<List<PatientAppointmentInfoDTO>> getSortedPastAppointmentByAppointmentPrice(@RequestBody List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList, @PathVariable String asc){
        try {
            if(asc.equals("asc")){
                return new ResponseEntity<>(appointmentService.sortByAppointmentPrice(patientAppointmentInfoDTOList,true),HttpStatus.OK);
            }else{
                return new ResponseEntity<>(appointmentService.sortByAppointmentPrice(patientAppointmentInfoDTOList,false),HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

    }


}
