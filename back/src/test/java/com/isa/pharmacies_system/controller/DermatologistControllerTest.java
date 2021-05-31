package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.UserPasswordDTO;
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

import static com.isa.pharmacies_system.prototype.ProtoClass.protoUserPasswordDTO;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
class DermatologistControllerTest {

    private static final String URL_PREFIX = "/api/dermatologist";

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
    void changeDermatologistPassword() throws Exception {
        UserPasswordDTO userPasswordDTO = protoUserPasswordDTO(new UserPasswordDTO());
        String json = TestUtil.json(userPasswordDTO);
        mockMvc.perform(put(URL_PREFIX + "/changePassword").contentType(contentType).content(json)).andExpect(status().isOk());
    }



}