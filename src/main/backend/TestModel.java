/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.backend;

import java.io.IOException;

/**
 *
 * @author chen0
 */
public class TestModel {
    public static void main(String[] args) {
        String essay = """
            The importance of environmental conservation cannot be overstated. 
            With global temperatures rising and biodiversity declining, it is critical 
            for humans to take responsibility for their impact on the planet.
            """;

        EssayFeedbackAPI api = new EssayFeedbackAPI();

        try {
            String rawJson = api.getFeedback(essay);
            String feedback = JsonParser.extractReply(rawJson);
            System.out.println("Feedback:\n" + feedback);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
