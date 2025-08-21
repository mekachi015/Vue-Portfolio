package com.example.Portfolio_Backend.repository;

import com.example.Portfolio_Backend.entity.FormSubmission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormRepository  extends MongoRepository<FormSubmission, String>{
    boolean existsByEmail(String email);
}
