package com.renuka.backend.service;

import com.renuka.backend.ai.FeedbackGenerator;
import com.renuka.backend.ai.QuestionBank;
import com.renuka.backend.ai.ScoreCalculator;

import com.renuka.backend.dto.InterviewResultResponse;
import com.renuka.backend.dto.NextQuestionResponse;
import com.renuka.backend.dto.StartInterviewRequest;
import com.renuka.backend.dto.StartInterviewResponse;
import com.renuka.backend.dto.SubmitAnswerRequest;

import com.renuka.backend.entity.Interview;
import com.renuka.backend.repository.InterviewRepository;

import org.springframework.stereotype.Service;

@Service
public class InterviewService {

    private final InterviewRepository repository;

    public InterviewService(InterviewRepository repository) {
        this.repository = repository;
    }

    // =======================
    // START INTERVIEW
    // =======================
    public StartInterviewResponse start(StartInterviewRequest request) {

        Interview interview = Interview.builder()
                .interviewType(request.getInterviewType())
                .currentQuestion(0)
                .completed(false)
                .confidenceScore(0)
                .communicationScore(0)
                .build();

        repository.save(interview);

        String firstQuestion;

        if (request.getInterviewType().equalsIgnoreCase("JAVA")) {
            firstQuestion = QuestionBank.JAVA.get(0);
        } else {
            firstQuestion = QuestionBank.HR.get(0);
        }

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

        java.util.List<String> questions =
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

        int technical =
                ScoreCalculator.technicalScore(interview.getAnswers());

        int communication =
                ScoreCalculator.communicationScore(interview.getAnswers());

        int confidence =
                ScoreCalculator.confidenceScore(interview.getAnswers());

        int overall =
                (technical + communication + confidence) / 3;

        String feedback =
                FeedbackGenerator.generate(
                        technical,
                        communication,
                        confidence
                );

        return new InterviewResultResponse(
                technical,
                communication,
                confidence,
                overall,
                feedback
        );
    }

}