package edu.ntnu.idatt2001.Unit_Factory;

import edu.ntnu.idatt2001.Units.*;

import java.lang.reflect.Array;
import java.util.*;

/**
 * The type Unit factory.
 * Inspired from example codes released by teacher in IDATT2001
 *
 * @author Daniel Evensen
 */
public class UnitFactory {

    /**
     * Method for creating a singular unit
     *
     * If unit type is null, throws new exception
     *
     * Uses enhanced switch case for any cases of unitType. And returns a new object of that unit.
     *
     * @param unitType the unit type
     * @param name     the name
     * @param health   the health
     * @return the unit
     */
    public static Unit createUnit(UnitType unitType, String name, int health) {
        if (unitType == null) {
            throw new IllegalArgumentException("Type of unit is necessary, cannot be null");
        } else {
            switch (unitType) {
                case InfantryUnit -> {
                    return new InfantryUnit(name, health);
                    }
                case RangedUnit -> {
                    return new RangedUnit(name, health);
                }
                case CavalryUnit -> {
                    return new CavalryUnit(name, health);
                }
                case CommanderUnit -> {
                    return new CommanderUnit(name, health);
                }
                case WizardUnit -> {
                    return new WizardUnit(name, health);
                }
                case SwordmasterUnit -> {
                    return new SwordmasterUnit(name, health);
                }
            }
        }
        return null;
    }

    /**
     * Method for creating multiple units at once, and saving it as an arraylist
     *
     * checks if number of units specified is a positive number, above 0, else throws exception
     *
     * creates an arraylist which will hold the units to be returned later
     *
     * iterates x amount of times, depending on number specified in numberOfUnits
     * for each iteration, it accesses createUnit method from above, and creates a singular unit, and stores it in
     * arraylist.
     *
     * returns the array list of units
     *
     * @param unitType      the unit type
     * @param numberOfUnits the number of units
     * @param name          the name
     * @param health        the health
     * @return the array list
     */
    public static ArrayList<Unit> createXAmountOfUnits(UnitType unitType, int numberOfUnits, String name, int health) {
        if (numberOfUnits <= 0) {
            throw new IllegalArgumentException("You cannot create an army with 0 or less units");
        }
        ArrayList<Unit> listOfUnits = new ArrayList<>();
        for (int i = 0; i < numberOfUnits; i++) {
            listOfUnits.add(createUnit(unitType, name, health));
        }
        return listOfUnits;
    }

}
