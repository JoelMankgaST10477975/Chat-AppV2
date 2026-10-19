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

public class RegistrationTest {
    // Username tests
    @Test
    public void testValidUsername() {
        Registration reg = new Registration("kyl_1", "Ch&8sce@Eke99!", "+27838968976");
        assertTrue(reg.checkUserName());
    }

    @Test
    public void testInvalidUsername() {
        Registration reg = new Registration("kyle", "Ch&8sce@Eke99!", "+27838968976");
        assertFalse(reg.checkUserName());
    }

    // Password tests
    @Test
    public void testValidPassword() {
        Registration reg = new Registration("kyl_1", "Ch&8sce@Eke99!", "+27838968976");
        assertTrue(reg.checkPasswordComplexity());
    }

    @Test
    public void testInvalidPassword() {
        Registration reg = new Registration("kyl_1", "password", "+27838968976");
        assertFalse(reg.checkPasswordComplexity());
    }

    // Cell phone tests
    @Test
    public void testValidCellPhoneNumber() {
        Registration reg = new Registration("kyl_1", "Ch&8sce@Eke99!", "+27838968976");
        assertTrue(reg.checkCellPhoneNumber());
    }

    @Test
    public void testInvalidCellPhoneNumber() {
        Registration reg = new Registration("kyl_1", "Ch&8sce@Eke99!", "089665553");
        assertFalse(reg.checkCellPhoneNumber());
    }

    // Login tests
    @Test
    public void testSuccessfulLogin() {
        Registration reg = new Registration("kyl_1", "Ch&8sce@Eke99!", "+27838968976");
        assertEquals("Welcome kyl_1, it is great to see you again.",
                     reg.returnLoginStatus("kyl_1", "Ch&8sce@Eke99!"));
    }

    @Test
    public void testFailedLoginWrongPassword() {
        Registration reg = new Registration("kyl_1", "Ch&8sce@Eke99!", "+27838968976");
        assertEquals("Username or password incorrect, please try again.",
                     reg.returnLoginStatus("kyl_1", "wrongPass123"));
    }

    @Test
    public void testFailedLoginWrongUsername() {
        Registration reg = new Registration("kyl_1", "Ch&8sce@Eke99!", "+27838968976");
        assertEquals("Username or password incorrect, please try again.",
                     reg.returnLoginStatus("wrongUser", "Ch&8sce@Eke99!"));
    }
}
