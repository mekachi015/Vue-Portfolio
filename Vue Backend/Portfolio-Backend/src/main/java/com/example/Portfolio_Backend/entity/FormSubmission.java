package com.example.Portfolio_Backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "form_submissions")
@Getter
@Setter
public class FormSubmission {

   @Id
    private String id;
    
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String query;
    private LocalDateTime submissionDate;

    public FormSubmission(){

    }

    public FormSubmission(String first_name, String last_name, String email, String phone, String query) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
        this.query = query;
        this.submissionDate = LocalDateTime.now();
    }
}
