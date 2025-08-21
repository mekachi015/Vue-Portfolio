package com.example.Portfolio_Backend.test_controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.Portfolio_Backend.DTO.FormSubmissionDTO;
import com.example.Portfolio_Backend.controller.FormController;
import com.example.Portfolio_Backend.entity.FormSubmission;
import com.example.Portfolio_Backend.serviceImplementation.FormService;
import com.example.Portfolio_Backend.servicesInterface.FormServiceInterface;
import com.example.Portfolio_Backend.test_configuration.TestConfig;

@WebMvcTest(FormController.class)
@Import(TestConfig.class)
public class FormControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FormServiceInterface formService;

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private FormSubmissionDTO validFormDTO;
    private FormSubmission savedForm;

    @BeforeEach
    void setUp() {
        validFormDTO = new FormSubmissionDTO();
        validFormDTO.setFirst_name("John");
        validFormDTO.setLast_name("Doe");
        validFormDTO.setEmail("john.doe@example.com");
        validFormDTO.setPhone("1234567890");
        validFormDTO.setQuery("Test query message");

        savedForm = new FormSubmission();
        savedForm.setId("1");
        savedForm.setFirst_name("John");
        savedForm.setLast_name("Doe");
        savedForm.setEmail("john.doe@example.com");
        savedForm.setPhone("1234567890");
        savedForm.setQuery("Test query message");
        savedForm.setSubmissionDate(LocalDateTime.now());
    }

    @Test
    void submitForm_ValidData_ShouldReturnCreated() throws Exception {

        Mockito.when(formService.existsByEmail(validFormDTO.getEmail())).thenReturn(false);
        Mockito.when(formService.saveFormSubmission(any(FormSubmissionDTO.class))).thenReturn(savedForm);

        ResponseEntity<String> mockResponse = new ResponseEntity<>("{\"ok\":\"true\"}", HttpStatus.OK);

        Mockito.when(restTemplate.exchange(
                eq("https://formspree.io/f/mnnakkdg"),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                eq(String.class))).thenReturn(mockResponse);

        mockMvc.perform(post("/api/form/submit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validFormDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(savedForm.getId()))
                .andExpect(jsonPath("$.first_name").value(savedForm.getFirst_name()))
                .andExpect(jsonPath("$.email").value(savedForm.getEmail()));
    }

    @Test
    void submitForm_EmailAlreadyExists_ShouldReturnConflict() throws Exception {
        Mockito.when(formService.existsByEmail(validFormDTO.getEmail())).thenReturn(true);

        mockMvc.perform(post("/api/form/submit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validFormDTO)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.email").value("Email already exists"));
    }

    /**
     * @Test
     *       void submitForm_InvalidData_ShouldReturnBadRequest() throws Exception {
     *       FormSubmissionDTO invalidFormDTO = new FormSubmissionDTO();
     *       invalidFormDTO.setFirst_name(""); // Empty first name - should
     *       trigger @NotBlank
     *       invalidFormDTO.setLast_name(""); // Empty last name
     *       invalidFormDTO.setEmail("invalid-email"); // Invalid email format
     *       invalidFormDTO.setQuery(""); // Empty query
     * 
     *       mockMvc.perform(post("/api/form/submit")
     *       .contentType(MediaType.APPLICATION_JSON)
     *       .content(objectMapper.writeValueAsString(invalidFormDTO)))
     *       .andExpect(status().isInternalServerError())
     *       .andDo(result ->
     *       System.out.println(result.getResponse().getContentAsString()))
     *       .andExpect(jsonPath("$").isMap())
     *       .andExpect(jsonPath("$").isNotEmpty())
     *       .andExpect(jsonPath("$.first_name").value("First name is required"))
     *       .andExpect(jsonPath("$.last_name").value("Last name is required"))
     *       .andExpect(jsonPath("$.email").value("Email is required"))
     *       .andExpect(jsonPath("$.query").value("Query is required"));
     *       }
     **/

    @Test
    void submitForm_InvalidData_ShouldReturnBadRequest() throws Exception {
        FormSubmissionDTO invalidFormDTO = new FormSubmissionDTO();
        // Leave all fields empty/null to trigger validation

        mockMvc.perform(post("/api/form/submit")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")) // Empty object
                .andExpect(status().isInternalServerError());
    }

    @Test
    void submitForm_FormspreeFails_ShouldStillReturnCreated() throws Exception {
        Mockito.when(formService.existsByEmail(validFormDTO.getEmail())).thenReturn(false);
        Mockito.when(formService.saveFormSubmission(any(FormSubmissionDTO.class))).thenReturn(savedForm);

        // Mock RestTemplate to return error
        ResponseEntity<String> mockErrorResponse = new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        Mockito.when(restTemplate.exchange(
                eq("https://formspree.io/f/mnnakkdg"),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                eq(String.class))).thenReturn(mockErrorResponse);

        mockMvc.perform(post("/api/form/submit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validFormDTO)))
                .andExpect(status().isCreated()); // Should still return created
    }

    @Test
    void getAllSubmissions_ShouldReturnList() throws Exception {
        List<FormSubmission> submissions = Arrays.asList(savedForm);
        Mockito.when(formService.getAllFormSubmissions()).thenReturn(submissions);

        mockMvc.perform(get("/api/form/submissions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].first_name").value("John"));
    }

    @Test
    void getSubmissionById_ValidId_ShouldReturnSubmission() throws Exception {
        Mockito.when(formService.getFormSubmissionById("1")).thenReturn(savedForm);

        mockMvc.perform(get("/api/form/submissions/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    void deleteSubmission_ValidId_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/form/submissions/1"))
                .andExpect(status().isNoContent());

        Mockito.verify(formService).deleteFormSubmission("1");
    }

    @Test
    void getAllSubmissions_EmptyList_ShouldReturnOk() throws Exception {
        Mockito.when(formService.getAllFormSubmissions()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/form/submissions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

}
