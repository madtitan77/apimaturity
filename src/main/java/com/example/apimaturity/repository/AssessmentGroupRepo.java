package com.example.apimaturity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.apimaturity.model.AssessmentGroup; 

public interface AssessmentGroupRepo extends JpaRepository<AssessmentGroup, Integer> {
}