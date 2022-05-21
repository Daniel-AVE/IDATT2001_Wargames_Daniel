package edu.ntnu.idatt2001.Army;

import edu.ntnu.idatt2001.Units.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * The class Army
 * Contains methods to create an army
 * Contains method to add or remove units to an army
 * @Author Daniel Evensen
 */
public class Army {
    private String name;
    private ArrayList<Unit> units; // creates arraylist from class Unit

    /**
     * Instantiates a new Army.
     * Only uses name as a parameter
     *
     * @param name the name
     */
    public Army(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Army name cannot be blank or empty");
        }
        this.units = new ArrayList<>();
        this.name = name;
    }

    /**
     * Instantiates a new Army.
     * Uses arraylist as a parameter as well, making it possible to instantiate the army with pre-existing units
     * Throws new illegal argument exception if list is empty
     *
     * @param name  the name
     * @param units the units
     */
    public Army(String name, ArrayList<Unit> units) {
        this.units = new ArrayList<>();
        if (units.isEmpty() || units == null) {
            throw new IllegalArgumentException("Cannot add an empty list");
        }
        this.name = name;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets all units.
     * creates a deep copy of the arraylist
     *
     * @return Returns a copy of the arraylist
     */
    public ArrayList<Unit> getAllUnits() {
        ArrayList<Unit> unitsCopy = new ArrayList<>();

        for (Unit units : units) {
            unitsCopy.add(units.copy());
        }
        return unitsCopy;
    }

    /**
     * Gets unit size by int.
     *
     * @return an integer value of the size of arraylist
     */
    public int getUnitSizeByInt() {
        return units.size();
    }

    /**
     * Method for adding a unit to the arraylist
     * Used to add units to an army
     *
     * @param unit the unit
     */
    public void add(Unit unit) {
        units.add(unit.copy());
    }

    /**
     * Method for adding an arraylist of units, making it possible to add a lot of pre-existing units at once
     * Used to add units to an army, from an arraylist
     * Checks that the arraylist being used to add is not empty, if it is, it throws a new exception
     *
     * @param inputUnits arraylist of units to be added to the army
     */
    public void addAll(ArrayList<Unit> inputUnits) {
        for (Unit unit : inputUnits) {
            units.add(unit.copy());
        } if (inputUnits.isEmpty() || inputUnits == null) {
            throw new IllegalArgumentException("Arraylist is empty. Please add units to list");
        }
    }

    /**
     * Method for removing units from army
     *
     * @param unit the unit to be removed
     */
    public void remove(Unit unit){
        units.remove(unit);
    }

    /**
     * boolean to check if the main arraylist is empty or not
     *
     * @return a boolean value of true or false if the list is not empty
     */
    public boolean hasUnits() {
        return !units.isEmpty();
    }

    /**
     * Method for getting a random unit from the army
     * Sets boundary from the size of the arraylist
     *
     * @return the random unit picked using java util Random
     */
    public Unit getRandom() {
        Random number = new Random();

        return units.get(number.nextInt(this.getUnitSizeByInt()));
    }

    /**
     * method for getting infantry units in arraylist units
     *
     * Creates new arraylist to contain infantry units
     * sets infantry unit arraylist equal to a stream method filtering through the arraylist, and using lambda expression
     * to check for instances of class InfantryUnit in the arraylist units, gotten through deep copy
     *
     * @return returns arraylist of infantry units
     */
    public ArrayList<Unit> getInfantryUnit() {
        ArrayList<Unit> infantryUnit;

        infantryUnit = (ArrayList<Unit>) getAllUnits().stream()
                .filter(p -> p instanceof InfantryUnit)
                .collect(Collectors.toList());
        return infantryUnit;
    }

    /**
     * method for getting ranged units in arraylist units
     *
     * Creates new arraylist to contain ranged units
     * sets ranged unit arraylist equal to a stream method filtering through the arraylist, and using lambda expression
     * to check for instances of class RangedUnit in the arraylist units, gotten through deep copy
     *
     * @return returns arraylist of ranged units
     */
    public ArrayList<Unit> getRangedUnit() {
        ArrayList<Unit> rangedUnit;

        rangedUnit = (ArrayList<Unit>) getAllUnits().stream()
                .filter(p -> p instanceof RangedUnit)
                .collect(Collectors.toList());
        return rangedUnit;
    }

    /**
     * method for getting cavalry units in arraylist units
     *
     * Creates new arraylist to contain cavalry units
     * sets cavalry unit arraylist equal to a stream method filtering through the arraylist, and using lambda expression
     * to check for instances of class CavalryUnit in the arraylist units, gotten through deep copy
     *
     * @return returns arraylist of cavalry units
     */
    public ArrayList<Unit> getCavalryUnit() {
        ArrayList<Unit> cavalryUnit;

        cavalryUnit = (ArrayList<Unit>) getAllUnits().stream()
                .filter(p -> p instanceof CavalryUnit)
                .filter(p -> !(p instanceof CommanderUnit))
                .collect(Collectors.toList());
        return cavalryUnit;
    }

    /**
     * method for getting commander units in arraylist units
     *
     * Creates new arraylist to contain commander units
     * sets commander unit arraylist equal to a stream method filtering through the arraylist, and using lambda expression
     * to check for instances of class CommanderUnit in the arraylist units, gotten through deep copy
     *
     * @return returns arraylist of commander units
     */
    public ArrayList<Unit> getCommanderUnit() {
        ArrayList<Unit> commanderUnit;

        commanderUnit = (ArrayList<Unit>) getAllUnits().stream()
                .filter(p -> p instanceof CommanderUnit)
                .collect(Collectors.toList());
        return commanderUnit;
    }

    /**
     * method for getting wizard units in arraylist units
     *
     * Creates new arraylist to contain wizard units
     * sets wizard unit arraylist equal to a stream method filtering through the arraylist, and using lambda expression
     * to check for instances of class WizardUnit in the arraylist units, gotten through deep copy
     *
     * @return returns arraylist of wizard units
     */
    public ArrayList<Unit> getWizardUnit() {
        ArrayList<Unit> wizardUnit;

        wizardUnit = (ArrayList<Unit>) getAllUnits().stream()
                .filter(p -> p instanceof WizardUnit)
                .collect(Collectors.toList());
        return wizardUnit;
    }

    /**
     * method for getting swordmaster units in arraylist units
     *
     * Creates new arraylist to contain swordmaster units
     * sets swordmaster unit arraylist equal to a stream method filtering through the arraylist, and using lambda expression
     * to check for instances of class SwordmasterUnit in the arraylist units, gotten through deep copy
     *
     * @return returns arraylist of swordmaster units
     */
    public ArrayList<Unit> getSwordmasterUnit() {
        ArrayList<Unit> swordmasterUnit;

        swordmasterUnit = (ArrayList<Unit>) getAllUnits().stream()
                .filter(p -> p instanceof SwordmasterUnit)
                .collect(Collectors.toList());
        return swordmasterUnit;
    }


    /**
     * toString method for the class Army
     * @return returns a string of information about the army, and it's remaining units
     */
    @Override
    public String toString() {
        return "Army: " + this.getName() +
                "\nRemaining units: " + this.getUnitSizeByInt();
    }

    /**
     * Equals method for the class Army. Created for the possibility of checking if armies equals each other
     * @param o object to compare to
     * @return returns a boolean value of true or false if the objects are equal or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Army army = (Army) o;
        return Objects.equals(name, army.name) && Objects.equals(units, army.units);
    }

    /**
     * Hashcode method
     * @return returns hashcode of object
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, units);
    }
}
