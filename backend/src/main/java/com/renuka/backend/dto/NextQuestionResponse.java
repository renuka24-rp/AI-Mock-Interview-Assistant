package com.renuka.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NextQuestionResponse {

    private boolean completed;

    private String question;

}