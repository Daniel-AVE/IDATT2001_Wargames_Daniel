package edu.ntnu.idatt2001.Unit_Factory;

import edu.ntnu.idatt2001.Units.*;

import java.lang.reflect.Array;
import java.util.*;

public class UnitFactory {

    public static Unit createUnit(UnitType unitType, String name, int health) {
        if (unitType == null) {
            throw new IllegalArgumentException("Type of unit is necessary, cannot be null");
        } else {
            Unit typeOfUnit = null;
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
