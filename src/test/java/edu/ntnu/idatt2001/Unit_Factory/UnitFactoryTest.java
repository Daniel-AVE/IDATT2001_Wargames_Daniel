package edu.ntnu.idatt2001.Unit_Factory;

import edu.ntnu.idatt2001.Unit_Factory.*;
import edu.ntnu.idatt2001.Army.*;
import edu.ntnu.idatt2001.Units.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class UnitFactoryTest {

    @Test
    public void testCreateSingularUnit() {
        Army army = new Army("test");

        assertFalse(army.hasUnits());

        army.add(UnitFactory.createUnit(UnitType.SwordmasterUnit, "Sword Hero", 100));

        assertTrue(army.hasUnits());
    }

    @Test
    public void testCreateSingularUnitWithNullUnitTypeThrowsException() {
        Army army = new Army("test");

        assertThrows(IllegalArgumentException.class, () -> {
            army.add(UnitFactory.createUnit(null, "Sword Hero", 100));
        });
    }

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
