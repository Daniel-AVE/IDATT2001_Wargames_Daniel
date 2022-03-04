package Army;

import Units.*;
import java.util.*;

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
