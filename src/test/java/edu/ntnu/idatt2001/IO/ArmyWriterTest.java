package edu.ntnu.idatt2001.IO;

import edu.ntnu.idatt2001.Army.*;
import edu.ntnu.idatt2001.Units.*;
import java.io.File;
import java.io.IOException;


import edu.ntnu.idatt2001.IO.ArmyWriter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArmyWriterTest {
    public static String path = "src/main/resources/army_files/";


    public void fillArmiesWithUnits(Army army1, Army army2) {
        for (int i = 0; i < 500; i++) {
            army1.add(new InfantryUnit("test1", 100));
            army2.add(new InfantryUnit("test2", 100));
        } for (int i = 0; i < 200; i++) {
            army1.add(new RangedUnit("test1", 100));
            army2.add(new RangedUnit("test2", 100));
        } for (int i = 0; i < 100; i++) {
            army1.add(new CavalryUnit("test1", 100));
            army2.add(new CavalryUnit("test2", 100));
        } for (int i = 0; i < 50; i++) {
            army1.add(new WizardUnit("test1", 100));
            army2.add(new WizardUnit("test2", 100));
        } for (int i = 0; i < 50; i++) {
            army1.add(new SwordmasterUnit("test1", 100));
            army2.add(new SwordmasterUnit("test2", 100));
        }
        army1.add(new CommanderUnit("Jaina Proudmoore", 180));
        army2.add(new CommanderUnit("Sylvanas Windrunner", 180));
    }

    public void fillArmyWithUnits(Army army1) {
        for (int i = 0; i < 500; i++) {
            army1.add(new InfantryUnit("test", 100));
        } for (int i = 0; i < 200; i++) {
            army1.add(new RangedUnit("test", 100));
        } for (int i = 0; i < 100; i++) {
            army1.add(new CavalryUnit("test", 100));
        } for (int i = 0; i < 50; i++) {
            army1.add(new WizardUnit("test1", 100));
        } for (int i = 0; i < 50; i++) {
            army1.add(new SwordmasterUnit("test1", 100));
        }
        army1.add(new CommanderUnit("Jaina Proudmoore", 180));
    }

    @Test
    void testWriteArmyToFile() {
        Army army1 = new Army("Human");
        Army army2 = new Army("Orc");
        fillArmiesWithUnits(army1, army2);

        assertDoesNotThrow(() -> {
            ArmyWriter.writeArmyToFile(army1, new File("src/main/resources/army_files/human_army.csv"));
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