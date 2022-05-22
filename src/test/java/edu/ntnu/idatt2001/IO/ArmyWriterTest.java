package edu.ntnu.idatt2001.IO;

import edu.ntnu.idatt2001.Army.*;
import edu.ntnu.idatt2001.Unit_Factory.UnitFactory;
import edu.ntnu.idatt2001.Unit_Factory.UnitType;
import edu.ntnu.idatt2001.Units.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


import edu.ntnu.idatt2001.IO.ArmyWriter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArmyWriterTest {
    public static String path = "src/main/resources/edu/ntnu/idatt2001/army_files/";


    public void fillArmiesWithUnits(Army army1, Army army2) {
        ArrayList<Unit> army = new ArrayList<>();

        army.addAll(UnitFactory.createXAmountOfUnits(UnitType.InfantryUnit, 500, "test", 100));
        army.addAll(UnitFactory.createXAmountOfUnits(UnitType.RangedUnit, 200, "test", 100));
        army.addAll(UnitFactory.createXAmountOfUnits(UnitType.CavalryUnit, 100, "test", 100));
        army.addAll(UnitFactory.createXAmountOfUnits(UnitType.WizardUnit, 50, "test", 100));
        army.addAll(UnitFactory.createXAmountOfUnits(UnitType.SwordmasterUnit, 50, "test", 100));
        army.add(UnitFactory.createUnit(UnitType.CommanderUnit, "test", 100));

        army1.addAll(army);
        army2.addAll(army);
    }

    public void fillArmyWithUnits(Army army1) {
        ArrayList<Unit> army = new ArrayList<>();

        army.addAll(UnitFactory.createXAmountOfUnits(UnitType.InfantryUnit, 500, "test", 100));
        army.addAll(UnitFactory.createXAmountOfUnits(UnitType.RangedUnit, 200, "test", 100));
        army.addAll(UnitFactory.createXAmountOfUnits(UnitType.CavalryUnit, 100, "test", 100));
        army.addAll(UnitFactory.createXAmountOfUnits(UnitType.WizardUnit, 50, "test", 100));
        army.addAll(UnitFactory.createXAmountOfUnits(UnitType.SwordmasterUnit, 50, "test", 100));
        army.add(UnitFactory.createUnit(UnitType.CommanderUnit, "test", 100));

        army1.addAll(army);
    }

    @Test
    void testWriteArmyToFile() {
        Army army1 = new Army("Human");
        Army army2 = new Army("Orc");
        fillArmiesWithUnits(army1, army2);

        assertDoesNotThrow(() -> {
            ArmyWriter.writeArmyToFile(army1, new File(path + "human_army.csv"));
            ArmyWriter.writeArmyToFile(army2, new File(path + "orc_army.csv"));
        });
    }

    @Test
    void testWriteArmyToWrongFormatFileThrowsException() {
        Army army = new Army("Orc");

        fillArmyWithUnits(army);

        assertThrows(IOException.class, () -> {
            ArmyWriter.writeArmyToFile(army, new File(path + "Orc_army.txt"));
        });
    }

    @Test
    void testWriteNullArmyToFileThrowsException() {
        Army army = null;

        assertThrows(IOException.class, () -> {
            ArmyWriter.writeArmyToFile(army, new File(path + "Orc_army.csv"));
        });
    }

    @Test
    void testWriteEmptyArmyToFile() {
        Army army = new Army("Empty");

        assertDoesNotThrow( () -> {
            ArmyWriter.writeArmyToFile(army, new File(path + "Empty_army.csv"));
        });
    }

    @Test
    void testWriteArmyToFileButWrongPathThrowsException() {
        Army army = new Army("Wrong path");
        fillArmyWithUnits(army);

        assertThrows(IOException.class, () -> {
           ArmyWriter.writeArmyToFile(army, new File("src/main/java/edu/ntnu/idatt2001/Army/wrong_path.csv"));
        });
    }
}