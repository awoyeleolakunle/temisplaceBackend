package com.dansaki.com.temisplacebackend.services.user;

import com.dansaki.com.temisplacebackend.dtos.request.RegistrationRequest;
import com.dansaki.com.temisplacebackend.dtos.request.UserProfileUpdateRequest;
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
class UserProfileUpdateRequestServiceImpTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testThatAUserProfileCanBeUpdated() throws Exception {

        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setEmailAddress("emailAddress");
        registrationRequest.setCountry("United Kingdom");
        registrationRequest.setCity("London");
        registrationRequest.setPhoneNumber("+4325345475768");
        registrationRequest.setPassword("12345");


        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        UserProfileUpdateRequest userProfileUpdateRequest = new UserProfileUpdateRequest();
        userProfileUpdateRequest.setEmailAddress("emailAddress");
        userProfileUpdateRequest.setFirstName("updatedFirstName");
        userProfileUpdateRequest.setLastName("updateLastName");
        userProfileUpdateRequest.setUserStatus("SUSPEND");
        userProfileUpdateRequest.setRoles("UNIT");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/temisplace/updateUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userProfileUpdateRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}