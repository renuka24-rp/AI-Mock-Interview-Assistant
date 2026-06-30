package com.renuka.backend.controller;

import com.renuka.backend.dto.InterviewResultResponse;
import com.renuka.backend.dto.NextQuestionResponse;
import com.renuka.backend.dto.StartInterviewRequest;
import com.renuka.backend.dto.StartInterviewResponse;
import com.renuka.backend.dto.SubmitAnswerRequest;
import com.renuka.backend.service.InterviewService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interview")
public class InterviewController {

    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    // ==========================
    // Start Interview
    // ==========================
    @PostMapping("/start")
    public StartInterviewResponse startInterview(
            @RequestBody StartInterviewRequest request) {

        return interviewService.start(request);
    }

    // ==========================
    // Submit Answer
    // ==========================
    @PostMapping("/answer")
    public NextQuestionResponse answer(
            @RequestBody SubmitAnswerRequest request) {

        return interviewService.submitAnswer(request);
    }

    // ==========================
    // Get Interview Result
    // ==========================
    @GetMapping("/result/{id}")
    public InterviewResultResponse result(
            @PathVariable Long id) {

        return interviewService.result(id);
    }

}