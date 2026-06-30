package com.renuka.backend.ai;

public class ScoreCalculator {

    public static int technicalScore(String answers){

        if(answers==null || answers.isBlank())
            return 0;

        int score = 50;

        if(answers.toLowerCase().contains("java"))
            score +=10;

        if(answers.toLowerCase().contains("spring"))
            score +=10;

        if(answers.toLowerCase().contains("jvm"))
            score +=10;

        if(answers.toLowerCase().contains("oop"))
            score +=10;

        if(answers.length()>300)
            score +=10;

        return Math.min(score,100);

    }

    public static int communicationScore(String answers){

        if(answers==null)
            return 0;

        int score = answers.length()/8;

        return Math.min(score,100);

    }

    public static int confidenceScore(String answers){

        if(answers==null)
            return 0;

        int score=60;

        if(answers.contains("I think"))
            score-=10;

        if(answers.contains("maybe"))
            score-=10;

        if(answers.contains("not sure"))
            score-=15;

        if(answers.length()>250)
            score+=20;

        return Math.max(0,Math.min(score,100));

    }

}