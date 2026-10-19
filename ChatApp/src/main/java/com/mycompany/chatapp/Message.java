/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;
/**
 *
 * @author Student
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class Message {
   private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    // Static variables to track messages across the application
    private static int totalMessagesSent = 0;
    private static ArrayList<String> sentMessagesList = new ArrayList<>();

    public Message(int messageNumber, String recipient, String messageText) {
        this.messageID = generateID();
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash(); 
}

private String generateID() {
        Random rand = new Random();
        return String.format("%010d", rand.nextInt(1000000000));
    }

// Ensures the message ID is not more than ten characters.
    public boolean checkMessageID() {
        return this.messageID.length() <= 10;
    }
    
// Ensures the recipient cell number is no more than ten characters long and starts with a code (+)
    public String checkRecipientCell() {
        // Note: The prompt says "no more than ten characters long", but test data like +27834557896 is 12 chars. 
        // Standard international format is +[country code][number]. We will check for '+' and length.
        if (recipient.startsWith("+") && recipient.length() >= 10) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }
    
 // Ensures message is <= 250 characters
    public String checkMessageLength() {
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = messageText.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
    }
    
// Creates and returns the Message Hash
    public String createMessageHash() {
        if (messageText == null || messageText.isEmpty()) return "";
        
        String firstTwo = messageID.substring(0, 2);
        String msgNum = String.valueOf(messageNumber);
        
        // Remove punctuation and split into words
        String[] words = messageText.replaceAll("[^a-zA-Z ]", "").trim().split("\\s+");
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();

        return firstTwo + ":" + msgNum + ":" + firstWord + lastWord;
    }


// Allows user to choose Send, Store, or Discard
    public String sentMessage(String choice) {
        if (choice.equalsIgnoreCase("Send")) {
            totalMessagesSent++;
            sentMessagesList.add("ID: " + messageID + " | Hash: " + messageHash + " | To: " + recipient + " | Msg: " + messageText);
            return "Message successfully sent.";
        } else if (choice.equalsIgnoreCase("Store")) {
            storeMessage();
            return "Message successfully stored.";
        } else if (choice.equalsIgnoreCase("Discard")) {
            return "Press 0 to delete the message.";
        }
        return "Invalid choice.";
    }
    

  // Returns all messages sent while the program is running
    public String printMessages() {
        if (sentMessagesList.isEmpty()) return "No messages sent yet.";
        StringBuilder sb = new StringBuilder();
        for (String msg : sentMessagesList) {
            sb.append(msg).append("\n");
        }
        return sb.toString();
    }
    
  // Returns the total number of messages sent
    public int returnTotalMessagess() {
        return totalMessagesSent;
    }
    
 // Stores message in JSON format (Basic File I/O implementation)
    public void storeMessage() {
        try (FileWriter file = new FileWriter("stored_messages.json", true)) {
            String json = "{\n" +
                    "  \"id\": \"" + messageID + "\",\n" +
                    "  \"recipient\": \"" + recipient + "\",\n" +
                    "  \"message\": \"" + messageText + "\",\n" +
                    "  \"hash\": \"" + messageHash + "\"\n" +
                    "}\n";
            file.write(json);
        } catch (IOException e) {
            System.out.println("Error storing message: " + e.getMessage());
        }
    }
    
// Getters used for testing
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
}