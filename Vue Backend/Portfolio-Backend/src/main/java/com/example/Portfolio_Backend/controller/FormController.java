package com.example.Portfolio_Backend.controller;


import com.example.Portfolio_Backend.DTO.FormSubmissionDTO;
import com.example.Portfolio_Backend.entity.FormSubmission;
import com.example.Portfolio_Backend.servicesInterface.FormServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/form")
@CrossOrigin(origins = "http://localhost:8080")
@Validated
public class FormController {

    private final FormServiceInterface formService;
    
    private final RestTemplate restTemplate;

    private static final String FORMSPREE_URL = "https://formspree.io/f/mnnakkdg";


    @Autowired
    public FormController(FormServiceInterface formService, RestTemplate restTemplate){
        this.formService = formService;
        this.restTemplate = restTemplate;
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitForm( @Valid @RequestBody FormSubmissionDTO formSubmissionDTO,
    BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

            return ResponseEntity.badRequest().body(errors);
        }

        if (formService.existsByEmail(formSubmissionDTO.getEmail())){
            Map<String, String> error = new HashMap<>();
            error.put("email", "Email already exists");

            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }

        try {

            FormSubmission savedSubmission = formService.saveFormSubmission(formSubmissionDTO);

            ResponseEntity<String> formspreeResponse = forwardToFormSpree (formSubmissionDTO);

            if (formspreeResponse.getStatusCode().is2xxSuccessful()){
                return ResponseEntity.status(HttpStatus.CREATED).body(savedSubmission);
            } else {
                System.err.println("Formspree submission failed; " + formspreeResponse.getBody());

                return ResponseEntity.status(HttpStatus.CREATED).body(savedSubmission);
            }
        }catch(Exception e){
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error saving form data" + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    private ResponseEntity<String> forwardToFormSpree(FormSubmissionDTO formData){
        HttpHeaders headers = new HttpHeaders ();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept", "application/json");

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("first_name", formData.getFirst_name());
        requestBody.put("last_name", formData.getLast_name());
        requestBody.put("email", formData.getEmail());
        requestBody.put("phone", formData.getPhone());
        requestBody.put("query", formData.getQuery());

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<> (requestBody, headers);

        return restTemplate.exchange(
            FORMSPREE_URL,
            HttpMethod.POST,
            requestEntity,
            String.class);
    }
    



    /**@PostMapping("/submit")
    public ResponseEntity<?> submitForm(@Valid @RequestBody FormSubmissionDTO formSubmissionDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            
            return ResponseEntity.badRequest().body(errors);
        }

        if(formService.existsByEmail(formSubmissionDTO.getEmail())){
            Map<String, String> error = new HashMap<>();
            error.put("email", "Email already exists");

            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }

        try{
            FormSubmission savedSubmission = formService.saveFormSubmission(formSubmissionDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedSubmission);
        } catch (Exception e){
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error saving form data: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    } **/

    @GetMapping("/submissions")
    public ResponseEntity<List<FormSubmission>> getAllSubmissions(){
        List<FormSubmission> submissions = formService.getAllFormSubmissions();
        
        return ResponseEntity.ok(submissions);
    }

    @GetMapping("/submissions/{id}")
    public ResponseEntity<FormSubmission> getSubmissionById(@PathVariable String id){
        FormSubmission submission = formService.getFormSubmissionById(id);

        return ResponseEntity.ok(submission);
    }

    @DeleteMapping("/submissions/{id}")
    public ResponseEntity<?> deleteSubmission(@PathVariable String id){
        formService.deleteFormSubmission(id);

        return ResponseEntity.noContent().build();
    }

}
