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

/**
 * A controller class for new army page
 *
 * @author Daniel Evensen
 */
public class NewArmyController {
    /**
     * all @FXML variables here instantiates objects made in Scenebuilder, with an FX:ID attached to them
     */
    @FXML
    Label goldCoins, /**
     * The Infantry total.
     */
    infantryTotal, /**
     * The Ranged total.
     */
    rangedTotal, /**
     * The Cavalry total.
     */
    cavalryTotal, /**
     * The Commander total.
     */
    commanderTotal, /**
     * The Wizard total.
     */
    wizardTotal, /**
     * The Swordmaster total.
     */
    swordmasterTotal, /**
     * The Warning label.
     */
    warningLabel;
    /**
     * The Army name availability.
     */
    @FXML
    Button armyNameAvailability, /**
     * The Infantry add button.
     */
    infantryAddButton, /**
     * The Ranged add button.
     */
    rangedAddButton, /**
     * The Cavalry add button.
     */
    cavalryAddButton, /**
     * The Commander add button.
     */
    commanderAddButton, /**
     * The Wizard add button.
     */
    wizardAddButton,
    /**
     * The Swordmaster add button.
     */
    swordmasterAddButton, /**
     * The Infantry remove button.
     */
    infantryRemoveButton, /**
     * The Ranged remove button.
     */
    rangedRemoveButton, /**
     * The Cavalry remove button.
     */
    cavalryRemoveButton, /**
     * The Commander remove button.
     */
    commanderRemoveButton, /**
     * The Wizard remove button.
     */
    wizardRemoveButton,
    /**
     * The Swordmaster remove button.
     */
    swordmasterRemoveButton, /**
     * The Create army button.
     */
    createArmyButton, /**
     * The Battle button.
     */
    battleButton, /**
     * The Exit button.
     */
    exitButton;
    /**
     * The Infantry number.
     */
    @FXML
    ChoiceBox infantryNumber, /**
     * The Ranged number.
     */
    rangedNumber, /**
     * The Cavalry number.
     */
    cavalryNumber, /**
     * The Commander number.
     */
    commanderNumber, /**
     * The Wizard number.
     */
    wizardNumber, /**
     * The Swordmaster number.
     */
    swordmasterNumber;
    /**
     * The Name of army.
     */
    @FXML
    TextField nameOfArmy, /**
     * The Infantry name.
     */
    infantryName, /**
     * The Ranged name.
     */
    rangedName, /**
     * The Cavalry name.
     */
    cavalryName, /**
     * The Commander name.
     */
    commanderName, /**
     * The Wizard name.
     */
    wizardName, /**
     * The Swordmaster name.
     */
    swordmasterName;
    /**
     * The Infantry tooltip.
     */
    @FXML
    Tooltip infantryTooltip, /**
     * The Ranged tooltip.
     */
    rangedTooltip, /**
     * The Cavalry tooltip.
     */
    cavalryTooltip, /**
     * The Commander tooltip.
     */
    commanderTooltip, /**
     * The Wizard tooltip.
     */
    wizardTooltip, /**
     * The Swordmaster tooltip.
     */
    swordmasterTooltip;

    /**
     * creates a final integer of the cost of each unit, which cannot be changed
     */
    private final int costOfInfantryUnit = 15;
    private final int costOfRangedUnit = 15;
    private final int costOfCavalryUnit = 17;
    private final int costOfCommanderUnit = 22;
    private final int costOfWizardUnit = 19;
    private final int costOfSwordmasterUnit = 20;

    /**
     * creates an integer of FXML Lable goldCoins which we can use to calculate later on
     */
    private int goldCoinsLeft = 10000;
    /**
     * a string for path, so it's easier to write the path down when needed
     */
    private final String path = "src/main/resources/edu/ntnu/idatt2001/army_files/";
    /**
     * a string for error outside of exception handling. Used in warninglabel to let user know to check terminal for message
     */
    private final String error = "Error: Check terminal for exception";

    /**
     * Initializes all choice boxes and other variables which needs to be initialized for this scene.
     * Initializes a tooltip for all labels with unit names, which can be hovered over for information
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

        /**
         * Choiceboxes for amount of units to be added to army
         */
        infantryNumber.getItems().addAll("1", "5", "10", "25", "50", "100", "500");
        rangedNumber.getItems().addAll("1", "5", "10", "25", "50", "100", "500");
        cavalryNumber.getItems().addAll("1", "5", "10", "25", "50", "100", "500");
        commanderNumber.getItems().addAll("1", "5", "10", "25", "50", "100", "500");
        wizardNumber.getItems().addAll("1", "5", "10", "25", "50", "100", "500");
        swordmasterNumber.getItems().addAll("1", "5", "10", "25", "50", "100", "500");

        /**
         * Predefines the values of choiceboxes to 0, to make it impossible to try to add null units
         */
        infantryNumber.valueProperty().set("0");
        rangedNumber.valueProperty().set("0");
        cavalryNumber.valueProperty().set("0");
        commanderNumber.valueProperty().set("0");
        wizardNumber.valueProperty().set("0");
        swordmasterNumber.valueProperty().set("0");

        /**
         * Disables all buttons except check army button. Let's user know where they should start
         */
        disableAllButtons();

    }

    /**
     * On check army name button pressed
     *
     * Checks if the input army name is valid through the method isValidJavaIdentifier().
     * If armyname is blank or empty, let's user know through a warning label, and resets typing field
     * if armyName is not valid through the isValidJavaIdentifier method, let's user know through warning label, and resets typing field
     * if army already exists, let's user know that they can't use that name through warning label, and resets typing field
     * if it gets through all checks, it let's user know that the army name is available, and enables all buttons
     */
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

    /**
     * On create army button pressed.
     * Checks to see if unit names are valid. If not, it stops user, and let's them know
     * if valid unit names, it creates a new army from a predefined method placed further down on the document
     * tries writing the army to file, and catches any errors.
     * disable all buttons on press, except for battle and check army name. This makes it possible for user to either
     * create another army, or go back to battle page, and battle
     *
     * sets text of total number of units per unit back to 0
     * also resets value of choiceboxes back to 0
     * resets unit name fields to null, removing all text from text fields
     * refills goldCoins and sets goldCoins label text back to original amount
     * Let's user know that their army has been created
     *
     * @throws IOException the io exception
     */
    @FXML
    void onCreateArmyButtonPressed() throws IOException {
        if (!(checkUnitNamesIsValid())) {
            warningLabel.setText("Unit name cannot contain words such as 'null', 'true', 'false', \n" +
                    "nor can it start with a number, and has to follow normal file_naming rules");
        } else {
            warningLabel.setText("");
            Army army = createArmy();
            File file = new File(path + army.getName() + ".csv");

            try {
                ArmyWriter.writeArmyToFile(army, file);
            } catch (IOException e) {
                warningLabel.setText(error);
                e.printStackTrace();
            }
            disableAllButtons();
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
    }


    /**
     * Method is retrieved from a comment from a StackOverflow post, and then further edited upon by me.
     * Source: https://stackoverflow.com/questions/15437866/how-to-validate-if-a-string-would-be-a-valid-java-variable
     *
     * Checks for various non-accepted java string inputs, such as:
     * if the string is empty
     * if the string starts with a number
     * if the string contains null, true or false
     * and checks through all characters in the string for illegal characters, such as ?, @, space, etc. up towards java identifier method
     *
     * if string does none of the above, method boolean comes back as true, saying the string is valid
     *
     * @param s String to check if is valid
     * @return boolean
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
     *
     * @throws IOException if scenes could not be switched
     */
    @FXML
    void onHomeButtonPressed()
    throws IOException {
        ViewSwitcher.switchTo(View.HOME);
    }

    /**
     * Redirects to credits page if clicked on credits menu button
     *
     * @throws IOException if scenes could not be switched
     */
    @FXML
    void onCreditsButtonPressed()
    throws IOException {
        ViewSwitcher.switchTo(View.CREDITS);
    }

    /**
     * Redirects to battle page if clicked on battle button
     *
     * @throws IOException if scenes could not be switched
     */
    @FXML
    void onBattleButtonPressed() throws IOException {
        ViewSwitcher.switchTo(View.BATTLE);
    }

    /**
     * On exit button pressed.
     *
     * Exits the application
     *
     * @throws IOException if application could not be closed
     */
    @FXML
    void onExitButtonPressed() throws IOException {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();

    }

    /**
     * Disable all buttons.
     *
     * A method made for ease-of-life when disabling all the buttons except a select few
     */
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

    /**
     * Enable all buttons.
     *
     * A method made for ease-of-life when enabling all the buttons except a select few
     */
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

    /**
     * On infantry add button pressed.
     * resets warningLabel
     * checks if unit name is a valid name
     * checks if user has selected a number of units to add (cannot add 0 units to an army).
     *
     * creates an integer for numberOfUnits from the value of choiceBox, by making the object a String value, and
     * parseInting that String value. Makes it easier to use the value of this object further
     *
     * Checks if the total amount of cost (number of units * cost per unit) is lower or equal to amount of gold left
     * if below:
     * try catch method:
     * try:
     * Creates an integer from an object through same method as earlier, added together with earlier created integer
     *  giving us the current number of units
     * subtracts cost of the amount of units from goldLeft, giving the user their current amount of gold left
     * updates total units of specific unit with total number of units
     * updates text showing gold coins
     * enables all buttons (in case some were disabled)
     *
     * Catches NumberFormatException which could have been caused when converting between String and Integer, and
     *  prints this to terminal
     *
     * @throws IOException the io exception
     */
    @FXML
    void onInfantryAddButtonPressed() throws IOException {
        warningLabel.setText("");
        if (!(checkUnitNamesIsValid())) {
            warningLabel.setText("Unit name cannot contain words such as 'null', 'true', 'false', \n" +
                    "nor can it start with a number, and has to follow normal file_naming rules");
        } else if (infantryNumber.getValue() == null || Integer.parseInt((String) infantryNumber.getValue()) == 0) {
            warningLabel.setText("You must choose a number of units to add!");
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

    /**
     * On ranged add button pressed.
     * resets warningLabel
     * checks if unit name is a valid name
     * checks if user has selected a number of units to add (cannot add 0 units to an army).
     *
     * creates an integer for numberOfUnits from the value of choiceBox, by making the object a String value, and
     * parseInting that String value. Makes it easier to use the value of this object further
     *
     * Checks if the total amount of cost (number of units * cost per unit) is lower or equal to amount of gold left
     * if below:
     * try catch method:
     * try:
     * Creates an integer from an object through same method as earlier, added together with earlier created integer
     *  giving us the current number of units
     * subtracts cost of the amount of units from goldLeft, giving the user their current amount of gold left
     * updates total units of specific unit with total number of units
     * updates text showing gold coins
     * enables all buttons (in case some were disabled)
     *
     * Catches NumberFormatException which could have been caused when converting between String and Integer, and
     *  prints this to terminal
     *
     * @throws IOException the io exception
     */
    @FXML
    void onRangedAddButtonPressed() throws IOException {
        warningLabel.setText("");
        if (!(checkUnitNamesIsValid())) {
            warningLabel.setText("Unit name cannot contain words such as 'null', 'true', 'false', \n" +
                    "nor can it start with a number, and has to follow normal file_naming rules");
        } else if (rangedNumber.getValue() == null || Integer.parseInt((String) rangedNumber.getValue()) == 0) {
            warningLabel.setText("You must choose a number of units to add!");
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

    /**
     * On cavalry add button pressed.
     * resets warningLabel
     * checks if unit name is a valid name
     * checks if user has selected a number of units to add (cannot add 0 units to an army).
     *
     * creates an integer for numberOfUnits from the value of choiceBox, by making the object a String value, and
     * parseInting that String value. Makes it easier to use the value of this object further
     *
     * Checks if the total amount of cost (number of units * cost per unit) is lower or equal to amount of gold left
     * if below:
     * try catch method:
     * try:
     * Creates an integer from an object through same method as earlier, added together with earlier created integer
     *  giving us the current number of units
     * subtracts cost of the amount of units from goldLeft, giving the user their current amount of gold left
     * updates total units of specific unit with total number of units
     * updates text showing gold coins
     * enables all buttons (in case some were disabled)
     *
     * Catches NumberFormatException which could have been caused when converting between String and Integer, and
     *  prints this to terminal
     *
     * @throws IOException the io exception
     */
    @FXML
    void onCavalryAddButtonPressed() throws IOException {
        warningLabel.setText("");
        if (!(checkUnitNamesIsValid())) {
            warningLabel.setText("Unit name cannot contain words such as 'null', 'true', 'false', \n" +
                    "nor can it start with a number, and has to follow normal file_naming rules");
        } else if (cavalryNumber.getValue() == null || Integer.parseInt((String) cavalryNumber.getValue()) == 0) {
            warningLabel.setText("You must choose a number of units to add!");
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

    /**
     * On commander add button pressed.
     * resets warningLabel
     * checks if unit name is a valid name
     * checks if user has selected a number of units to add (cannot add 0 units to an army).
     *
     * creates an integer for numberOfUnits from the value of choiceBox, by making the object a String value, and
     * parseInting that String value. Makes it easier to use the value of this object further
     *
     * Checks if the total amount of cost (number of units * cost per unit) is lower or equal to amount of gold left
     * if below:
     * try catch method:
     * try:
     * Creates an integer from an object through same method as earlier, added together with earlier created integer
     *  giving us the current number of units
     * subtracts cost of the amount of units from goldLeft, giving the user their current amount of gold left
     * updates total units of specific unit with total number of units
     * updates text showing gold coins
     * enables all buttons (in case some were disabled)
     *
     * Catches NumberFormatException which could have been caused when converting between String and Integer, and
     *  prints this to terminal
     *
     * @throws IOException the io exception
     */
    @FXML
    void onCommanderAddButtonPressed() throws IOException {
        warningLabel.setText("");
        if (!(checkUnitNamesIsValid())) {
            warningLabel.setText("Unit name cannot contain words such as 'null', 'true', 'false', \n" +
                    "nor can it start with a number, and has to follow normal file_naming rules");
        } else if (commanderNumber.getValue() == null || Integer.parseInt((String) commanderNumber.getValue()) == 0) {
            warningLabel.setText("You must choose a number of units to add!");
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

    /**
     * On wizard add button pressed.
     * resets warningLabel
     * checks if unit name is a valid name
     * checks if user has selected a number of units to add (cannot add 0 units to an army).
     *
     * creates an integer for numberOfUnits from the value of choiceBox, by making the object a String value, and
     * parseInting that String value. Makes it easier to use the value of this object further
     *
     * Checks if the total amount of cost (number of units * cost per unit) is lower or equal to amount of gold left
     * if below:
     * try catch method:
     * try:
     * Creates an integer from an object through same method as earlier, added together with earlier created integer
     *  giving us the current number of units
     * subtracts cost of the amount of units from goldLeft, giving the user their current amount of gold left
     * updates total units of specific unit with total number of units
     * updates text showing gold coins
     * enables all buttons (in case some were disabled)
     *
     * Catches NumberFormatException which could have been caused when converting between String and Integer, and
     *  prints this to terminal
     *
     * @throws IOException the io exception
     */
    @FXML
    void onWizardAddButtonPressed() throws IOException {
        warningLabel.setText("");
        if (!(checkUnitNamesIsValid())) {
            warningLabel.setText("Unit name cannot contain words such as 'null', 'true', 'false', \n" +
                    "nor can it start with a number, and has to follow normal file_naming rules");
        } else if (wizardNumber.getValue() == null || Integer.parseInt((String) wizardNumber.getValue()) == 0) {
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

    /**
     * On swordmaster add button pressed.
     * resets warningLabel
     * checks if unit name is a valid name
     * checks if user has selected a number of units to add (cannot add 0 units to an army).
     *
     * creates an integer for numberOfUnits from the value of choiceBox, by making the object a String value, and
     * parseInting that String value. Makes it easier to use the value of this object further
     *
     * Checks if the total amount of cost (number of units * cost per unit) is lower or equal to amount of gold left
     * if below:
     * try catch method:
     * try:
     * Creates an integer from an object through same method as earlier, added together with earlier created integer
     *  giving us the current number of units
     * subtracts cost of the amount of units from goldLeft, giving the user their current amount of gold left
     * updates total units of specific unit with total number of units
     * updates text showing gold coins
     * enables all buttons (in case some were disabled)
     *
     * Catches NumberFormatException which could have been caused when converting between String and Integer, and
     *  prints this to terminal
     *
     * @throws IOException the io exception
     */
    @FXML
    void onSwordmasterAddButtonPressed() throws IOException {
        warningLabel.setText("");
        if (!(checkUnitNamesIsValid())) {
            warningLabel.setText("Unit name cannot contain words such as 'null', 'true', 'false', \n" +
                    "nor can it start with a number, and has to follow normal file_naming rules");
        } else if (swordmasterNumber.getValue() == null || Integer.parseInt((String) swordmasterNumber.getValue()) == 0) {
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

    /**
     * On infantry remove button pressed.
     * resets warningLabel
     * checks if unit name is a valid name
     * checks if user has selected a number of units to remove (cannot remove 0 units from an army).
     *
     * creates an integer for numberOfUnits from the value of choiceBox, by making the object a String value, and
     * parseInting that String value. Makes it easier to use the value of this object further
     *
     * Checks again if number of units is above 0 after being converted to integer
     * if below:
     * try catch method:
     * try:
     * Creates an integer from an object through same method as earlier, subtracted by earlier created integer
     *  giving us the current number of units
     * adds the worth of the number of units removed * their cost, to goldCoins, letting user see how much gold is left
     * updates total units of specific unit with total number of units
     * updates text showing gold coins
     * enables all buttons (in case some were disabled)
     *
     * Catches NumberFormatException which could have been caused when converting between String and Integer, and
     *  prints this to terminal
     *
     * @throws IOException the io exception
     */
    @FXML
    void onInfantryRemoveButtonPressed() throws IOException{
        warningLabel.setText("");
        if (!(checkUnitNamesIsValid())) {
            warningLabel.setText("Unit name cannot contain words such as 'null', 'true', 'false', \n" +
                    "nor can it start with a number, and has to follow normal file_naming rules");
        } else if (infantryNumber.getValue() == null || Integer.parseInt((String) infantryNumber.getValue()) == 0) {
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

    /**
     * On ranged remove button pressed.
     *
     * Checks again if number of units is above 0 after being converted to integer
     * if below:
     * try catch method:
     * try:
     * Creates an integer from an object through same method as earlier, subtracted by earlier created integer
     *  giving us the current number of units
     * adds the worth of the number of units removed * their cost, to goldCoins, letting user see how much gold is left
     * updates total units of specific unit with total number of units
     * updates text showing gold coins
     * enables all buttons (in case some were disabled)
     *
     * Catches NumberFormatException which could have been caused when converting between String and Integer, and
     *  prints this to terminal
     *
     * @throws IOException the io exception
     */
    @FXML
    void onRangedRemoveButtonPressed() throws IOException{
        warningLabel.setText("");
        if (!(checkUnitNamesIsValid())) {
            warningLabel.setText("Unit name cannot contain words such as 'null', 'true', 'false', \n" +
                    "nor can it start with a number, and has to follow normal file_naming rules");
        } else if (rangedNumber.getValue() == null || Integer.parseInt((String) rangedNumber.getValue()) == 0) {
            disableAllButtons();
            warningLabel.setText("You must choose a number of units to remove!");
            throw new IOException("You must choose a number of units to remove!");
        } else {
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

    /**
     * On cavalry remove button pressed.
     *
     * Checks again if number of units is above 0 after being converted to integer
     * if below:
     * try catch method:
     * try:
     * Creates an integer from an object through same method as earlier, subtracted by earlier created integer
     *  giving us the current number of units
     * adds the worth of the number of units removed * their cost, to goldCoins, letting user see how much gold is left
     * updates total units of specific unit with total number of units
     * updates text showing gold coins
     * enables all buttons (in case some were disabled)
     *
     * Catches NumberFormatException which could have been caused when converting between String and Integer, and
     *  prints this to terminal
     *
     * @throws IOException the io exception
     */
    @FXML
    void onCavalryRemoveButtonPressed() throws IOException{
        warningLabel.setText("");
        if (!(checkUnitNamesIsValid())) {
            warningLabel.setText("Unit name cannot contain words such as 'null', 'true', 'false', \n" +
                    "nor can it start with a number, and has to follow normal file_naming rules");
        } else if (cavalryNumber.getValue() == null || Integer.parseInt((String) cavalryNumber.getValue()) == 0) {
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

    /**
     * On commander remove button pressed.
     *
     * Checks again if number of units is above 0 after being converted to integer
     * if below:
     * try catch method:
     * try:
     * Creates an integer from an object through same method as earlier, subtracted by earlier created integer
     *  giving us the current number of units
     * adds the worth of the number of units removed * their cost, to goldCoins, letting user see how much gold is left
     * updates total units of specific unit with total number of units
     * updates text showing gold coins
     * enables all buttons (in case some were disabled)
     *
     * Catches NumberFormatException which could have been caused when converting between String and Integer, and
     *  prints this to terminal
     *
     * @throws IOException the io exception
     */
    @FXML
    void onCommanderRemoveButtonPressed() throws IOException{
        warningLabel.setText("");
        if (!(checkUnitNamesIsValid())) {
            warningLabel.setText("Unit name cannot contain words such as 'null', 'true', 'false', \n" +
                    "nor can it start with a number, and has to follow normal file_naming rules");
        } else if (commanderNumber.getValue() == null || Integer.parseInt((String) commanderNumber.getValue()) == 0) {
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

    /**
     * On wizard remove button pressed.
     *
     * Checks again if number of units is above 0 after being converted to integer
     * if below:
     * try catch method:
     * try:
     * Creates an integer from an object through same method as earlier, subtracted by earlier created integer
     *  giving us the current number of units
     * adds the worth of the number of units removed * their cost, to goldCoins, letting user see how much gold is left
     * updates total units of specific unit with total number of units
     * updates text showing gold coins
     * enables all buttons (in case some were disabled)
     *
     * Catches NumberFormatException which could have been caused when converting between String and Integer, and
     *  prints this to terminal
     *
     * @throws IOException the io exception
     */
    @FXML
    void onWizardRemoveButtonPressed() throws IOException{
        warningLabel.setText("");
        if (!(checkUnitNamesIsValid())) {
            warningLabel.setText("Unit name cannot contain words such as 'null', 'true', 'false', \n" +
                    "nor can it start with a number, and has to follow normal file_naming rules");
        } else if (wizardNumber.getValue() == null || Integer.parseInt((String) wizardNumber.getValue()) == 0) {
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

    /**
     * On swordmaster remove button pressed.
     *
     * Checks again if number of units is above 0 after being converted to integer
     * if below:
     * try catch method:
     * try:
     * Creates an integer from an object through same method as earlier, subtracted by earlier created integer
     *  giving us the current number of units
     * adds the worth of the number of units removed * their cost, to goldCoins, letting user see how much gold is left
     * updates total units of specific unit with total number of units
     * updates text showing gold coins
     * enables all buttons (in case some were disabled)
     *
     * Catches NumberFormatException which could have been caused when converting between String and Integer, and
     *  prints this to terminal
     *
     * @throws IOException the io exception
     */
    @FXML
    void onSwordmasterRemoveButtonPressed() throws IOException{
        warningLabel.setText("");
        if (!(checkUnitNamesIsValid())) {
            warningLabel.setText("Unit name cannot contain words such as 'null', 'true', 'false', \n" +
                    "nor can it start with a number, and has to follow normal file_naming rules");
        } else if (infantryNumber.getValue() == null || Integer.parseInt((String) swordmasterNumber.getValue()) == 0) {
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

    /**
     * Method for checking if unitNames are valid
     *
     * uses previously used isValidJavaIdentifier method for each infantryName, gotten with getText from their text-fields
     * if not-valid: returns false
     * else it returns true
     * @return true or false depending on valid unit name or not
     */
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

    /**
     * ease of life method for creating an army
     *
     * takes nameOfArmy from textField, and stores it as a string
     * creates a new instance of Army with the name previously stored
     *
     * Uses Unit Factory to add unit types to army.
     * Gets amount of units by converting choicebox values to String and then converting to Integer
     * Gets unit name from converting textfield to text
     * sets health of every unit to 100
     * @return returns army
     */
    private Army createArmy(){
        String armyName = nameOfArmy.getText();
        Army army = new Army(armyName);

        army.addAll(UnitFactory.createXAmountOfUnits(UnitType.InfantryUnit, Integer.parseInt(infantryTotal.getText()), infantryName.getText(), 100));
        army.addAll(UnitFactory.createXAmountOfUnits(UnitType.RangedUnit, Integer.parseInt(rangedTotal.getText()), rangedName.getText(), 100));
        army.addAll(UnitFactory.createXAmountOfUnits(UnitType.CavalryUnit, Integer.parseInt(cavalryTotal.getText()), cavalryName.getText(), 100));
        army.addAll(UnitFactory.createXAmountOfUnits(UnitType.CommanderUnit, Integer.parseInt(commanderTotal.getText()), commanderName.getText(), 100));
        army.addAll(UnitFactory.createXAmountOfUnits(UnitType.WizardUnit, Integer.parseInt(wizardTotal.getText()), wizardName.getText(), 100));
        army.addAll(UnitFactory.createXAmountOfUnits(UnitType.SwordmasterUnit, Integer.parseInt(swordmasterTotal.getText()), swordmasterName.getText(), 100));

        return army;
    }
}
