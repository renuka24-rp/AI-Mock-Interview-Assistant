package com.renuka.backend.controller;

import com.renuka.backend.ai.GeminiService;
import com.renuka.backend.dto.AiEvaluationResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeminiController {

    private final GeminiService geminiService;

    public GeminiController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @GetMapping("/api/test/gemini")
    public AiEvaluationResponse testGemini() {

        return geminiService.evaluateAnswer(
                "What is JVM?",
                "JVM is the Java Virtual Machine that executes Java bytecode."
        );
    }
}