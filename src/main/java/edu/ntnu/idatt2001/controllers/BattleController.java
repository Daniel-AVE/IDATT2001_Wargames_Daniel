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
 * A controller class for both about and help page
 */
public class BattleController {
    @FXML
    Button exitButton;
    @FXML
    Button battleButton;
    @FXML
    Button resetButton;
    @FXML
    Button pathButton1, pathButton2;
    @FXML
    Button loadButton1, loadButton2;
    @FXML
    Label warningLabel;
    @FXML
    Label winnerLabel;
    @FXML
    Label infantryArmy1, infantryArmy2;
    @FXML
    Label rangedArmy1, rangedArmy2;
    @FXML
    Label cavalryArmy1, cavalryArmy2;
    @FXML
    Label commanderArmy1, commanderArmy2;
    @FXML
    Label wizardArmy1, wizardArmy2;
    @FXML
    Label swordmasterArmy1, swordmasterArmy2;
    @FXML
    Label nameOfArmy1, nameOfArmy2;
    @FXML
    Label army1Total, army2Total;
    @FXML
    ChoiceBox battleType;
    @FXML
    ChoiceBox terrain;
    @FXML
    ImageView terrainImage;
    @FXML
    Tooltip infantryTooltip, infantryTooltip1, rangedTooltip, rangedTooltip1, cavalryTooltip, cavalryTooltip1,
            commanderTooltip, commanderTooltip1, wizardTooltip, wizardTooltip1, swordmasterTooltip, swordmasterTooltip1;

    private final String path = "src/main/resources/edu/ntnu/idatt2001/army_files/";
    private final String error = "Error: Check terminal for exception";
    private String pathArmy1;
    private String pathArmy2;
    private Stage fileStage;
    private Army army1;
    private Army army2;
    private final FileChooser FC = new FileChooser();
    private Battle battle;

    /**
     * Initializes all choice boxes and other variables which needs to be initialized for this scene.
     * Includes: Adding listeners, to be able to update images and armies without having to refresh page
     * @throws IOException
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
                "Armor: 13 \n" +
                "Resist-Bonus: 8-5-0" +
                "Extra attack bonus in hill and plains\n");

        wizardTooltip1.setText("Wizard:\n" +
                "Health: 100\n" +
                "Attack: 18\n" +
                "Attack-Bonus: 12-6-0\n" +
                "Armor: 13 \n" +
                "Resist-Bonus: 8-5-0" +
                "Extra attack bonus in hill and plains");

        swordmasterTooltip.setText("Swordmaster:\n" +
                "Health: 100\n" +
                "Attack: 18\n" +
                "Attack-Bonus: 0-7-13" +
                "Armor: 16 \n" +
                "Resist-Bonus: 0-2-4" +
                "Extra resist bonus in forest");

        swordmasterTooltip1.setText("Swordmaster:\n" +
                "Health: 100\n" +
                "Attack: 18\n" +
                "Attack-Bonus: 0-7-13" +
                "Armor: 16 \n" +
                "Resist-Bonus: 0-2-4" +
                "Extra resist bonus in forest");




        terrain.getItems().addAll("forest", "hill", "plains");
        terrain.getSelectionModel().selectedItemProperty().addListener((observableValue, oldTerrain, newTerrain) ->
                terrainImage.setImage(new Image(("file:src/main/resources/edu/ntnu/idatt2001/Images/" + newTerrain + ".jpg"))));

        battleType.getItems().addAll("Random", "Turn-based");
    }

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
     * @throws IOException
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

    /**
     * Redirects to create new tournament page if clicked on create new tournament button
     * @throws IOException if scenes could not be switched
     */
    @FXML
    void onNewArmyButtonPressed()
    throws IOException{
        ViewSwitcher.switchTo(View.NEWARMY);
    }

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

    @FXML
    void onExitButtonPressed() throws IOException{
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void loadFromFileButton1Pressed() throws IOException{
        warningLabel.setText("");
        File file = FC.showOpenDialog(fileStage);
        try {
            army1 = ArmyReader.readArmyFromFile(file);
            army1UnitNumbers();
            pathArmy1 = file.getAbsolutePath();
            pathButton1.setDisable(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void loadFromFileButton2Pressed() throws IOException{
        warningLabel.setText("");
        File file = FC.showOpenDialog(fileStage);
        try {
            army2 = ArmyReader.readArmyFromFile(file);
            army2UnitNumbers();
            pathArmy2 = file.getAbsolutePath();
            pathButton2.setDisable(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void pathButton1Pressed() throws IOException{
        warningLabel.setText("");
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Army Path");
        a.setHeaderText(army1.getName());
        a.setContentText("Path: \n" + pathArmy1);
        a.showAndWait();
    }

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