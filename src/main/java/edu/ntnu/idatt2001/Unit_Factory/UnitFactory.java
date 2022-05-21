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
            }
        }
        return null;
    }

    public static List<Unit> createXAmountOfUnits(UnitType unitType, int numberOfUnits, String name, int health) {
        ArrayList<Unit> listOfUnits = new ArrayList<>();
        for (int i = 0; i < numberOfUnits; i++) {
            listOfUnits.add(createUnit(unitType, name, health));
        }
        return listOfUnits;
    }

}
