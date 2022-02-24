import Units.*;
import java.util.*;

public class Army {
    private String name;
    private ArrayList<Unit> units;

    public Army(String name) {
        this.units = new ArrayList<>();
        this.name = name;
    }

    public Army(String name, ArrayList<Unit> units) {
        this.units = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Unit> getAllUnits() {
        ArrayList<Unit> unitsCopy = new ArrayList<>();

        for (Unit units : units) {
            unitsCopy.add(units.copy());
        }
        return unitsCopy;
    }

    public int getUnitSizeByInt() {
        return units.size();
    }

    public void add(Unit unit) {
        units.add(unit.copy());
    }

    public void addAll(ArrayList<Unit> inputUnits) {
        for (Unit unit : inputUnits) {
            units.add(unit.copy());
        }
    }

    public void remove(Unit unit){
        units.remove(unit);
    }

    public boolean hasUnits() {
        return !units.isEmpty();
    }

    public Unit getRandom() {
        Random number = new Random();

        return units.get(number.nextInt(this.getUnitSizeByInt()));
    }

    @Override
    public String toString() {
        return "Army: " + this.getName() +
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
