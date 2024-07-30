package com.example.apimaturity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.apimaturity.service.AssessmentGroupService;
import com.example.apimaturity.api.ApiResponse;
import com.example.apimaturity.model.AssessmentGroup; 



@RestController
@RequestMapping("/api/apimaturity/clients/{clientId}/assessment-groups/")
public class AssessmentGroupController {

    @Autowired
    private AssessmentGroupService service;

    @PostMapping
    public ResponseEntity<ApiResponse<AssessmentGroup>> createAssessmentGroup(@PathVariable("clientId") Long pathClientId, @RequestBody AssessmentGroup assessmentGroup) {

        // Check if the clientId in the path matches the clientId in the request body
        if (!pathClientId.equals(assessmentGroup.getClientId())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, "Client ID in the path does not match Client ID in the request body", null));
        }
    
        AssessmentGroup created = service.saveAssessmentGroup(assessmentGroup);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Assessment group created successfully", created));
    
    }
}