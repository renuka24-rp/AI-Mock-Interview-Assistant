package com.renuka.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubmitAnswerRequest {

    private Long interviewId;

    private String answer;

}