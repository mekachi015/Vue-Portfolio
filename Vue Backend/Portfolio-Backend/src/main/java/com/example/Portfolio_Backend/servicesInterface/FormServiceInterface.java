package com.example.Portfolio_Backend.servicesInterface;

import com.example.Portfolio_Backend.DTO.FormSubmissionDTO;
import com.example.Portfolio_Backend.entity.FormSubmission;
import java.util.List;

public interface FormServiceInterface {
    FormSubmission saveFormSubmission(FormSubmissionDTO formSubmissionDTO);

    List<FormSubmission> getAllFormSubmissions();
    FormSubmission getFormSubmissionById(String id);
    void deleteFormSubmission(String id);
    boolean existsByEmail(String email);
}
