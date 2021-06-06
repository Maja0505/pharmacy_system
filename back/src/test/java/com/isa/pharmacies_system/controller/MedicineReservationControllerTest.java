package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.MedicineReservationDTO;
import com.isa.pharmacies_system.DTO.MedicineReservationInfoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static com.isa.pharmacies_system.prototype.ProtoClass.protoMedicineReservationDTO;
import static com.isa.pharmacies_system.prototype.ProtoClass.protoMedicineReservationInfo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class MedicineReservationControllerTest {

    private static final String URL_PREFIX = "/api/medicineReservation";
    private static final String URL_PREFIX_MEDICINE = "/api/medicine";


    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Transactional
    @Rollback()
    @WithMockUser(authorities = {"ROLE_PATIENT"})
    void createMedicineReservation() throws Exception {
        MedicineReservationDTO m = protoMedicineReservationDTO(new MedicineReservationDTO());
        String json = "{\"dateOfTakingMedicine\":\"" + m.getDateOfTakingMedicine() +  "\"" + "}";
        System.out.println(json);
        this.mockMvc.perform(post(URL_PREFIX + "/create/2/2/1").contentType(contentType).content(json)).andExpect(status().isCreated());
    }

    @Test
    @Transactional
    @Rollback()
    @WithMockUser(authorities = {"ROLE_PATIENT"})
    void cancelMedicineReservation() throws Exception {
        MedicineReservationInfoDTO m = protoMedicineReservationInfo(new MedicineReservationInfoDTO());
        String json = "{\"reservationId\":\"" + m.getReservationId() +
                "\",\"medicineId\":\"" + m.getMedicineId() +
                "\",\"pharmacyId\":\"" + m.getPharmacyId() +
                "\",\"medicineName\":\"" + m.getMedicineName() +
                "\",\"pharmacyName\":\"" + m.getPharmacyName() +
                "\",\"takingDate\":\"" +  m.getTakingDate() +
                "\",\"statusOfMedicineReservation\":\"" + m.getStatusOfMedicineReservation() + "\"" +"}";
        this.mockMvc.perform(put(URL_PREFIX + "/cancel").contentType(contentType).content(json)).andExpect(status().isOk());
    }

    @Transactional
    @Rollback()
    @WithMockUser(authorities = {"ROLE_PATIENT"})
    @Test
    void getAllMedicinesShortVersion() throws Exception {
        mockMvc.perform(get(URL_PREFIX_MEDICINE + "/all/short")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[*].medicineId").value(hasItem(1)))
                .andExpect(jsonPath("$.[*].medicineName").value(hasItem("Bromic")));
    }
}