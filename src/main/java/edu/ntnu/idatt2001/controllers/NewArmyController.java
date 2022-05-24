package edu.ntnu.idatt2001.controllers;

import edu.ntnu.idatt2001.Army.Army;
import edu.ntnu.idatt2001.IO.ArmyReader;
import edu.ntnu.idatt2001.IO.ArmyWriter;
import edu.ntnu.idatt2001.Scenes.View;
import edu.ntnu.idatt2001.Scenes.ViewSwitcher;
import edu.ntnu.idatt2001.Unit_Factory.UnitFactory;
import edu.ntnu.idatt2001.Unit_Factory.UnitType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

/**
 * A controller class for both about and help page
 */
public class NewArmyController {
    @FXML
    Label goldCoins, infantryTotal, rangedTotal, cavalryTotal, commanderTotal, wizardTotal, swordmasterTotal, warningLabel;
    @FXML
    Button armyNameAvailability, infantryAddButton, rangedAddButton, cavalryAddButton, commanderAddButton, wizardAddButton,
    swordmasterAddButton, infantryRemoveButton, rangedRemoveButton, cavalryRemoveButton, commanderRemoveButton, wizardRemoveButton,
    swordmasterRemoveButton, createArmyButton, battleButton, exitButton;
    @FXML
    ChoiceBox infantryNumber, rangedNumber, cavalryNumber, commanderNumber, wizardNumber, swordmasterNumber;
    @FXML
    TextField nameOfArmy, infantryName, rangedName, cavalryName, commanderName, wizardName, swordmasterName;
    @FXML
    Tooltip infantryTooltip, rangedTooltip, cavalryTooltip, commanderTooltip, wizardTooltip, swordmasterTooltip;

    private final int costOfInfantryUnit = 15;
    private final int costOfRangedUnit = 15;
    private final int costOfCavalryUnit = 17;
    private final int costOfCommanderUnit = 22;
    private final int costOfWizardUnit = 19;
    private final int costOfSwordmasterUnit = 20;
    private int goldCoinsLeft = 10000;
    private final String path = "src/main/resources/edu/ntnu/idatt2001/army_files/";
    private final String error = "Error: Check terminal for exception";

    @FXML
    public void initialize() {
        infantryTooltip.setText("Infantry:\n" +
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

        cavalryTooltip.setText("Cavalry:\n" +
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

        wizardTooltip.setText("Wizard:\n" +
                "Health: 100\n" +
                "Attack: 18\n" +
                "Attack-Bonus: 12-6-0\n" +
                "Armor: 13 \n" +
                "Resist-Bonus: 8-5-0" +
                "Extra attack bonus in hill and plains\n");

        swordmasterTooltip.setText("Swordmaster:\n" +
                "Health: 100\n" +
                "Attack: 18\n" +
                "Attack-Bonus: 0-7-13" +
                "Armor: 16 \n" +
                "Resist-Bonus: 0-2-4" +
                "Extra resist bonus in forest");

        infantryNumber.getItems().addAll("1", "5", "10", "25", "50", "100", "500");
        rangedNumber.getItems().addAll("1", "5", "10", "25", "50", "100", "500");
        cavalryNumber.getItems().addAll("1", "5", "10", "25", "50", "100", "500");
        commanderNumber.getItems().addAll("1", "5", "10", "25", "50", "100", "500");
        wizardNumber.getItems().addAll("1", "5", "10", "25", "50", "100", "500");
        swordmasterNumber.getItems().addAll("1", "5", "10", "25", "50", "100", "500");

        infantryNumber.valueProperty().set("0");
        rangedNumber.valueProperty().set("0");
        cavalryNumber.valueProperty().set("0");
        commanderNumber.valueProperty().set("0");
        wizardNumber.valueProperty().set("0");
        swordmasterNumber.valueProperty().set("0");

        disableAllButtons();

    }

    @FXML
    void onCheckArmyNameButton() {
        warningLabel.setText("");
        String armyName = nameOfArmy.getText();
        Army checkIfArmyExists = new Army(nameOfArmy.getText());
        if (armyName.isBlank() || armyName.isEmpty()) {
            warningLabel.setText("Army name is required!");
            disableAllButtons();
            nameOfArmy.setText("");
        } else if (!(isValidJavaIdentifier(armyName))) {
            warningLabel.setText("Army name cannot contain words such as 'null', 'true', 'false', \n" +
                    "nor can it start with a number, and has to follow normal file_naming rules");
            disableAllButtons();
            nameOfArmy.setText("");
        } else if (ArmyReader.armyExists(checkIfArmyExists)) {
            warningLabel.setText("Army name is already taken. Please choose another name for your army");
            disableAllButtons();
            nameOfArmy.setText("");
        } else {
            warningLabel.setText("Army name is available for use");
            enableAllButtons();
        }
    }

    @FXML
    void onCreateArmyButtonPressed() throws IOException {
        warningLabel.setText("");
        Army army = createArmy();
        File file = new File(path + army.getName() + ".csv");

        try {
            ArmyWriter.writeArmyToFile(army, file);
        } catch (IOException e) {
            warningLabel.setText(error);
            e.printStackTrace();
        }
        enableAllButtons();
        createArmyButton.setDisable(true);
        nameOfArmy.setText("");
        battleButton.setDisable(false);

        infantryTotal.setText("0");
        rangedTotal.setText("0");
        cavalryTotal.setText("0");
        commanderTotal.setText("0");
        wizardTotal.setText("0");
        swordmasterTotal.setText("0");

        infantryNumber.valueProperty().set("0");
        rangedNumber.valueProperty().set("0");
        cavalryNumber.valueProperty().set("0");
        commanderNumber.valueProperty().set("0");
        wizardNumber.valueProperty().set("0");
        swordmasterNumber.valueProperty().set("0");

        infantryName.setText(null);
        rangedName.setText(null);
        cavalryName.setText(null);
        commanderName.setText(null);
        wizardName.setText(null);
        swordmasterName.setText(null);

        goldCoinsLeft = 10000;
        goldCoins.setText("10000");
        warningLabel.setText("Army " + army.getName() + " has been created");
    }





    /**
     * Method is retrieved from a comment from a StackOverflow post, and then further edited upon by me.
     * Source: https://stackoverflow.com/questions/15437866/how-to-validate-if-a-string-would-be-a-valid-java-variable
     * @param s String to check if is valid
     * @return
     */
    public static boolean isValidJavaIdentifier(String s) {
        if (s.isEmpty()) {
            return false;
        }
        if (!Character.isJavaIdentifierStart(s.charAt(0))) {
            return false;
        }
        if(s.toLowerCase().contains("null") || s.toLowerCase().contains("true") || s.toLowerCase().contains("false")) {
            return false;
        }
        for (int i = 1; i < s.length(); i++) {
            if (!Character.isJavaIdentifierPart(s.charAt(i))) {
                return false;
            }
        }
        return true;
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

    @FXML
    void onBattleButtonPressed() throws IOException {
        ViewSwitcher.switchTo(View.BATTLE);
    }

    @FXML
    void onExitButtonPressed() throws IOException {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    void disableAllButtons() {
        infantryAddButton.setDisable(true);
        rangedAddButton.setDisable(true);
        cavalryAddButton.setDisable(true);
        commanderAddButton.setDisable(true);
        wizardAddButton.setDisable(true);
        swordmasterAddButton.setDisable(true);

        infantryRemoveButton.setDisable(true);
        rangedRemoveButton.setDisable(true);
        cavalryRemoveButton.setDisable(true);
        commanderRemoveButton.setDisable(true);
        wizardRemoveButton.setDisable(true);
        swordmasterRemoveButton.setDisable(true);

        createArmyButton.setDisable(true);
    }

    @FXML
    void enableAllButtons() {
        infantryAddButton.setDisable(false);
        rangedAddButton.setDisable(false);
        cavalryAddButton.setDisable(false);
        commanderAddButton.setDisable(false);
        wizardAddButton.setDisable(false);
        swordmasterAddButton.setDisable(false);

        infantryRemoveButton.setDisable(false);
        rangedRemoveButton.setDisable(false);
        cavalryRemoveButton.setDisable(false);
        commanderRemoveButton.setDisable(false);
        wizardRemoveButton.setDisable(false);
        swordmasterRemoveButton.setDisable(false);

        createArmyButton.setDisable(false);
    }

    @FXML
    void onInfantryAddButtonPressed() throws IOException {
        warningLabel.setText("");
        if (!(checkUnitNamesIsValid())) {
            warningLabel.setText("Army name cannot contain words such as 'null', 'true', 'false', \n" +
                    "nor can it start with a number, and has to follow normal file_naming rules");
        } else if (infantryNumber.getValue() == null) {
            warningLabel.setText("You must choose a number of units to add!");
            disableAllButtons();
            throw new IOException("You must choose a number of units to add!");
        } else {
            warningLabel.setText("");
            int numberOfUnits = Integer.parseInt((String) infantryNumber.getValue());
            if (numberOfUnits * costOfInfantryUnit <= goldCoinsLeft) {
                try {
                    int totalNumberOfUnits = (Integer.parseInt(infantryTotal.getText())) + numberOfUnits;
                    goldCoinsLeft -= numberOfUnits * costOfInfantryUnit;
                    infantryTotal.setText(Integer.toString(totalNumberOfUnits));
                    goldCoins.setText(Integer.toString(goldCoinsLeft));
                    enableAllButtons();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } else {
                warningLabel.setText("You don't have enough gold coins for that!");
            }
        }
    }

    @FXML
    void onRangedAddButtonPressed() throws IOException {
        warningLabel.setText("");
        if (!(checkUnitNamesIsValid())) {
            warningLabel.setText("Army name cannot contain words such as 'null', 'true', 'false', \n" +
                    "nor can it start with a number, and has to follow normal file_naming rules");
        } else if (rangedNumber.getValue() == null) {
            warningLabel.setText("You must choose a number of units to add!");
            disableAllButtons();
            throw new IOException("You must choose a number of units to add!");
        } else {
            warningLabel.setText("");
            int numberOfUnits = Integer.parseInt((String) rangedNumber.getValue());
            if (numberOfUnits * costOfRangedUnit <= goldCoinsLeft) {
                try {
                    int totalNumberOfUnits = Integer.parseInt(rangedTotal.getText()) + numberOfUnits;
                    goldCoinsLeft -= numberOfUnits * costOfRangedUnit;
                    rangedTotal.setText(Integer.toString(totalNumberOfUnits));
                    goldCoins.setText(Integer.toString(goldCoinsLeft));
                    enableAllButtons();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } else {
                warningLabel.setText("You don't have enough gold coins for that!");
            }
        }
    }

    @FXML
    void onCavalryAddButtonPressed() throws IOException {
        warningLabel.setText("");
        if (!(checkUnitNamesIsValid())) {
            warningLabel.setText("Army name cannot contain words such as 'null', 'true', 'false', \n" +
                    "nor can it start with a number, and has to follow normal file_naming rules");
        } else if (cavalryNumber.getValue() == null) {
            warningLabel.setText("You must choose a number of units to add!");
            disableAllButtons();
            throw new IOException("You must choose a number of units to add!");
        } else {
            warningLabel.setText("");
            int numberOfUnits = Integer.parseInt((String) cavalryNumber.getValue());
            if (numberOfUnits * costOfCavalryUnit <= goldCoinsLeft) {
                try {
                    int totalNumberOfUnits = Integer.parseInt(cavalryTotal.getText()) + numberOfUnits;
                    goldCoinsLeft -= numberOfUnits * costOfCavalryUnit;
                    cavalryTotal.setText(Integer.toString(totalNumberOfUnits));
                    goldCoins.setText(Integer.toString(goldCoinsLeft));
                    enableAllButtons();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } else {
                warningLabel.setText("You don't have enough gold coins for that!");
            }
        }
    }

    @FXML
    void onCommanderAddButtonPressed() throws IOException {
        warningLabel.setText("");
        if (!(checkUnitNamesIsValid())) {
            warningLabel.setText("Army name cannot contain words such as 'null', 'true', 'false', \n" +
                    "nor can it start with a number, and has to follow normal file_naming rules");
        } else if (commanderNumber.getValue() == null) {
            warningLabel.setText("You must choose a number of units to add!");
            disableAllButtons();
            throw new IOException("You must choose a number of units to add!");
        } else {
            warningLabel.setText("");
            int numberOfUnits = Integer.parseInt((String) commanderNumber.getValue());
            if (numberOfUnits * costOfCommanderUnit <= goldCoinsLeft) {
                try {
                    int totalNumberOfUnits = Integer.parseInt(commanderTotal.getText()) + numberOfUnits;
                    goldCoinsLeft -= numberOfUnits * costOfCommanderUnit;
                    commanderTotal.setText(Integer.toString(totalNumberOfUnits));
                    goldCoins.setText(Integer.toString(goldCoinsLeft));
                    enableAllButtons();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } else {
                warningLabel.setText("You don't have enough gold coins for that!");
            }
        }
    }

    @FXML
    void onWizardAddButtonPressed() throws IOException {
        warningLabel.setText("");
        if (!(checkUnitNamesIsValid())) {
            warningLabel.setText("Army name cannot contain words such as 'null', 'true', 'false', \n" +
                    "nor can it start with a number, and has to follow normal file_naming rules");
        } else if (wizardNumber.getValue() == null) {
            disableAllButtons();
            warningLabel.setText("You must choose a number of units to add!");
            throw new IOException("You must choose a number of units to add!");
        } else {
            warningLabel.setText("");
            int numberOfUnits = Integer.parseInt((String) wizardNumber.getValue());
            if (numberOfUnits * costOfWizardUnit <= goldCoinsLeft) {
                try {
                    int totalNumberOfUnits = Integer.parseInt(wizardTotal.getText()) + numberOfUnits;
                    goldCoinsLeft -= numberOfUnits * costOfWizardUnit;
                    wizardTotal.setText(Integer.toString(totalNumberOfUnits));
                    goldCoins.setText(Integer.toString(goldCoinsLeft));
                    enableAllButtons();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } else {
                warningLabel.setText("You don't have enough gold coins for that!");
            }
        }
    }

    @FXML
    void onSwordmasterAddButtonPressed() throws IOException {
        warningLabel.setText("");
        if (!(checkUnitNamesIsValid())) {
            warningLabel.setText("Army name cannot contain words such as 'null', 'true', 'false', \n" +
                    "nor can it start with a number, and has to follow normal file_naming rules");
        } else if (swordmasterNumber.getValue() == null) {
            disableAllButtons();
            warningLabel.setText("You must choose a number of units to add!");
            throw new IOException("You must choose a number of units to add!");
        } else {
            warningLabel.setText("");
            int numberOfUnits = Integer.parseInt((String) swordmasterNumber.getValue());
            if (numberOfUnits * costOfSwordmasterUnit <= goldCoinsLeft) {
                try {
                    int totalNumberOfUnits = Integer.parseInt(swordmasterTotal.getText()) + numberOfUnits;
                    goldCoinsLeft -= numberOfUnits * costOfSwordmasterUnit;
                    swordmasterTotal.setText(Integer.toString(totalNumberOfUnits));
                    goldCoins.setText(Integer.toString(goldCoinsLeft));
                    enableAllButtons();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } else {
                warningLabel.setText("You don't have enough gold coins for that!");
            }
        }
    }

    @FXML
    void onInfantryRemoveButtonPressed() throws IOException{
        warningLabel.setText("");
        if (!(checkUnitNamesIsValid())) {
            warningLabel.setText("Army name cannot contain words such as 'null', 'true', 'false', \n" +
                    "nor can it start with a number, and has to follow normal file_naming rules");
        } else if (infantryNumber.getValue() == null) {
            disableAllButtons();
            warningLabel.setText("You must choose a number of units to remove!");
            throw new IOException("You must choose a number of units to remove!");
        } else {
            warningLabel.setText("");
            int numberOfUnits = Integer.parseInt((String) infantryNumber.getValue());
            if (numberOfUnits > 0) {
                try {
                    int totalNumberOfUnits = Integer.parseInt(infantryTotal.getText()) - numberOfUnits;
                    goldCoinsLeft += numberOfUnits * costOfInfantryUnit;
                    infantryTotal.setText(Integer.toString(totalNumberOfUnits));
                    goldCoins.setText(Integer.toString(goldCoinsLeft));
                    enableAllButtons();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } else {
                warningLabel.setText("There aren't enough units to remove");
            }
        }
    }

    @FXML
    void onRangedRemoveButtonPressed() throws IOException{
        warningLabel.setText("");
        if (!(checkUnitNamesIsValid())) {
            warningLabel.setText("Army name cannot contain words such as 'null', 'true', 'false', \n" +
                    "nor can it start with a number, and has to follow normal file_naming rules");
        } else if (rangedNumber.getValue() == null) {
            disableAllButtons();
            warningLabel.setText("You must choose a number of units to remove!");
            throw new IOException("You must choose a number of units to remove!");
        } else {
            warningLabel.setText("");
            int numberOfUnits = Integer.parseInt((String) rangedNumber.getValue());
            if (numberOfUnits > 0) {
                try {
                    int totalNumberOfUnits = Integer.parseInt(rangedTotal.getText()) - numberOfUnits;
                    goldCoinsLeft += numberOfUnits * costOfRangedUnit;
                    rangedTotal.setText(Integer.toString(totalNumberOfUnits));
                    goldCoins.setText(Integer.toString(goldCoinsLeft));
                    enableAllButtons();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } else {
                warningLabel.setText("There aren't enough units to remove");
            }
        }
    }

    @FXML
    void onCavalryRemoveButtonPressed() throws IOException{
        warningLabel.setText("");
        if (!(checkUnitNamesIsValid())) {
            warningLabel.setText("Army name cannot contain words such as 'null', 'true', 'false', \n" +
                    "nor can it start with a number, and has to follow normal file_naming rules");
        } else if (cavalryNumber.getValue() == null) {
            disableAllButtons();
            warningLabel.setText("You must choose a number of units to remove!");
            throw new IOException("You must choose a number of units to remove!");
        } else {
            warningLabel.setText("");
            int numberOfUnits = Integer.parseInt((String) cavalryNumber.getValue());
            if (numberOfUnits > 0) {
                try {
                    int totalNumberOfUnits = Integer.parseInt(cavalryTotal.getText()) - numberOfUnits;
                    goldCoinsLeft += numberOfUnits * costOfCavalryUnit;
                    cavalryTotal.setText(Integer.toString(totalNumberOfUnits));
                    goldCoins.setText(Integer.toString(goldCoinsLeft));
                    enableAllButtons();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } else {
                warningLabel.setText("There aren't enough units to remove");
            }
        }
    }

    @FXML
    void onCommanderRemoveButtonPressed() throws IOException{
        warningLabel.setText("");
        if (!(checkUnitNamesIsValid())) {
            warningLabel.setText("Army name cannot contain words such as 'null', 'true', 'false', \n" +
                    "nor can it start with a number, and has to follow normal file_naming rules");
        } else if (commanderNumber.getValue() == null) {
            disableAllButtons();
            warningLabel.setText("You must choose a number of units to remove!");
            throw new IOException("You must choose a number of units to remove!");
        } else {
            warningLabel.setText("");
            int numberOfUnits = Integer.parseInt((String) commanderNumber.getValue());
            if (numberOfUnits > 0) {
                try {
                    int totalNumberOfUnits = Integer.parseInt(commanderTotal.getText()) - numberOfUnits;
                    goldCoinsLeft += numberOfUnits * costOfCommanderUnit;
                    commanderTotal.setText(Integer.toString(totalNumberOfUnits));
                    goldCoins.setText(Integer.toString(goldCoinsLeft));
                    enableAllButtons();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } else {
                warningLabel.setText("There aren't enough units to remove");
            }
        }
    }

    @FXML
    void onWizardRemoveButtonPressed() throws IOException{
        warningLabel.setText("");
        if (!(checkUnitNamesIsValid())) {
            warningLabel.setText("Army name cannot contain words such as 'null', 'true', 'false', \n" +
                    "nor can it start with a number, and has to follow normal file_naming rules");
        } else if (wizardNumber.getValue() == null) {
            disableAllButtons();
            warningLabel.setText("You must choose a number of units to remove!");
            throw new IOException("You must choose a number of units to remove!");
        } else {
            warningLabel.setText("");
            int numberOfUnits = Integer.parseInt((String) wizardNumber.getValue());
            if (numberOfUnits > 0) {
                try {
                    int totalNumberOfUnits = Integer.parseInt(wizardTotal.getText()) - numberOfUnits;
                    goldCoinsLeft += numberOfUnits * costOfWizardUnit;
                    wizardTotal.setText(Integer.toString(totalNumberOfUnits));
                    goldCoins.setText(Integer.toString(goldCoinsLeft));
                    enableAllButtons();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } else {
                warningLabel.setText("There aren't enough units to remove");
            }
        }
    }

    @FXML
    void onSwordmasterRemoveButtonPressed() throws IOException{
        warningLabel.setText("");
        if (!(checkUnitNamesIsValid())) {
            warningLabel.setText("Army name cannot contain words such as 'null', 'true', 'false', \n" +
                    "nor can it start with a number, and has to follow normal file_naming rules");
        } else if (infantryNumber.getValue() == null) {
            disableAllButtons();
            warningLabel.setText("You must choose a number of units to remove!");
            throw new IOException("You must choose a number of units to remove!");
        } else {
            warningLabel.setText("");
            int numberOfUnits = Integer.parseInt((String) swordmasterNumber.getValue());
            if (numberOfUnits > 0) {
                try {
                    int totalNumberOfUnits = Integer.parseInt(swordmasterTotal.getText()) - numberOfUnits;
                    goldCoinsLeft += numberOfUnits * costOfSwordmasterUnit;
                    swordmasterTotal.setText(Integer.toString(totalNumberOfUnits));
                    goldCoins.setText(Integer.toString(goldCoinsLeft));
                    enableAllButtons();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } else {
                warningLabel.setText("There aren't enough units to remove");
            }
        }
    }

    private boolean checkUnitNamesIsValid() {
        if (!(isValidJavaIdentifier(infantryName.getText()))) {
            return false;
        } else if (!(isValidJavaIdentifier(rangedName.getText()))) {
            return false;
        } else if (!(isValidJavaIdentifier(cavalryName.getText()))) {
            return false;
        } else if (!(isValidJavaIdentifier(commanderName.getText()))) {
            return false;
        } else if (!(isValidJavaIdentifier(wizardName.getText()))) {
            return false;
        } else if (!(isValidJavaIdentifier(swordmasterName.getText()))) {
            return false;
        } else {
            return true;
        }

    }

    private Army createArmy(){
        String armyName = nameOfArmy.getText();
        Army army = new Army(armyName);

        if (!(checkUnitNamesIsValid())) {
            warningLabel.setText("");
        }
        army.addAll(UnitFactory.createXAmountOfUnits(UnitType.InfantryUnit, Integer.parseInt(infantryTotal.getText()), infantryName.getText(), 100));
        army.addAll(UnitFactory.createXAmountOfUnits(UnitType.RangedUnit, Integer.parseInt(rangedTotal.getText()), rangedName.getText(), 100));
        army.addAll(UnitFactory.createXAmountOfUnits(UnitType.CavalryUnit, Integer.parseInt(cavalryTotal.getText()), cavalryName.getText(), 100));
        army.addAll(UnitFactory.createXAmountOfUnits(UnitType.WizardUnit, Integer.parseInt(commanderTotal.getText()), commanderName.getText(), 100));
        army.addAll(UnitFactory.createXAmountOfUnits(UnitType.SwordmasterUnit, Integer.parseInt(wizardTotal.getText()), wizardName.getText(), 100));
        army.addAll(UnitFactory.createXAmountOfUnits(UnitType.CommanderUnit, Integer.parseInt(swordmasterTotal.getText()), swordmasterName.getText(), 100));

        return army;
    }
}
