package Army_tests;
import Army.*;
import Units.*;
import java.util.*;

import resources.*;
import Units.CavalryUnit;
import Units.CommanderUnit;
import Units.InfantryUnit;
import Units.RangedUnit;
import org.junit.Test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Army_Writer_Test {


    public void fillArmyWithUnits(Army army1, Army army2) {
        for (int i = 0; i < 500; i++) {
            army1.add(new InfantryUnit("test1", 100));
            army2.add(new InfantryUnit("test2", 100));
        } for (int i = 0; i < 200; i++) {
            army1.add(new RangedUnit("test1", 100));
            army2.add(new RangedUnit("test2", 100));
        } for (int i = 0; i < 100; i++) {
            army1.add(new CavalryUnit("test1", 100));
            army2.add(new CavalryUnit("test2", 100));
        }
        army1.add(new CommanderUnit("Jaina Proudmoore", 180));
        army2.add(new CommanderUnit("Sylvanas Windrunner", 180));
    }

    @Test
    public void writeArmyToFile() {
        Army army1 = new Army();

        assertDoesNotThrow(() -> {
            writeArmyToFile(army1, army.csv);
        })
    }
}