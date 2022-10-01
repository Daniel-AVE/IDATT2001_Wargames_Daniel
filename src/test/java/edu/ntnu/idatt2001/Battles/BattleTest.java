package edu.ntnu.idatt2001.Battles;


import edu.ntnu.idatt2001.Army.*;
import edu.ntnu.idatt2001.Battles.*;
import edu.ntnu.idatt2001.Terrain.Terrain;
import edu.ntnu.idatt2001.Unit_Factory.UnitFactory;
import edu.ntnu.idatt2001.Unit_Factory.UnitType;
import edu.ntnu.idatt2001.Units.*;
import java.util.*;

import org.junit.jupiter.api.Test;
import org.w3c.dom.ranges.Range;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test class for class Battle in package Battle
 * @author Daniel Evensen
 */
public class BattleTest {

    /**
     * Method to fill two armies with pre-set units to be used for tests
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
     * Fill 1 army with units.
     * Used to test / simulate a battle of 1 full army against an army consisting of close to 0 units
     * Used to test the simulation of a random battle
     *
     * @param army1 the army 1
     */
    public void fillArmyWithUnits(Army army1) {
        ArrayList<Unit> army = new ArrayList<>();

        army.addAll(UnitFactory.createXAmountOfUnits(UnitType.InfantryUnit, 500, "test", 100));
        army.addAll(UnitFactory.createXAmountOfUnits(UnitType.RangedUnit, 200, "test", 100));
        army.addAll(UnitFactory.createXAmountOfUnits(UnitType.CavalryUnit, 100, "test", 100));
        army.addAll(UnitFactory.createXAmountOfUnits(UnitType.WizardUnit, 50, "test", 100));
        army.add(UnitFactory.createUnit(UnitType.CommanderUnit, "test", 100));

        army1.addAll(army);
    }

    /**
     * Test for checking if the winning army is the one we're expecting after simulating a random battle
     * Although it's a random simulation, and after perhaps millions or billions of iterations, the army with 1 unit
     * would get 1 single win, the chance of it is too small to pay any heed. But since there is that chance, there is
     * a chance for the test to fail. Therefore there is a question if this test is needed or not since it's randomised,
     * and we can not 100% predict the outcome of the battle.
     *
     * Simulates the battle in all 3 types of terrain
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

        armyTwo.add(UnitFactory.createUnit(UnitType.SwordmasterUnit, "Footman", 100));

        assertTrue(armyOne.hasUnits());
        assertTrue(armyTwo.hasUnits());

        Battle battle1 = new Battle(armyOne, armyTwo, Terrain.forest);
        Battle battle2 = new Battle(armyOne, armyTwo, Terrain.hill);
        Battle battle3 = new Battle(armyOne, armyTwo, Terrain.plains);

        for (int i = 0; i < 1; i++) {
            battle1.simulateRandom();
            battle2.simulateRandom();
            battle3.simulateRandom();
        }
        assertEquals(armyOne, battle1.simulateRandom());
        assertEquals(armyOne, battle2.simulateRandom());
        assertEquals(armyOne, battle3.simulateRandom());

    }
}
