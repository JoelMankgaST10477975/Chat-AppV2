/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

/**
 *
 * @author Student
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class MessageTest {
 @Test
    public void testMessageLengthSuccess() {
        // Test Data 1 from prompt
        Message msg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        // Creating a message with 251 characters
        String longMessage = "a".repeat(251); 
        Message msg = new Message(0, "+27718693002", longMessage);
        assertEquals("Message exceeds 250 characters by 1; please reduce the size.", msg.checkMessageLength());
    }

    @Test
    public void testRecipientSuccess() {
        Message msg = new Message(0, "+2783455789", "Test Message"); 
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipientFailure() {
        // Test Data 2 from prompt: "08575975889" lacks the '+' code
        Message msg = new Message(0, "08575975889", "Hi Keegan, did you receive the payment?");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }

    @Test
    public void testMessageHashGeneration() {
        // Testing the Hash format: 00:0:HITHANKS
        Message msg = new Message(0, "+27718693002", "Hi thanks");
        
        // The ID is randomly generated, so we extract the first two chars dynamically
        String expectedStart = msg.getMessageID().substring(0, 2) + ":0:HI";
        
        assertTrue(msg.getMessageHash().startsWith(expectedStart), "Hash should start with first 2 digits of ID and message number");
        assertTrue(msg.getMessageHash().endsWith("THANKS"), "Hash should end with first and last words in uppercase");
    }
    
    @Test
    public void testSentMessage() {
        Message msg = new Message(0, "+27718693002", "Hello");
        assertEquals("Message successfully sent.", msg.sentMessage("Send"));
        assertEquals("Message successfully stored.", msg.sentMessage("Store"));
        assertEquals("Press 0 to delete the message.", msg.sentMessage("Discard"));
    }   
}
