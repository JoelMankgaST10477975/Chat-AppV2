/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapp;

/**
 *
 * @author Student
 */
import java.util.Scanner;
public class ChatApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Registration phase
        System.out.println("Registration ");
        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        System.out.print("Enter cell phone number (+27...): ");
        String cellNumber = input.nextLine();

        Registration reg = new Registration(username, password, cellNumber);
        String registrationResult = reg.registerUser();
        System.out.println(registrationResult);

   // Only allow login if registration succeeded
        if (registrationResult.equals("Username, password, and cell phone successfully captured.")) {
            System.out.println("\n Login ");
            System.out.print("Enter username: ");
            String loginUsername = input.nextLine();

            System.out.print("Enter password: ");
            String loginPassword = input.nextLine();

            // Capture the login status to check if it was successful
            String loginStatus = reg.returnLoginStatus(loginUsername, loginPassword);
            System.out.println(loginStatus);

            // Only show the menu if the login was successful
            if (loginStatus.startsWith("Welcome")) {
                
                System.out.println("\nWelcome to QuickChat.");
                System.out.print("How many messages do you wish to enter? ");
                int numMessages = input.nextInt();
                input.nextLine(); // Consume the newline character after the number
                
                boolean running = true;
                int messagesEntered = 0;

                // 4. Application runs until user selects 'quit'
                while (running) {
                    // 3. Numeric Menu
                    System.out.println("\n--- Menu ---");
                    System.out.println("1) Send Messages");
                    System.out.println("2) Show recently sent messages");
                    System.out.println("3) Quit");
                    System.out.print("Choose an option: ");
                    
                    int choice = input.nextInt();
                    input.nextLine(); // Consume newline

                    switch (choice) {
                        case 1:
                            if (messagesEntered >= numMessages) {
                                System.out.println("You have reached your limit of " + numMessages + " messages.");
                                break;
                            }
                            
                            System.out.println("\n--- Entering Message " + (messagesEntered + 1) + " ---");
                            System.out.print("Enter recipient number (+27...): ");
                            String recipient = input.nextLine();
                            
                            System.out.print("Enter message: ");
                            String text = input.nextLine();
                     // Instantiate your Message class
                            Message msg = new Message(messagesEntered, recipient, text);
                            
                            // Check validation (Optional but highly recommended)
                            System.out.println(msg.checkRecipientCell());
                            System.out.println(msg.checkMessageLength());
                            
                            // Ask the user for the Send/Discard/Store option
                            System.out.println("\nChoose an action:");
                            System.out.println("1) Send Message");
                            System.out.println("2) Disregard Message");
                            System.out.println("3) Store Message");
                            System.out.print("Choice: ");
                            int actionChoice = input.nextInt();
                            input.nextLine(); // Consume newline
                            
                            // Translate the number into the String your method expects
                            String actionString = "";
                            if (actionChoice == 1) {
                                actionString = "Send";
                            } else if (actionChoice == 2) {
                                actionString = "Discard";
                            } else if (actionChoice == 3) {
                                actionString = "Store";
                            }
                            
                            // Call the method from your Message class and print the result
                            System.out.println(msg.sentMessage(actionString));
                            
                            // Increment the message counter
                            messagesEntered++;
                            
                            if (messagesEntered == numMessages) {
                                System.out.println("Total messages sent: " + messagesEntered);
                            }
                            break;

                        case 2:
                            // Point 3b
                            System.out.println("Coming Soon.");
                            break;

                        case 3:
                            running = false;
                            System.out.println("Exiting QuickChat. Goodbye!");
                            break;

                        default:
                            System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                    }
                }
            }

        } else {
            System.out.println("\n Registration failed. Please fix your details before trying to log in.");
        }

        input.close();
    } 
} 