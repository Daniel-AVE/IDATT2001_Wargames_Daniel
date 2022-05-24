package edu.ntnu.idatt2001.controllers;

import edu.ntnu.idatt2001.Scenes.View;
import edu.ntnu.idatt2001.Scenes.ViewSwitcher;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * A controller class for both about and help page
 */
public class HomeController {

    /**
     * Redirects to main page if home menu button is clicked
     * @throws IOException if scenes could not be switched
     */
    @FXML
    void onHomeButtonPressed()
    throws IOException {
        ViewSwitcher.switchTo(View.HOME);
    }

    /**
     * Redirects to about page if clicked on about menu button
     * @throws IOException if scenes could not be switched
     */
    @FXML
    void onCreditsButtonPressed()
    throws IOException {
        ViewSwitcher.switchTo(View.CREDITS);
    }

    @FXML
    void onNewBattleButtonPressed() throws IOException {
        ViewSwitcher.switchTo(View.BATTLE);
    }
}