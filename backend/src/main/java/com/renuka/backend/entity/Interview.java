package com.renuka.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "interviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String interviewType;

    private int currentQuestion;

    @Column(columnDefinition = "TEXT")
    private String answers;

    // AI Scores
    private int technicalScore;

    private int communicationScore;

    private int confidenceScore;

    private int overallScore;

    private boolean completed;

    // AI Feedback
    @Column(columnDefinition = "TEXT")
    private String feedback;

    @Column(columnDefinition = "TEXT")
    private String strengths;

    @Column(columnDefinition = "TEXT")
    private String weaknesses;

    @Column(columnDefinition = "TEXT")
    private String suggestions;
}