package com.renuka.backend.ai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.renuka.backend.dto.AiEvaluationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public GeminiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AiEvaluationResponse evaluateAnswer(String question, String answer) {

        try {

            String prompt = PromptBuilder.buildPrompt(question, answer);

            String url =
                    "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key="
                            + apiKey;

            String requestBody = """
                    {
                      "contents": [
                        {
                          "parts": [
                            {
                              "text": %s
                            }
                          ]
                        }
                      ]
                    }
                    """.formatted(toJson(prompt));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity =
                    new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.POST,
                            entity,
                            String.class
                    );

            JsonNode root = objectMapper.readTree(response.getBody());

            String aiText =
                    root.path("candidates")
                            .get(0)
                            .path("content")
                            .path("parts")
                            .get(0)
                            .path("text")
                            .asText();

            return objectMapper.readValue(
                    aiText,
                    AiEvaluationResponse.class
            );

        } catch (Exception e) {

            e.printStackTrace();

            return new AiEvaluationResponse(
                    0,
                    0,
                    0,
                    0,
                    "AI Evaluation Failed",
                    "",
                    "",
                    ""
            );

        }

    }

    private String toJson(String text) {

        return "\"" +
                text.replace("\\", "\\\\")
                        .replace("\"", "\\\"")
                        .replace("\n", "\\n")
                + "\"";
    }

}