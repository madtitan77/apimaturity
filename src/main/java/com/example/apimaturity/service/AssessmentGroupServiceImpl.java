package com.example.apimaturity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.apimaturity.model.AssessmentGroup;
import com.example.apimaturity.repository.AssessmentGroupRepo;

@Service
public class AssessmentGroupServiceImpl implements AssessmentGroupService {

    @Autowired
    private AssessmentGroupRepo assessmentGroupRepo;

    @Override
    public AssessmentGroup saveAssessmentGroup(AssessmentGroup assessmentGroup) {
        return assessmentGroupRepo.save(assessmentGroup);
    }
    
}
