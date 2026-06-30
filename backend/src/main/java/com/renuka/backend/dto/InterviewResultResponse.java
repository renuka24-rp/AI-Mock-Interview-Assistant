package com.renuka.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InterviewResultResponse {

    private int technicalScore;

    private int communicationScore;

    private int confidenceScore;

    private int overallScore;

    private String feedback;

}