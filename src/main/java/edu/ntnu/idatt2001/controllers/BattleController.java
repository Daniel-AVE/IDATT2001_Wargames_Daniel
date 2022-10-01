package edu.ntnu.idatt2001.controllers;

import edu.ntnu.idatt2001.Army.Army;
import edu.ntnu.idatt2001.Battles.Battle;
import edu.ntnu.idatt2001.IO.*;
import edu.ntnu.idatt2001.Scenes.View;
import edu.ntnu.idatt2001.Scenes.ViewSwitcher;
import edu.ntnu.idatt2001.Terrain.Terrain;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * A controller class for Battle page
 *
 * @author Daniel Evensen
 */
public class BattleController {
    /**
     * all @FXML variables here instantiates objects made in Scenebuilder, with an FX:ID attached to them
     */
    @FXML
    Button exitButton;
    /**
     * The Battle button.
     */
    @FXML
    Button battleButton;
    /**
     * The Reset button.
     */
    @FXML
    Button resetButton;
    /**
     * The Path button 1.
     */
    @FXML
    Button pathButton1, /**
     * The Path button 2.
     */
    pathButton2;
    /**
     * The Load button 1.
     */
    @FXML
    Button loadButton1, /**
     * The Load button 2.
     */
    loadButton2;
    /**
     * The Warning label.
     */
    @FXML
    Label warningLabel;
    /**
     * The Winner label.
     */
    @FXML
    Label winnerLabel;
    /**
     * The Infantry army 1.
     */
    @FXML
    Label infantryArmy1, /**
     * The Infantry army 2.
     */
    infantryArmy2;
    /**
     * The Ranged army 1.
     */
    @FXML
    Label rangedArmy1, /**
     * The Ranged army 2.
     */
    rangedArmy2;
    /**
     * The Cavalry army 1.
     */
    @FXML
    Label cavalryArmy1, /**
     * The Cavalry army 2.
     */
    cavalryArmy2;
    /**
     * The Commander army 1.
     */
    @FXML
    Label commanderArmy1, /**
     * The Commander army 2.
     */
    commanderArmy2;
    /**
     * The Wizard army 1.
     */
    @FXML
    Label wizardArmy1, /**
     * The Wizard army 2.
     */
    wizardArmy2;
    /**
     * The Swordmaster army 1.
     */
    @FXML
    Label swordmasterArmy1, /**
     * The Swordmaster army 2.
     */
    swordmasterArmy2;
    /**
     * The Name of army 1.
     */
    @FXML
    Label nameOfArmy1, /**
     * The Name of army 2.
     */
    nameOfArmy2;
    /**
     * The Army 1 total.
     */
    @FXML
    Label army1Total, /**
     * The Army 2 total.
     */
    army2Total;
    /**
     * The Battle type.
     */
    @FXML
    ChoiceBox battleType;
    /**
     * The Terrain.
     */
    @FXML
    ChoiceBox terrain;
    /**
     * The Terrain image.
     */
    @FXML
    ImageView terrainImage;
    /**
     * The Infantry tooltip.
     */
    @FXML
    Tooltip infantryTooltip, /**
     * The Infantry tooltip 1.
     */
    infantryTooltip1, /**
     * The Ranged tooltip.
     */
    rangedTooltip, /**
     * The Ranged tooltip 1.
     */
    rangedTooltip1, /**
     * The Cavalry tooltip.
     */
    cavalryTooltip, /**
     * The Cavalry tooltip 1.
     */
    cavalryTooltip1,
    /**
     * The Commander tooltip.
     */
    commanderTooltip, /**
     * The Commander tooltip 1.
     */
    commanderTooltip1, /**
     * The Wizard tooltip.
     */
    wizardTooltip, /**
     * The Wizard tooltip 1.
     */
    wizardTooltip1, /**
     * The Swordmaster tooltip.
     */
    swordmasterTooltip, /**
     * The Swordmaster tooltip 1.
     */
    swordmasterTooltip1;

    /**
     * a string for path, so it's easier to write the path down when needed
     */
    private final String path = "src/main/resources/edu/ntnu/idatt2001/army_files/";
    /**
     * a string for error outside of exception handling. Used in warninglabel to let user know to check terminal for message
     */
    private final String error = "Error: Check terminal for exception";

    /**
     * Normal variables needed for this class
     */
    private String pathArmy1;
    private String pathArmy2;
    private Stage fileStage;
    private Army army1;
    private Army army2;
    /**
     * object of FileChooser, so user can choose a file from their computer
     */
    private final FileChooser FC = new FileChooser();
    private Battle battle;

    /**
     * Initializes all choice boxes and other variables which needs to be initialized for this scene.
     * Initializes a tooltip for all labels with unit names, which can be hovered over for information
     * Includes: Adding listeners, to be able to update images and armies without having to refresh page
     *
     * @throws IOException if scene could not be initialized
     */
    @FXML
    public void initialize() throws IOException {
        infantryTooltip.setText("Infantry:\n" +
                "Health: 100\n" +
                "Attack: 15\n" +
                "Attack-Bonus: 2\n" +
                "Armor: 10 \n" +
                "Resist-Bonus: 1\n" +
                "Extra bonus in forest");

        infantryTooltip1.setText("Infantry:\n" +
                "Health: 100\n" +
                "Attack: 15\n" +
                "Attack-Bonus: 2\n" +
                "Armor: 10 \n" +
                "Resist-Bonus: 1\n" +
                "Extra bonus in forest");

        rangedTooltip.setText("Ranged:\n" +
                "Health: 100\n" +
                "Attack: 15\n" +
                "Attack-Bonus: 3\n" +
                "Armor: 8 \n" +
                "Resist-Bonus: 6-4-2\n" +
                "Extra attack bonus in hill\n" +
                "lower attack bonus in forest");

        rangedTooltip1.setText("Ranged:\n" +
                "Health: 100\n" +
                "Attack: 15\n" +
                "Attack-Bonus: 3\n" +
                "Armor: 8 \n" +
                "Resist-Bonus: 6-4-2\n" +
                "Extra attack bonus in hill\n" +
                "lower attack bonus in forest");

        cavalryTooltip.setText("Cavalry:\n" +
                "Health: 100\n" +
                "Attack: 20\n" +
                "Attack-Bonus: 6-2\n" +
                "Armor: 12 \n" +
                "Resist-Bonus: 2\n" +
                "Extra attack bonus in plains\n" +
                "loss of resist bonus in forest");

        cavalryTooltip1.setText("Cavalry:\n" +
                "Health: 100\n" +
                "Attack: 20\n" +
                "Attack-Bonus: 6-2\n" +
                "Armor: 12 \n" +
                "Resist-Bonus: 2\n" +
                "Extra attack bonus in plains\n" +
                "loss of resist bonus in forest");

        commanderTooltip.setText("Commander:\n" +
                "Health: 100\n" +
                "Attack: 25\n" +
                "Attack-Bonus: 6-2\n" +
                "Armor: 15 \n" +
                "Resist-Bonus: 2\n" +
                "Extra attack bonus in plains\n" +
                "loss of resist bonus in forest");

        commanderTooltip1.setText("Commander:\n" +
                "Health: 100\n" +
                "Attack: 25\n" +
                "Attack-Bonus: 6-2\n" +
                "Armor: 15 \n" +
                "Resist-Bonus: 2\n" +
                "Extra attack bonus in plains\n" +
                "loss of resist bonus in forest");

        wizardTooltip.setText("Wizard:\n" +
                "Health: 100\n" +
                "Attack: 18\n" +
                "Attack-Bonus: 12-6-0\n" +
                "Armor: 8 \n" +
                "Resist-Bonus: 8-5-0" +
                "Extra attack bonus in hill and plains\n");

        wizardTooltip1.setText("Wizard:\n" +
                "Health: 100\n" +
                "Attack: 18\n" +
                "Attack-Bonus: 12-6-0\n" +
                "Armor: 8 \n" +
                "Resist-Bonus: 8-5-0" +
                "Extra attack bonus in hill and plains");

        swordmasterTooltip.setText("Swordmaster:\n" +
                "Health: 100\n" +
                "Attack: 20\n" +
                "Attack-Bonus: 0-7-13" +
                "Armor: 10 \n" +
                "Resist-Bonus: 0-2-4" +
                "Extra resist bonus in forest");

        swordmasterTooltip1.setText("Swordmaster:\n" +
                "Health: 100\n" +
                "Attack: 20\n" +
                "Attack-Bonus: 0-7-13" +
                "Armor: 10 \n" +
                "Resist-Bonus: 0-2-4" +
                "Extra resist bonus in forest");




        terrain.getItems().addAll("forest", "hill", "plains");
        terrain.getSelectionModel().selectedItemProperty().addListener((observableValue, oldTerrain, newTerrain) ->
                terrainImage.setImage(new Image(("file:src/main/resources/edu/ntnu/idatt2001/Images/" + newTerrain + ".jpg"))));

        battleType.getItems().addAll("Random", "Turn-based");
    }

    /**
     * On reset button pressed.
     * Uses files and paths to retrieve previously loaded armies again, and reset their values to what it was
     */
    @FXML
    void onResetButtonPressed() {
        File file1 = new File(pathArmy1);
        File file2 = new File(pathArmy2);
        try {
            army1 = ArmyReader.readArmyFromFile(file1);
            army2 = ArmyReader.readArmyFromFile(file2);
            army1UnitNumbers();
            army2UnitNumbers();
        } catch (IOException e) {
            warningLabel.setText(error);
            e.printStackTrace();
        }
        battleButton.setDisable(false);
        loadButton1.setDisable(false);
        loadButton2.setDisable(false);
        pathButton1.setDisable(false);
        pathButton2.setDisable(false);
        resetButton.setDisable(true);
        winnerLabel.setText("");
        warningLabel.setText("Army 1 and army 2 reset.");
    }

    /**
     * Method for easily setting values showcasing unit numbers for army1
     *
     * @throws IOException the io exception
     */
    @FXML
    void army1UnitNumbers() throws IOException{
        nameOfArmy1.setText(army1.getName());
        infantryArmy1.setText(String.valueOf(army1.getInfantryUnit().size()));
        rangedArmy1.setText(String.valueOf(army1.getRangedUnit().size()));
        cavalryArmy1.setText(String.valueOf(army1.getCavalryUnit().size()));
        commanderArmy1.setText(String.valueOf(army1.getCommanderUnit().size()));
        wizardArmy1.setText(String.valueOf(army1.getWizardUnit().size()));
        swordmasterArmy1.setText(String.valueOf(army1.getSwordmasterUnit().size()));
        army1Total.setText(String.valueOf(army1.getUnitSizeByInt()));
    }

    /**
     * Method for easily setting values showcasing unit numbers for army2
     *
     * @throws IOException the io exception
     */
    @FXML
    void army2UnitNumbers() throws IOException{
        nameOfArmy2.setText(army2.getName());
        infantryArmy2.setText(String.valueOf(army2.getInfantryUnit().size()));
        rangedArmy2.setText(String.valueOf(army2.getRangedUnit().size()));
        cavalryArmy2.setText(String.valueOf(army2.getCavalryUnit().size()));
        commanderArmy2.setText(String.valueOf(army2.getCommanderUnit().size()));
        wizardArmy2.setText(String.valueOf(army2.getWizardUnit().size()));
        swordmasterArmy2.setText(String.valueOf(army2.getSwordmasterUnit().size()));
        army2Total.setText(String.valueOf(army2.getUnitSizeByInt()));
    }

    /**
     * Redirects to main page if home menu button is clicked
     *
     * @throws IOException if scenes could not be switched
     */
    @FXML
    void onHomeButtonPressed()
    throws IOException {
        ViewSwitcher.switchTo(View.HOME);
    }

    /**
     * Redirects to about credits page if clicked on 'credits' menu button
     *
     * @throws IOException if scenes could not be switched
     */
    @FXML
    void onCreditsButtonPressed()
    throws IOException {
        ViewSwitcher.switchTo(View.CREDITS);
    }

    /**
     * Redirects to new army page if clicked on create new army button
     *
     * @throws IOException if scenes could not be switched
     */
    @FXML
    void onNewArmyButtonPressed()
    throws IOException{
        ViewSwitcher.switchTo(View.NEWARMY);
    }

    /**
     * On battle button pressed, simulation of battle is run
     * Check for exceptions where armies, terrain or battleType could be null.
     *
     * If it detects the choice of random battle type, it simulates a randomized battle
     * If it detects the choice of turn-base battle type, it simulates a turn-based battle
     * Gets string from the value of choiceBox Terrain, meaning it checks for what is selected by user, and gives a string of that.
     *
     * Sets unit numbers again after simulation to let user see current state of armies after the battle
     *
     * Checks for unhandled exceptions
     *
     * @throws IOException the io exception
     */
    @FXML
    void onBattleButtonPressed() throws IOException {
        warningLabel.setText("");

        if (army1 == null || army2 == null) {
            warningLabel.setText("It is required to choose armies!");
        } else if (terrain.getValue() == null) {
            warningLabel.setText("It is required to choose a terrain!");
        } else if (battleType.getValue() == null) {
            warningLabel.setText("It is required to choose a battle type!");
        } else {
            if (battleType.getValue() == "Random") {
                try {
                    battle = new Battle(army1, army2, Terrain.valueOf((String) terrain.getValue()));
                    battle.simulateRandom();
                    army1UnitNumbers();
                    army2UnitNumbers();
                } catch (IllegalArgumentException e) {
                    warningLabel.setText(error);
                    e.printStackTrace();
                }
            } else if (battleType.getValue() == "Turn-based") {
                try {
                    battle = new Battle(army1, army2, Terrain.valueOf((String) terrain.getValue()));
                    battle.simulateTurnbased();
                    army1UnitNumbers();
                    army2UnitNumbers();
                } catch (IllegalArgumentException e) {
                    warningLabel.setText(error);
                    e.printStackTrace();
                }
            }

            if (army1.hasUnits()) {
                winnerLabel.setText(army1.getName() + " has won the battle!");
            } else {
                winnerLabel.setText(army2.getName() + " has won the battle!");
            }
            battleButton.setDisable(true);
            loadButton1.setDisable(true);
            loadButton2.setDisable(true);
            pathButton1.setDisable(true);
            pathButton2.setDisable(true);
            resetButton.setDisable(false);
        }
    }

    /**
     * On exit button pressed, the application is closed
     *
     * @throws IOException the io exception
     */
    @FXML
    void onExitButtonPressed() throws IOException{
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Load from file button 1 pressed.
     *
     * Opens up a filechooser window for user, where they can choose a local file from their computer
     * saves the path to that file, and gets absolute path of it. Which can be used by user to view path, and program to remember
     * where it loaded the army from, on reset.
     *
     * @throws IOException the io exception
     */
    @FXML
    void loadFromFileButton1Pressed() throws IOException{
        warningLabel.setText("");
        File file = FC.showOpenDialog(fileStage);
        try {
            army1 = ArmyReader.readArmyFromFile(file);
            army1UnitNumbers();
            pathArmy1 = file.getAbsolutePath();
            pathButton1.setDisable(false);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Load from file button 2 pressed.
     *
     * Opens up a filechooser window for user, where they can choose a local file from their computer
     * saves the path to that file, and gets absolute path of it. Which can be used by user to view path, and program to remember
     * where it loaded the army from, on reset.
     *
     * @throws IOException the io exception
     */
    @FXML
    void loadFromFileButton2Pressed() throws IOException{
        warningLabel.setText("");
        File file = FC.showOpenDialog(fileStage);
        try {
            army2 = ArmyReader.readArmyFromFile(file);
            army2UnitNumbers();
            pathArmy2 = file.getAbsolutePath();
            pathButton2.setDisable(false);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Path button 1 pressed.
     *
     * gets the saved path of the loaded army, and shows it to user in an Information Alert window, which pops up by itself
     *
     * @throws IOException the io exception
     */
    @FXML
    void pathButton1Pressed() throws IOException{
        warningLabel.setText("");
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Army Path");
        a.setHeaderText(army1.getName());
        a.setContentText("Path: \n" + pathArmy1);
        a.showAndWait();
    }

    /**
     * Path button 2 pressed.
     *
     * gets the saved path of the loaded army, and shows it to user in an Information Alert window, which pops up by itself
     *
     * @throws IOException the io exception
     */
    @FXML
    void pathButton2Pressed() throws IOException{
        warningLabel.setText("");
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Army Path");
        a.setHeaderText(army2.getName());
        a.setContentText("Path: \n" + pathArmy2);
        a.showAndWait();
    }
}