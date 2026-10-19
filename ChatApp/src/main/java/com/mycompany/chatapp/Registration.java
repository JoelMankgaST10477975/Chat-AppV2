/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Student
 */

package com.mycompany.chatapp;


public class Registration {
    private String username;
    private String password;
    private String cellNumber;

    // Constructor
    public Registration(String username, String password, String cellNumber) {
        this.username = username;
        this.password = password;
        this.cellNumber = cellNumber;
    }

    // Username validation
    public boolean checkUserName() {
        return username.contains("_") && username.length() <= 5;
    }

    // Password validation
    public boolean checkPasswordComplexity() {
        boolean length = password.length() >= 8;
        boolean capitalLetter = password.matches(".*[A-Z].*");
        boolean number = password.matches(".*[0-9].*");
        boolean specialChar = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");

        return length && capitalLetter && number && specialChar;
    }

    // Cell phone validation
    public boolean checkCellPhoneNumber() {
        return cellNumber.matches("^\\+\\d{10,12}$");
    }

    // Registration logic
    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber()) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        return "Username, password, and cell phone successfully captured.";
    }

    // Login validation
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return this.username.equals(enteredUsername) && this.password.equals(enteredPassword);
    }

    public String returnLoginStatus(String enteredUsername, String enteredPassword) {
        if (loginUser(enteredUsername, enteredPassword)) {
            return "Welcome " + enteredUsername + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
