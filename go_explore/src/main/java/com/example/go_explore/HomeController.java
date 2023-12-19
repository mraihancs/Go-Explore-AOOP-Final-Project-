package com.example.go_explore;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

    public class HomeController {

        @FXML
        private void handleButtonToPage1Click(ActionEvent event) {
            navigateToPage(event, "page1.fxml");
        }

        @FXML
        private void handleButtonToPage2Click(ActionEvent event) {
            navigateToPage(event, "page2.fxml");
        }

        @FXML
        private void handleButtonToPage3Click(ActionEvent event) {
            navigateToPage(event, "page3.fxml");
        }

        @FXML
        private void handleButtonToPage4Click(ActionEvent event) {
            navigateToPage(event, "index.fxml");
        }

        @FXML
        private void handleButtonToPage5Click(ActionEvent event) {
            navigateToPage(event, "payment.fxml");
        }


        private void navigateToPage(ActionEvent event, String page) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(page));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


