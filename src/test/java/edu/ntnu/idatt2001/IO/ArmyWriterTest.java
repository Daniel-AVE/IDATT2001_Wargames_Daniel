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

/**
 * The type Army writer test.
 *
 * @author Daniel Evensen
 */
public class ArmyWriterTest {
    /**
     * The constant path.
     * Creates a String of path to the directory meant for army files
     */
    public static String path = "src/main/resources/edu/ntnu/idatt2001/army_files/";


    /**
     * Fill armies with units.
     *
     * Method made for ease-of-life, creating 2 armies with the use of unit factory
     *
     * @param army1 the army 1
     * @param army2 the army 2
     */
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

    /**
     * Fill army with units.
     *
     * Method made for ease-of-life creating a singular army with the use of unit factory
     *
     * @param army1 the army 1
     */
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

    /**
     * Test write army to file.
     *
     * uses asssertDoesNotThrow to make sure there are no exceptions thrown when trying to write files
     */
    @Test
    void testWriteArmyToFile() {
        Army army1 = new Army("Human");
        Army army2 = new Army("Orc");
        fillArmiesWithUnits(army1, army2);

        assertDoesNotThrow(() -> {
            ArmyWriter.writeArmyToFile(army1, new File(path + "Orc.csv"));
            ArmyWriter.writeArmyToFile(army2, new File(path + "Human.csv"));
        });
    }

    /**
     * Test write army to wrong format file throws exception.
     *
     * uses assertThrows with IOException class to make sure it throws an IOException when trying to write an army
     * to a file with the wrong format
     */
    @Test
    void testWriteArmyToWrongFormatFileThrowsException() {
        Army army = new Army("Orc");

        fillArmyWithUnits(army);

        assertThrows(IOException.class, () -> {
            ArmyWriter.writeArmyToFile(army, new File(path + "Orc.txt"));
        });
    }

    /**
     * Test write null army to file throws exception.
     *
     * uses assertThrows with IOException class to make sure it throws an IOException when trying to write a null
     * army to a file
     */
    @Test
    void testWriteNullArmyToFileThrowsException() {
        Army army = null;

        assertThrows(IOException.class, () -> {
            ArmyWriter.writeArmyToFile(army, new File(path + "testArmy.csv"));
        });
    }

    /**
     * Test write empty army to file.
     *
     * uses assertDoesNotThrow to make sure no exceptions are thrown when trying to write an empty army to file
     * which basically means that it's not writing anything, but creating the empty file which may be useful later
     */
    @Test
    void testWriteEmptyArmyToFile() {
        Army army = new Army("Empty");

        assertDoesNotThrow( () -> {
            ArmyWriter.writeArmyToFile(army, new File(path + "Empty_army.csv"));
        });
    }

    /**
     * Test write army to file but wrong path throws exception.
     *
     * uses assertThrows with IOException class to make sure it throw an IOException when trying to write an army to
     * a file, but with the wrong path
     */
    @Test
    void testWriteArmyToFileButWrongPathThrowsException() {
        Army army = new Army("Wrong path");
        fillArmyWithUnits(army);

        assertThrows(IOException.class, () -> {
           ArmyWriter.writeArmyToFile(army, new File("src/main/java/edu/ntnu/idatt2001/Army/wrong_path.csv"));
        });
    }
}