/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.backend;

/**
 *
 * @author chen0
 */
import com.google.gson.*;

public class JsonParser {

    public static String extractReply(String jsonResponse) {
        com.google.gson.JsonObject json = com.google.gson.JsonParser.parseString(jsonResponse).getAsJsonObject();
        JsonArray choices = json.getAsJsonArray("choices");
        JsonObject message = choices.get(0).getAsJsonObject().getAsJsonObject("message");
        return message.get("content").getAsString().trim();
    }
}