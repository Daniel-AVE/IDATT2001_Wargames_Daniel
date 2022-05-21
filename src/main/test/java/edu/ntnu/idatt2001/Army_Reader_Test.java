package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.Army.*;
import edu.ntnu.idatt2001.Units.*;
import edu.ntnu.idatt2001.army_file_managers.*;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class Army_Reader_Test {
    public static String path = "src/main/resources/army_files/";

    @Test
    void testReadArmyFromFile() {
        ArmyReader armyReader = new ArmyReader();
        Army army = null;

        try {
            army = armyReader.readArmyFromFile(new File(path + "orc_army.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertDoesNotThrow(() -> {
            armyReader.readArmyFromFile(new File(path + "orc_army.csv"));
        });

        assert(army != null);
        assertEquals(801, army.getUnitSizeByInt());
    }

    @Test
    void testReadArmyFromWrongFileFormat() {
        ArmyReader armyReader = new ArmyReader();
        Army army = null;

        assertThrows(IOException.class, () -> {
            armyReader.readArmyFromFile(new File(path + "testReader.txt"));
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
}