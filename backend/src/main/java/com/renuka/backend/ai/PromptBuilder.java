package com.renuka.backend.ai;

public class PromptBuilder {

    private PromptBuilder() {
    }

    public static String buildPrompt(String question, String answer) {

        return """
                You are an experienced Senior Software Engineer conducting a technical interview.

                Evaluate the candidate's answer professionally.

                Question:
                %s

                Candidate Answer:
                %s

                Evaluate the answer on:

                1. Technical Accuracy (0-100)
                2. Communication Skills (0-100)
                3. Confidence (0-100)

                Also provide:
                - Overall Score
                - Detailed Feedback
                - Strengths
                - Weaknesses
                - Suggestions for Improvement

                IMPORTANT:
                Return ONLY valid JSON.

                Example:

                {
                  "technicalScore":90,
                  "communicationScore":85,
                  "confidenceScore":80,
                  "overallScore":85,
                  "feedback":"Excellent Java fundamentals.",
                  "strengths":"Good understanding of JVM and OOP.",
                  "weaknesses":"Could explain memory management in more detail.",
                  "suggestions":"Use more real-world examples and explain concepts confidently."
                }

                Do not return markdown.
                Do not return explanation.
                Do not use ```json.
                Return ONLY JSON.
                """.formatted(question, answer);

    }

}