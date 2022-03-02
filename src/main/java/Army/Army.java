package Army;

import Units.*;
import java.util.*;

/**
 * The type Army.
 */
public class Army {
    private String name;
    private ArrayList<Unit> units;

    /**
     * Instantiates a new Army.
     *
     * @param name the name
     */
    public Army(String name) {
        this.units = new ArrayList<>();
        this.name = name;
    }

    /**
     * Instantiates a new Army.
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
     *
     * @return the all units
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
     * @return the unit size by int
     */
    public int getUnitSizeByInt() {
        return units.size();
    }

    /**
     * Add.
     *
     * @param unit the unit
     */
    public void add(Unit unit) {
        units.add(unit.copy());
    }

    /**
     * Add all.
     *
     * @param inputUnits the input units
     */
    public void addAll(ArrayList<Unit> inputUnits) {
        for (Unit unit : inputUnits) {
            units.add(unit.copy());
        } if (inputUnits.isEmpty() || inputUnits == null) {
            throw new IllegalArgumentException("Arraylist is empty. Please add units to list");
        }
    }

    /**
     * Remove.
     *
     * @param unit the unit
     */
    public void remove(Unit unit){
        units.remove(unit);
    }

    /**
     * Has units boolean.
     *
     * @return the boolean
     */
    public boolean hasUnits() {
        return !units.isEmpty();
    }

    /**
     * Gets random.
     *
     * @return the random
     */
    public Unit getRandom() {
        Random number = new Random();

        return units.get(number.nextInt(this.getUnitSizeByInt()));
    }

    @Override
    public String toString() {
        return "Army.Army: " + this.getName() +
                "\nRemaining units: " + this.getUnitSizeByInt();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Army army = (Army) o;
        return Objects.equals(name, army.name) && Objects.equals(units, army.units);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, units);
    }
}
