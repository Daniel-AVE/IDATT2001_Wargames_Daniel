package Units;
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

    public ArrayList<Unit> unitsCopy() {
        ArrayList<Unit> unitsCopy = new ArrayList<>();

        for (Unit units : units) {
            unitsCopy.add(units.copy());
        }
        return unitsCopy;
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
}
