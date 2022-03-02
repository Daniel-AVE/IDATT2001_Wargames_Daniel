package Battles_test;
import Army.*;
import Battles.*;
import Units.*;
import java.util.*;

import Units.CavalryUnit;
import Units.CommanderUnit;
import Units.InfantryUnit;
import Units.RangedUnit;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Battle test.
 */
public class Battle_test {

    /**
     * Fill armies with units.
     *
     * @param army1 the army 1
     * @param army2 the army 2
     */
    public void fillArmiesWithUnits(Army army1, Army army2) {
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

    /**
     * Fill army with units.
     *
     * @param army1 the army 1
     */
    public void fillArmyWithUnits(Army army1) {
        for (int i = 0; i < 500; i++) {
            army1.add(new InfantryUnit("", 100));
        } for (int i = 0; i < 200; i++) {
            army1.add(new RangedUnit("", 100));
        } for (int i = 0; i < 100; i++) {
            army1.add(new CavalryUnit("", 100));
        }
        army1.add(new CommanderUnit("Jaina Proudmoore", 180));
    }

    /**
     * Simulate random battle.
     */
    @Test
    public void simulateRandomBattle() {
        Army armyOne = new Army("Human");
        Army armyTwo = new Army("Orc");

        assertFalse(armyOne.hasUnits());
        assertFalse(armyTwo.hasUnits());

        fillArmyWithUnits(armyOne);

        armyTwo.add(new InfantryUnit("Footman", 1, 0, 0));

        assertTrue(armyOne.hasUnits());
        assertTrue(armyTwo.hasUnits());

        Battle battle = new Battle(armyOne, armyTwo);

        for (int i = 0; i < 1; i++) {
            battle.simulateRandom();
        }
        assertEquals(armyOne, battle.simulateRandom());

    }
}
