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

    @Column(length = 5000)
    private String answers;

    private int confidenceScore;

    private int communicationScore;

    private boolean completed;
}