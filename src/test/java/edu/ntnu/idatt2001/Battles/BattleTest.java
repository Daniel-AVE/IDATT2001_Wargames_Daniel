package edu.ntnu.idatt2001.Battles;


import edu.ntnu.idatt2001.Army.*;
import edu.ntnu.idatt2001.Battles.*;
import edu.ntnu.idatt2001.Units.*;
import java.util.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test class for class Battle in package Battle
 * @Author Daniel Evensen
 */
public class BattleTest {

    /**
     * Method to fill two armies with pre-set units to be used for tests
     *
     * @param army1 the army 1
     * @param army2 the army 2
     */
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
        }
        army1.add(new CommanderUnit("Jaina Proudmoore", 180));
        army2.add(new CommanderUnit("Sylvanas Windrunner", 180));
    }

    /**
     * Fill 1 army with units.
     * Used to test / simulate a battle of 1 full army against an army consisting of close to 0 units
     * Used to test the simulation of a random battle
     *
     * @param army1 the army 1
     */
    public void fillArmyWithUnits(Army army1) {
        for (int i = 0; i < 500; i++) {
            army1.add(new InfantryUnit("test", 100));
        } for (int i = 0; i < 200; i++) {
            army1.add(new RangedUnit("test", 100));
        } for (int i = 0; i < 100; i++) {
            army1.add(new CavalryUnit("test", 100));
        }
        army1.add(new CommanderUnit("Jaina Proudmoore", 180));
    }

    /**
     * Test for checking if the winning army is the one we're expecting after simulating a random battle
     * Although it's a random simulation, and after perhaps millions or billions of iterations, the army with 1 unit
     * would get 1 single win, the chance of it is too small to pay any heed. But since there is that chance, there is
     * a chance for the test to fail. Therefore there is a question if this test is needed or not since it's randomised,
     * and we can not 100% predict the outcome of the battle.
     *
     * uses assertFalse to check that armies are empty before adding units to the armies
     *
     * uses assertTrue to check that armies now has units after adding units to the armies
     *
     * uses assertEquals to check that the winning army from simulateRandom method from Battle class is the same as the
     * expected army.
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
