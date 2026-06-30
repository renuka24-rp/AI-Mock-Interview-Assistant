package com.renuka.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StartInterviewResponse {

    private Long interviewId;

    private String question;

}