package edu.ntnu.idatt2001.controllers;

import edu.ntnu.idatt2001.Scenes.View;
import edu.ntnu.idatt2001.Scenes.ViewSwitcher;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * A controller class for both about and help page
 *
 * @author Daniel Evensen
 */
public class CreditsController {

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
     * Redirects to credits page if clicked on credits menu button
     * in this case, credits menu button is disabled, so it can't be clicked
     * @throws IOException if scenes could not be switched
     */
    @FXML
    void onCreditsButtonPressed()
    throws IOException {
        ViewSwitcher.switchTo(View.CREDITS);
    }

    /**
     * Redirects to battle page if clicked on new battle button
     * @throws IOException if scenes could not be switched
     */
    @FXML
    void onNewBattleButtonPressed()
    throws IOException{
        ViewSwitcher.switchTo(View.BATTLE);
    }
}
