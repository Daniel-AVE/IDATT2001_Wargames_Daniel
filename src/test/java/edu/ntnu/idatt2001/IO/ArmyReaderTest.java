package edu.ntnu.idatt2001.IO;

import edu.ntnu.idatt2001.Army.*;
import edu.ntnu.idatt2001.IO.*;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class ArmyReaderTest {
    public static String path = "src/main/resources/edu/ntnu/idatt2001/army_files/";

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

    @Test
    void testReadArmyFromWrongFileFormat() {
        assertThrows(IOException.class, () -> {
            ArmyReader.readArmyFromFile(new File(path + "testReader.txt"));
        });
    }

    @Test
    void testReadArmyFromNonExistentFile() {
        ArmyReader armyReader = new ArmyReader();

        assertThrows(IOException.class, () -> {
            armyReader.readArmyFromFile(new File(path + "dragon_army.csv"));
        });
    }

    @Test
    void testReadArmyFromEmptyFile() {
        ArmyReader armyReader = new ArmyReader();

        assertThrows(IOException.class, () -> {
            armyReader.readArmyFromFile(new File(path + "Empty:army.csv"));
        });
    }

    @Test
    void testReturnTrueIfArmyExists() {

        Army army = new Army("Human");

        assertTrue(ArmyReader.armyExists(army));
    }

    @Test
    void testReturnFalseIfArmyDoesNotExist() {
        Army army = new Army("Alien");

        assertFalse(ArmyReader.armyExists(army));
    }
}
