package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.PatientAppointmentInfoDTO;
import com.isa.pharmacies_system.util.TestUtil;
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

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class DermatologistAppointmentControllerTest {

    private static final String URL_PREFIX = "/api/dermatologistAppointment";
    private static final String URL_PREFIX_SORT = "/api/appointment";


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
    @WithMockUser(authorities = {"ROLE_DERMATOLOGIST"})
    void changeDermatologistAppointmentStatusToMissed() throws Exception {
        mockMvc.perform(put(URL_PREFIX + "/changeStatusToMissed/1")).andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback()
    @WithMockUser(authorities = {"ROLE_DERMATOLOGIST"})
    void getSortedPastAppointmentByPatientLastName() throws Exception {
        List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList = new ArrayList<>();
        PatientAppointmentInfoDTO p1 = new PatientAppointmentInfoDTO();
        p1.setPatientLastName("Antic");
        PatientAppointmentInfoDTO p2 = new PatientAppointmentInfoDTO();
        p2.setPatientLastName("Bajic");
        patientAppointmentInfoDTOList.add(p1);
        patientAppointmentInfoDTOList.add(p2);
        String json = TestUtil.json(patientAppointmentInfoDTOList);
        mockMvc.perform(put(URL_PREFIX_SORT + "/sortByPatientLastName/desc").contentType(contentType).content(json)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].patientLastName").value("Bajic"));
    }
}