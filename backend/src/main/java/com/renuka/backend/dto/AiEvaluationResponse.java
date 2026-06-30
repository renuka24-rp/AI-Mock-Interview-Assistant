package com.renuka.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AiEvaluationResponse {

    private int technicalScore;
    private int communicationScore;
    private int confidenceScore;
    private int overallScore;

    private String feedback;
    private String strengths;
    private String weaknesses;
    private String suggestions;

}