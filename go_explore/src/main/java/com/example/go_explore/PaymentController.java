package com.example.go_explore;

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

public class PaymentController {
    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField nidField;

    @FXML
    private TextField nationalityField;

    @FXML
    private TextField packageField;

    @FXML
    private TextField amountField;

    @FXML
    private TextField bkashField;

    @FXML
    private TextField tidField;


    @FXML
    private Button submitButton;

    @FXML
    private Button loginButton;


    @FXML
    private void initialize() {
        submitButton.setOnAction(event -> {

            Thread registrationThread = new Thread(this::registerUser);
            registrationThread.start();
        });
    }
//    private void initialize() {
//        submitButton.setOnAction(event -> registerUser());
//    }


    @FXML
    private void log() {
        loginButton.setOnAction(event -> goToLoginPage());
    }
    private void registerUser() {
        String username = usernameField.getText();
        String email = emailField.getText();
        String nid = nidField.getText();
        String nationality = nationalityField.getText();
        String tourpackage = packageField.getText();
        String amount = amountField.getText();
        String bkash = bkashField.getText();
        String tid = tidField.getText();



        // Save registration data to a file
        boolean registrationSuccess = saveRegistration(username, email,nid,nationality,tourpackage,amount,bkash,tid);

        // Display registration success/failure message
        if (registrationSuccess) {
            displayAlert("Payment Successful", "Payment successfully!");
            clearFields();
        } else {
            displayAlert("Payment Error", "Failed to payment.");
        }
    }



    private void goToLoginPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("index.fxml"));
            Parent loginRoot = loader.load();

            // Get the current stage
            Stage stage = (Stage) usernameField.getScene().getWindow();

            Scene loginScene = new Scene(loginRoot);
            stage.setScene(loginScene);
            stage.setTitle("Go_Explore");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    private boolean saveRegistration(String username, String email, String nid, String nationality, String tourpackage, String amount, String bkash, String tid) {
        String file = "data.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(username + "," + email + "," + nid + "," +nationality+ "," +tourpackage+"," + amount + "," + bkash+ "," + tid);
            writer.newLine();
            return true; // Registration successful
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Registration failed
        }
    }

    private void clearFields() {
        usernameField.clear();
        emailField.clear();

        nidField.clear();
        nationalityField.clear();

        packageField.clear();
        amountField.clear();

        bkashField.clear();
        tidField.clear();
    }

    private void displayAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


}
