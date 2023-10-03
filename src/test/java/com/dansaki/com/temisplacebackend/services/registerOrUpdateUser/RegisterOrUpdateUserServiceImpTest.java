package com.dansaki.com.temisplacebackend.services.registerOrUpdateUser;

import com.dansaki.com.temisplacebackend.dtos.request.RegisterOrUpdateUserRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
class RegisterOrUpdateUserServiceImpTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void testThatAnAdminCanRegisterOrUpdateAUser() throws Exception {

        RegisterOrUpdateUserRequest registerOrUpdateUserRequest = new RegisterOrUpdateUserRequest();
        registerOrUpdateUserRequest.setEmailAddress("emailAddress");
        registerOrUpdateUserRequest.setPassword("password");
        registerOrUpdateUserRequest.setCity("Lagos");
        registerOrUpdateUserRequest.setFirstName("firstName");
        registerOrUpdateUserRequest.setLastName("lastName");
        registerOrUpdateUserRequest.setPostCode("10012345");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/registerOrUpdateUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerOrUpdateUserRequest)))
                .andExpect(MockMvcResultMatchers.status().is(200));



        RegisterOrUpdateUserRequest registerOrUpdateUserRequest1 = new RegisterOrUpdateUserRequest();
        registerOrUpdateUserRequest1.setEmailAddress("emailAddress");
        registerOrUpdateUserRequest1.setPassword("password");
        registerOrUpdateUserRequest1.setCity("Lagos");
        registerOrUpdateUserRequest1.setFirstName("firstName");
        registerOrUpdateUserRequest1.setLastName("lastName");
        registerOrUpdateUserRequest1.setPostCode("10012345");
       // registerOrUpdateUserRequest1.setUserStatus("ACTIVE");
        registerOrUpdateUserRequest1.setPostCode("1001101");
        registerOrUpdateUserRequest1.setPhoneNumber("+456345678");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/registerOrUpdateUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerOrUpdateUserRequest1)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}