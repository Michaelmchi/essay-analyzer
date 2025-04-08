/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.backend;
import java.net.HttpURLConnection;
import org.json.JSONObject;
import com.google.gson.Gson;

/**
 *
 * @author chen0
 */
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class EssayFeedbackAPI {

    private static final String API_KEY = "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";
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public String getFeedback(String essayText) throws IOException {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        String prompt = "Please provide constructive feedback on this essay:\n\n" + essayText;

        String requestBody = """
            {
              "model": "gpt-3.5-turbo",
              "messages": [
                {"role": "system", "content": "You are a helpful academic writing assistant."},
                {"role": "user", "content": "%s"}
              ],
              "temperature": 0.7
            }
        """.formatted(escapeJson(prompt));

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = requestBody.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"))) {

            String line;
            while ((line = br.readLine()) != null) {
                response.append(line.trim());
            }
        }

        return response.toString();
    }

    private String escapeJson(String input) {
        return input.replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r");
    }
}