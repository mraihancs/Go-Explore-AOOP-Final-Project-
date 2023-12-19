package com.example.go_explore;

//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//
//public class RegistrationController {
//
//    @FXML
//    private TextField usernameField;
//
//    @FXML
//    private PasswordField passwordField;
//
//    @FXML
//    private TextField emailField;
//
//    @FXML
//    private Button registerButton;
//
//    @FXML
//    private void initialize() {
//        registerButton.setOnAction(event -> registerUser());
//    }
//
//    private void registerUser() {
//        String username = usernameField.getText();
//        String password = passwordField.getText();
//        String email = emailField.getText();
//
//        // Save registration data to a file
//        saveRegistration(username, password, email);
//    }
//
//    private void saveRegistration(String username, String password, String email) {
//        String file = "users.txt";
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
//            writer.write(username + "," + password + "," + email);
//            writer.newLine();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}


import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RegistrationController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField emailField;

    @FXML
    private Button registerButton;

    @FXML
    private Button loginButton;



    @FXML
    private void initialize() {
        registerButton.setOnAction(event -> registerUser());
    }

    @FXML
    private void log() {
        loginButton.setOnAction(event -> goToLoginPage());
    }

    private void registerUser() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();

        // Save registration data to a file
        boolean registrationSuccess = saveRegistration(username, password, email);

        // Display registration success/failure message
        if (registrationSuccess) {
            displayAlert("Registration Successful", "User registered successfully!");
            clearFields();
        } else {
            displayAlert("Registration Error", "Failed to register user.");
        }
    }
    //Go to  Login Page codes

    private void goToLoginPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent loginRoot = loader.load();

            // Get the current stage
            Stage stage = (Stage) usernameField.getScene().getWindow();

            Scene loginScene = new Scene(loginRoot);
            stage.setScene(loginScene);
            stage.setTitle("Login-Go_Explore");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean saveRegistration(String username, String password, String email) {
        String file = "users.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(username + "," + password + "," + email);
            writer.newLine();
            return true; // Registration successful
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Registration failed
        }
    }

    private void clearFields() {
        usernameField.clear();
        passwordField.clear();
        emailField.clear();
    }

    private void displayAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
