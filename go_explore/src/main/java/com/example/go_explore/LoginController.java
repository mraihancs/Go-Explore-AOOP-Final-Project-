package com.example.go_explore;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registrationButton;


    @FXML
    private void initialize() {
        registrationButton.setOnAction(event -> goToRegistrationPage());

    }

    @FXML
    private void goToRegistrationPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("registration.fxml"));
            Parent registrationRoot = loader.load();

            Stage stage = (Stage) registrationButton.getScene().getWindow();
            Scene registrationScene = new Scene(registrationRoot);

            stage.setScene(registrationScene);
            stage.setTitle("Registration");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loginUser() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Perform login validation in a background thread
        Thread loginThread = new Thread(() -> {
            boolean isValid = validateCredentials(username, password);

            Platform.runLater(() -> {
                if (isValid) {
                    navigateToHomePage(username);
                    //displayAlert("Login Successful", "Welcome, " + username + "!");
                    // Navigate to the home page or perform necessary actions
                } else {
                    displayAlert("Login Failed", "Invalid username or password.");
                }
            });
        });
        loginThread.start();
    }

    // Validate user credentials against a file (users.txt in this case)
    private boolean validateCredentials(String username, String password) {
        String file = "users.txt"; // Path to the file containing usernames and passwords

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String storedUsername = parts[0];
                String storedPassword = parts[1];

                if (storedUsername.equals(username) && storedPassword.equals(password)) {
                    return true; // Credentials match
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // No match found or an error occurred
    }


    private void navigateToHomePage(String username) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("index.fxml"));
            Parent testRoot = loader.load();



            Scene testScene = new Scene(testRoot);
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(testScene);
            stage.setTitle("GO EXPLORE");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            displayAlert("Error", "Failed to load test page.");
        }
    }






    //


    // Helper method to display alerts
    private void displayAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}





//2
//import javafx.fxml.FXML;
//import javafx.scene.control.Alert;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//
//public class LoginController {
//
//    @FXML
//    private TextField usernameField;
//
//    @FXML
//    private PasswordField passwordField;
//
//    // This method is linked to the Login button in login.fxml
//    @FXML
//    private void loginUser() {
//        String username = usernameField.getText();
//        String password = passwordField.getText();
//
//        // Perform login validation
//        boolean isValid = validateCredentials(username, password);
//
//        if (isValid) {
//            // Successful login - navigate to home or do further actions
//            displayAlert("Login Successful", "Welcome, " + username + "!");
//            // Navigate to the home page or do necessary actions
//        } else {
//            // Failed login - show error message
//            displayAlert("Login Failed", "Invalid username or password.");
//        }
//    }
//
//    // Validate user credentials against a file (users.txt in this case)
//    private boolean validateCredentials(String username, String password) {
//        String file = "users.txt"; // Path to the file containing usernames and passwords
//
//        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] parts = line.split(",");
//                String storedUsername = parts[0];
//                String storedPassword = parts[1];
//
//                if (storedUsername.equals(username) && storedPassword.equals(password)) {
//                    return true; // Credentials match
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return false; // No match found or an error occurred
//    }
//
//    // Helper method to display alerts
//    private void displayAlert(String title, String content) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(content);
//        alert.showAndWait();
//    }
//}



//1
//import javafx.fxml.FXML;
//import javafx.scene.control.Alert;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//
//public class LoginController {
//
//    @FXML
//    private TextField usernameField;
//
//    @FXML
//    private PasswordField passwordField;
//
//    private HomeController homeController;
//
//    public void setHomeController(HomeController homeController) {
//        this.homeController = homeController;
//    }
//// I am using Thread here (MRaihan)
//    @FXML
//    private void loginUser() {
//        String username = usernameField.getText();
//        String password = passwordField.getText();
//
//        // Perform login in a background thread
//        Thread loginThread = new Thread(() -> {
//            boolean isValid = validateCredentials(username, password);
//
//            // Update UI on the JavaFX Application Thread
//            javafx.application.Platform.runLater(() -> {
//                if (isValid) {
//                    homeController.setUsername(username);
//                    homeController.goToHomePage();
//                } else {
//                    displayAlert("Login Failed", "Invalid username or password.");
//                }
//            });
//        });
//        loginThread.start();
//    }
//
//    private boolean validateCredentials(String username, String password) {
//        String file = "users.txt";
//        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] parts = line.split(",");
//                String storedUsername = parts[0];
//                String storedPassword = parts[1];
//                if (storedUsername.equals(username) && storedPassword.equals(password)) {
//                    return true; // Credentials match
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return false; // No match found or an error occurred
//    }
//
//    private void displayAlert(String title, String content) {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(content);
//        alert.showAndWait();
//    }
//}

