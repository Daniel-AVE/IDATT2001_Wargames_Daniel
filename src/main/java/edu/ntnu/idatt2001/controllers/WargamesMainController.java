package edu.ntnu.idatt2001.controllers;

import edu.ntnu.idatt2001.Scenes.View;
import edu.ntnu.idatt2001.Scenes.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

import java.io.IOException;

public class WargamesMainController {

    /**
     * Clicking Start button brings user to the "home" page of the application
     * @throws IOException if scene could not be switched
     */
    @FXML
    void onStartButtonPressed() throws IOException {
        ViewSwitcher.switchTo((View.HOME));
    }
}