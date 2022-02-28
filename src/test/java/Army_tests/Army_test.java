package Army_tests;
import Army.*;
import Units.*;
import java.util.*;

import Units.CavalryUnit;
import Units.CommanderUnit;
import Units.InfantryUnit;
import Units.RangedUnit;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Army_test {

    public void fillArmyWithUnits(Army army1, Army army2) {
        for (int i = 0; i < 500; i++) {
            army1.add(new InfantryUnit("", 100));
            army2.add(new InfantryUnit("", 100));
        } for (int i = 0; i < 200; i++) {
            army1.add(new RangedUnit("", 100));
            army2.add(new RangedUnit("", 100));
        } for (int i = 0; i < 100; i++) {
            army1.add(new CavalryUnit("", 100));
            army2.add(new CavalryUnit("", 100));
        }
        army1.add(new CommanderUnit("Jaina Proudmoore", 180));
        army2.add(new CommanderUnit("Sylvanas Windrunner", 180));
    }

    @Test
    public void createArmy() {
        Army army1 = new Army("Human");
        Army army2 = new Army("Orc");

        assertFalse(army1.hasUnits());
        assertFalse(army2.hasUnits());

        fillArmyWithUnits(army1, army2);

        assertTrue(army1.hasUnits());
        assertTrue(army2.hasUnits());
    }

    @Test
    public void createArmyWithArrayList() {
        Army army1 = new Army("Human");
        Army army2 = new Army("Orc");

        assertFalse(army1.hasUnits());
        assertFalse(army2.hasUnits());

        ArrayList<Unit> fillArmyWithUnits = new ArrayList<>();

        fillArmyWithUnits(army1, army2);

        army1.addAll(fillArmyWithUnits);
        army2.addAll(fillArmyWithUnits);

        assertTrue(army1.hasUnits());
        assertTrue(army2.hasUnits());
    }

    @Test
    public void createArmyWithAddAllUsingEmptyList() {
        Army army1 = new Army("Human");
        Army army2 = new Army("Orc");

        ArrayList<Unit> fillArmyWithEmptyList = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> army1.addAll(fillArmyWithEmptyList));
        assertThrows(IllegalArgumentException.class, () -> army2.addAll(fillArmyWithEmptyList));
    }

    @Test
    public void createArmyWithNameAndEmptyArrayList() {
        ArrayList<Unit> emptyList = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> {
            Army army1 = new Army("test", emptyList);
        });
    }

    @Test
    public void createArmyWithNameAndArrayList() {
        ArrayList<Unit> armyList = new ArrayList<>();

        armyList.add(new InfantryUnit("", 100));

        assertDoesNotThrow(() -> {
            Army army1 = new Army("Test", armyList);
        });
    }

    @Test
    public void removeUnitFromArmy() {
        Army army1 = new Army("Human");
        Army army2 = new Army("Orc");
        Army army3 = new Army("Test");
        fillArmyWithUnits(army1, army2);

        assertTrue(army1.hasUnits());
        assertEquals(801, army1.getUnitSizeByInt());

        army1.remove(army1.getRandom());
        assertEquals(800,army1.getUnitSizeByInt());

        army3.add(new CommanderUnit("Thrall", 180));
        assertTrue(army3.hasUnits());
        army3.remove(army3.getRandom());
        assertFalse(army3.hasUnits());
    }
}
