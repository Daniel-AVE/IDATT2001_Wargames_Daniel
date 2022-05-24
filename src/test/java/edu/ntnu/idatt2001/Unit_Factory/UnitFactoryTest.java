package edu.ntnu.idatt2001.Unit_Factory;

import edu.ntnu.idatt2001.Unit_Factory.*;
import edu.ntnu.idatt2001.Army.*;
import edu.ntnu.idatt2001.Units.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


/**
 * The type Unit factory test.
 *
 * @author Daniel Evensen
 */
public class UnitFactoryTest {

    /**
     * Test create singular unit.
     *
     * uses assertFalse before adding units to the army with unit factory to make sure there are no units in the army
     * uses assertTrue after adding units to the army with unit factory to make sure there are units in the army
     */
    @Test
    public void testCreateSingularUnit() {
        Army army = new Army("test");

        assertFalse(army.hasUnits());

        army.add(UnitFactory.createUnit(UnitType.SwordmasterUnit, "Sword Hero", 100));

        assertTrue(army.hasUnits());
    }

    /**
     * Test create singular unit with null unit type throws exception.
     *
     * uses assertThrows with IllegalArgumentException class to make sure an IllegalArgumentException is thrown when
     * trying to add a null unit type with unit factory
     */
    @Test
    public void testCreateSingularUnitWithNullUnitTypeThrowsException() {
        Army army = new Army("test");

        assertThrows(IllegalArgumentException.class, () -> {
            army.add(UnitFactory.createUnit(null, "Sword Hero", 100));
        });
    }

    /**
     * Test create army with multiple units.
     *
     * uses assertFalse before adding units to the army to make sure the army is empty
     * uses assertTrue after adding units to the army to make sure the army is not empty
     * uses assertEquals to make sure that the amount of units supposed to be in the army, is the correct amount
     */
    @Test
    public void testCreateArmyWithMultipleUnits() {
        Army armyOne = new Army("test");

        assertFalse(armyOne.hasUnits());

        ArrayList<Unit> army = new ArrayList<>();

        army.addAll(UnitFactory.createXAmountOfUnits(UnitType.InfantryUnit, 500, "test", 100));
        army.addAll(UnitFactory.createXAmountOfUnits(UnitType.RangedUnit, 200, "test", 100));
        army.addAll(UnitFactory.createXAmountOfUnits(UnitType.CavalryUnit, 100, "test", 100));
        army.addAll(UnitFactory.createXAmountOfUnits(UnitType.WizardUnit, 50, "test", 100));
        army.addAll(UnitFactory.createXAmountOfUnits(UnitType.SwordmasterUnit, 50, "test", 100));
        army.add(UnitFactory.createUnit(UnitType.CommanderUnit, "test", 100));

        armyOne.addAll(army);

        assertTrue(armyOne.hasUnits());
        assertEquals(901, armyOne.getUnitSizeByInt());
    }

    /**
     * Test create army with multiple units with null unit types throws exception.
     *
     * uses assertThrows with IllegalArgumentException class making sure a IllegalArgumentException is thrown when
     * trying to create an army with multiple units with null unit type
     */
    @Test
    public void testCreateArmyWithMultipleUnitsWithNullUnitTypesThrowsException() {
        Army armyOne = new Army("test");

        ArrayList<Unit> army = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> {
            army.addAll(UnitFactory.createXAmountOfUnits(null, 500, "test", 100));
            army.addAll(UnitFactory.createXAmountOfUnits(null, 200, "test", 100));
            army.addAll(UnitFactory.createXAmountOfUnits(null, 100, "test", 100));
            army.addAll(UnitFactory.createXAmountOfUnits(null, 50, "test", 100));
            army.addAll(UnitFactory.createXAmountOfUnits(null, 50, "test", 100));
            army.add(UnitFactory.createUnit(null, "test", 100));
        });

    }

}
