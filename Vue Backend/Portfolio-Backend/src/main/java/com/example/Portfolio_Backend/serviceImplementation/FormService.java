package com.example.Portfolio_Backend.serviceImplementation;


import com.example.Portfolio_Backend.DTO.FormSubmissionDTO;
import com.example.Portfolio_Backend.entity.FormSubmission;
import com.example.Portfolio_Backend.repository.FormRepository;
import com.example.Portfolio_Backend.servicesInterface.FormServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FormService implements FormServiceInterface {

    private final FormRepository formRepository;

    @Autowired
    public FormService(FormRepository formRepository){
        this.formRepository = formRepository;
    }

    @Override
    public FormSubmission saveFormSubmission(FormSubmissionDTO formSubmissionDTO){
        FormSubmission formSubmission = new FormSubmission(
            formSubmissionDTO.getFirst_name(),
            formSubmissionDTO.getLast_name(),
            formSubmissionDTO.getEmail(),
            formSubmissionDTO.getPhone(),
            formSubmissionDTO.getQuery()
        );

        return formRepository.save(formSubmission);
    }

    @Override
    public List<FormSubmission> getAllFormSubmissions(){
        return formRepository.findAll();
    }

    @Override
    public FormSubmission getFormSubmissionById(String id) {
        return formRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Form submission not found with id: " + id));
    }

    @Override
    public void deleteFormSubmission(String id) {
        formRepository.deleteById(id);
    }
    
    @Override
    public boolean existsByEmail(String email) {
        return formRepository.existsByEmail(email);
    }
}
