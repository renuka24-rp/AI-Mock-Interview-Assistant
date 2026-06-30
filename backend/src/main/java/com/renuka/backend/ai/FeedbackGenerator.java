package com.renuka.backend.ai;

public class FeedbackGenerator {

    public static String generate(

            int technical,

            int communication,

            int confidence){

        StringBuilder feedback=new StringBuilder();

        if(technical>80){

            feedback.append("Excellent technical knowledge. ");

        }else if(technical>60){

            feedback.append("Good technical understanding. ");

        }else{

            feedback.append("Improve Java fundamentals. ");

        }

        if(communication>80){

            feedback.append("Communication is excellent. ");

        }else{

            feedback.append("Explain answers in more detail. ");

        }

        if(confidence>80){

            feedback.append("Very confident responses.");

        }else{

            feedback.append("Try answering more confidently.");

        }

        return feedback.toString();

    }

}