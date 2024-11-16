package com.example.apimaturity.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "assessments")
public class UserAssessment {

    @Id
    @SequenceGenerator(name="assessments_assessment_id_seq", sequenceName="assessments_assessment_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="assessments_assessment_id_seq")
    @Column(name = "record_id")
    private Integer recordId;

    @Column(name = "component_id")
    private Integer componentId;

    @Column(name = "ai_rating")
    private Integer aiRating;

    @Lob
    @Column(name = "observation", columnDefinition = "TEXT")
    private String observation;

    @Column(name = "assessment_number")
    private Integer assessmentNumber;

    @Column(name = "client_id")
    private Integer clientId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "name")
    private String name;

    @Column(name = "ai_rationale")
    private String aiRationale;

    @ManyToOne
    @JoinColumn(name="component_id", insertable = false, updatable = false)
    private Component component;

    @ManyToOne
    @JoinColumn(name="client_id", insertable = false, updatable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name="assessment_number", insertable = false, updatable = false)
    private AssessmentGroup assessmentGroup;

    // getters and setters for all these fields

    // Please note that adding a method similar to Python's `to_dict()`
    // is strongly discouraged in Java -- please use an object mapper (Jackson) instead
}