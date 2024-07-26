import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.apimaturity.model.AssessmentGroup;
import com.example.apimaturity.repository.AssessmentGroupRepo;
import com.example.apimaturity.service.AssessmentGroupService;
import com.example.apimaturity.service.AssessmentGroupServiceImpl;
import com.example.apimaturity.controller.AssessmentGroupController; // Add the import statement for AssessmentGroupController
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class AssessmentGroupControllerTest {

    @Mock
    private AssessmentGroupRepo assessmentGroupRepo;


    @InjectMocks
    private AssessmentGroupServiceImpl assessmentGroupService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAssessmentGroup() {
        // Arrange
        AssessmentGroup assessmentGroup = new AssessmentGroup();
        
        when(assessmentGroupService.saveAssessmentGroup(any(AssessmentGroup.class))).thenReturn(assessmentGroup);

        // Act
        AssessmentGroup result = assessmentGroupService.saveAssessmentGroup(assessmentGroup);

        assertEquals(assessmentGroup.getClientId(), result.getClientId());
        assertEquals(assessmentGroup.getUserId(), result.getUserId());
        assertEquals(assessmentGroup.getObjective(), result.getObjective());
        assertEquals(assessmentGroup.getName(), result.getName());
       
    }
}