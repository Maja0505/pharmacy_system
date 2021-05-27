package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.VacationRequestDTO;
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

import static com.isa.pharmacies_system.prototype.ProtoClass.protoVacationRequestDTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
class DermatologistVacationRequestControllerTest {

    private static final String URL_PREFIX = "/api/dermatologistVacationRequest";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

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
    void createDermatologistVacationRequest() throws Exception {
        VacationRequestDTO vacationRequestDTO = protoVacationRequestDTO(new VacationRequestDTO());
        String json = "{\"staffId\":\"" + vacationRequestDTO.getStaffId() + "\",\"typeOfVacation\":\"" + vacationRequestDTO.getTypeOfVacation() + "\",\"vacationRequestNotes\":\"" + vacationRequestDTO.getVacationRequestNotes() + "\",\"vacationStartDate\":\"" + vacationRequestDTO.getVacationStartDate() + "\",\"vacationEndDate\":\"" + vacationRequestDTO.getVacationEndDate() + "\"}";
        mockMvc.perform(post(URL_PREFIX + "/create").contentType(contentType).content(json)).andExpect(status().isCreated());
    }
}