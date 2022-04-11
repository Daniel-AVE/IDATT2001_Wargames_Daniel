package edu.ntnu.idatt2001;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class mainController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}