package com.renuka.backend.service;

import com.renuka.backend.ai.GeminiService;
import com.renuka.backend.ai.QuestionBank;

import com.renuka.backend.dto.AiEvaluationResponse;
import com.renuka.backend.dto.InterviewResultResponse;
import com.renuka.backend.dto.NextQuestionResponse;
import com.renuka.backend.dto.StartInterviewRequest;
import com.renuka.backend.dto.StartInterviewResponse;
import com.renuka.backend.dto.SubmitAnswerRequest;

import com.renuka.backend.entity.Interview;
import com.renuka.backend.repository.InterviewRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewService {

    private final InterviewRepository repository;
    private final GeminiService geminiService;

    public InterviewService(
            InterviewRepository repository,
            GeminiService geminiService) {

        this.repository = repository;
        this.geminiService = geminiService;
    }

    // =======================
    // START INTERVIEW
    // =======================
    public StartInterviewResponse start(StartInterviewRequest request) {

        Interview interview = Interview.builder()
                .interviewType(request.getInterviewType())
                .currentQuestion(0)
                .completed(false)
                .technicalScore(0)
                .communicationScore(0)
                .confidenceScore(0)
                .overallScore(0)
                .build();

        repository.save(interview);

        String firstQuestion =
                request.getInterviewType().equalsIgnoreCase("JAVA")
                        ? QuestionBank.JAVA.get(0)
                        : QuestionBank.HR.get(0);

        return new StartInterviewResponse(
                interview.getId(),
                firstQuestion
        );
    }

    // =======================
    // SUBMIT ANSWER
    // =======================
    public NextQuestionResponse submitAnswer(SubmitAnswerRequest request) {

        Interview interview = repository.findById(request.getInterviewId())
                .orElseThrow();

        String oldAnswers =
                interview.getAnswers() == null ? "" : interview.getAnswers();

        interview.setAnswers(oldAnswers + "\n" + request.getAnswer());

        interview.setCurrentQuestion(
                interview.getCurrentQuestion() + 1
        );

        int index = interview.getCurrentQuestion();

        List<String> questions =
                interview.getInterviewType().equalsIgnoreCase("JAVA")
                        ? QuestionBank.JAVA
                        : QuestionBank.HR;

        if (index >= questions.size()) {

            interview.setCompleted(true);
            repository.save(interview);

            return new NextQuestionResponse(
                    true,
                    "Interview Finished"
            );
        }

        repository.save(interview);

        return new NextQuestionResponse(
                false,
                questions.get(index)
        );
    }

    // =======================
    // AI EVALUATION RESULT
    // =======================
    public InterviewResultResponse result(Long interviewId) {

        Interview interview = repository.findById(interviewId)
                .orElseThrow();

        AiEvaluationResponse ai =
                geminiService.evaluateAnswer(
                        "Complete Interview Evaluation",
                        interview.getAnswers()
                );

        // Save AI scores
        interview.setTechnicalScore(ai.getTechnicalScore());
        interview.setCommunicationScore(ai.getCommunicationScore());
        interview.setConfidenceScore(ai.getConfidenceScore());
        interview.setOverallScore(ai.getOverallScore());

        interview.setFeedback(ai.getFeedback());
        interview.setStrengths(ai.getStrengths());
        interview.setWeaknesses(ai.getWeaknesses());
        interview.setSuggestions(ai.getSuggestions());

        repository.save(interview);

        return new InterviewResultResponse(
                ai.getTechnicalScore(),
                ai.getCommunicationScore(),
                ai.getConfidenceScore(),
                ai.getOverallScore(),
                ai.getFeedback(),
                ai.getStrengths(),
                ai.getWeaknesses(),
                ai.getSuggestions()
        );
    }

}