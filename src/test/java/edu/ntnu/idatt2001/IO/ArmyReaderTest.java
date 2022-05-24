package edu.ntnu.idatt2001.IO;

import edu.ntnu.idatt2001.Army.*;
import edu.ntnu.idatt2001.IO.*;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Army reader test.
 *
 * @author Daniel Evensen
 */
public class ArmyReaderTest {
    /**
     * The constant path.
     * Creates a String of path to the directory meant for army files
     */
    public static String path = "src/main/resources/edu/ntnu/idatt2001/army_files/";

    /**
     * Test read army from file.
     *
     * Creates instance of Army, and sets value as null
     *
     * tries setting value of army to what is contained within "Orc.csv"
     * throws exception if fails. Couldn't get it to do this no matter which assert I used, so ended up like this
     *
     * uses AssertDoesNotThrows, using a lamba expression where we make sure it reads the file correctly, without
     * throwing any exceptions
     *
     * uses assertTrue to make sure that the army is not null anymore, making sure it actually contains something
     *
     * uses assertEquals to make sure that the correct amount of units is saved within the army
     *
     */
    @Test
    void testReadArmyFromFile() {
        Army army = null;

        try {
            army = ArmyReader.readArmyFromFile(new File(path + "Orc.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertDoesNotThrow(() -> {
            ArmyReader.readArmyFromFile(new File(path + "Orc.csv"));
        });

        assertTrue(army != null);
        assertEquals(901, army.getUnitSizeByInt());
    }

    /**
     * Test read army from wrong file format.
     *
     * uses AssertThrows with the IOException class to make sure it throws an IOException when trying to read a file with
     * wrong format
     */
    @Test
    void testReadArmyFromWrongFileFormat() {
        assertThrows(IOException.class, () -> {
            ArmyReader.readArmyFromFile(new File(path + "testReader.txt"));
        });
    }

    /**
     * Test read army from non existent file.
     *
     * uses assertThrows with the IOException class to make sure it throws an IOException when trying to read a
     * non-existent file
     */
    @Test
    void testReadArmyFromNonExistentFile() {
        ArmyReader armyReader = new ArmyReader();

        assertThrows(IOException.class, () -> {
            armyReader.readArmyFromFile(new File(path + "dragon_army.csv"));
        });
    }

    /**
     * Test read army from empty file.
     *
     * uses assertThrows with IOException class to make sure it throws an IOException when trying to read from
     * an empty file
     */
    @Test
    void testReadArmyFromEmptyFile() {
        ArmyReader armyReader = new ArmyReader();

        assertThrows(IOException.class, () -> {
            armyReader.readArmyFromFile(new File(path + "Empty:army.csv"));
        });
    }

    /**
     * Test return true if army exists.
     *
     * uses assertTrue to make sure that the army exists
     */
    @Test
    void testReturnTrueIfArmyExists() {

        Army army = new Army("Human");

        assertTrue(ArmyReader.armyExists(army));
    }

    /**
     * Test return false if army does not exist.
     *
     * uses assertFalse to make sure that it's not reading any non-existent files, and setting it as true
     */
    @Test
    void testReturnFalseIfArmyDoesNotExist() {
        Army army = new Army("ThisArmyDoesNotExist");

        assertFalse(ArmyReader.armyExists(army));
    }
}
