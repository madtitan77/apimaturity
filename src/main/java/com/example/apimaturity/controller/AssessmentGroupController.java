package com.example.apimaturity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.apimaturity.service.AssessmentGroupService; // Add this import statement
import com.example.apimaturity.model.AssessmentGroup; // Add this import statement


@RestController
@RequestMapping("/api/assessment-groups")
public class AssessmentGroupController {

    @Autowired
    private AssessmentGroupService service;

    @PostMapping
    public ResponseEntity<AssessmentGroup> createAssessmentGroup(@RequestBody AssessmentGroup assessmentGroup) {
        AssessmentGroup created = service.saveAssessmentGroup(assessmentGroup);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    
    }
}