package Army_tests;
import Army.*;
import Units.*;
import java.util.*;

import Units.CavalryUnit;
import Units.CommanderUnit;
import Units.InfantryUnit;
import Units.RangedUnit;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for methods within the Army class
 * Testing all methods which makes sense to test
 *
 * @Author Daniel Evensen
 */
public class Army_test {

    /**
     * method to fill two armies with pre-set units to be used for tests
     * @param army1
     * @param army2
     */
    public void fillArmyWithUnits(Army army1, Army army2) {
        for (int i = 0; i < 500; i++) {
            army1.add(new InfantryUnit("test1", 100));
            army2.add(new InfantryUnit("test2", 100));
        } for (int i = 0; i < 200; i++) {
            army1.add(new RangedUnit("test1", 100));
            army2.add(new RangedUnit("test2", 100));
        } for (int i = 0; i < 100; i++) {
            army1.add(new CavalryUnit("test1", 100));
            army2.add(new CavalryUnit("test2", 100));
        }
        army1.add(new CommanderUnit("Jaina Proudmoore", 180));
        army2.add(new CommanderUnit("Sylvanas Windrunner", 180));
    }

    /**
     * Test for creating an army with units, and adding units to the army
     *
     * uses assertFalse to make sure that armies does not have units before adding units
     *
     * uses assertTrue after adding units to armies, and check if armies has units in them.
     */
    @Test
    public void createArmy() {
        Army army1 = new Army("Human");
        Army army2 = new Army("Orc");

        assertFalse(army1.hasUnits());
        assertFalse(army2.hasUnits());

        fillArmyWithUnits(army1, army2);

        assertTrue(army1.hasUnits());
        assertTrue(army2.hasUnits());
    }

    /**
     * Test for creating an army with units from arraylist
     *
     * uses assertFalse to check that armies does not have units before adding them
     * creates arraylists and adds units for both armies into both arraylists
     * uses addAll to add the units in the arraylists to the armies
     * uses assertTrue to check that armies has units after adding the units
     */
    @Test
    public void createArmyWithArrayList() {
        Army army1 = new Army("Human");
        Army army2 = new Army("Orc");

        assertFalse(army1.hasUnits());
        assertFalse(army2.hasUnits());

        ArrayList<Unit> fillArmy1WithUnits = new ArrayList<>();
        ArrayList<Unit> fillArmy2WithUnits = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            fillArmy1WithUnits.add(new InfantryUnit("test1", 100));
            fillArmy2WithUnits.add(new InfantryUnit("test2", 100));
        } for (int i = 0; i < 200; i++) {
            fillArmy1WithUnits.add(new RangedUnit("test1", 100));
            fillArmy2WithUnits.add(new RangedUnit("test2", 100));
        } for (int i = 0; i < 100; i++) {
            fillArmy1WithUnits.add(new CavalryUnit("test1", 100));
            fillArmy2WithUnits.add(new CavalryUnit("test2", 100));
        }
        fillArmy1WithUnits.add(new CommanderUnit("Jaina Proudmoore", 180));
        fillArmy2WithUnits.add(new CommanderUnit("Sylvanas Windrunner", 180));;

        army1.addAll(fillArmy1WithUnits);
        army2.addAll(fillArmy2WithUnits);

        assertTrue(army1.hasUnits());
        assertTrue(army2.hasUnits());
    }

    /**
     * Test for filling/creating an army with an empty array list
     *
     * uses assertThrows to check if the method throws an exception when trying to add an empty arraylist
     * to the armies.
     */
    @Test
    public void createArmyWithAddAllUsingEmptyList() {
        Army army1 = new Army("Human");
        Army army2 = new Army("Orc");

        ArrayList<Unit> fillArmyWithEmptyList = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> army1.addAll(fillArmyWithEmptyList));
        assertThrows(IllegalArgumentException.class, () -> army2.addAll(fillArmyWithEmptyList));
    }

    /**
     * Test for checking if you can create and army with a name, and an empty arraylist
     *
     * uses assertThrows and a lambda expression to check that the method throws an exception when trying to
     * create the army with an empty arraylist
     */
    @Test
    public void createArmyWithNameAndEmptyArrayList() {
        ArrayList<Unit> emptyList = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> {
            Army army1 = new Army("test", emptyList);
        });
    }

    /**
     * Test for checking if you can create and army with a name, and an arraylist with units in it
     *
     * uses assertDoesNotThrow and a lambda expression to check if the method does not throw an exception when trying
     * to create the army with the name of army and arraylist
     */
    @Test
    public void createArmyWithNameAndArrayList() {
        ArrayList<Unit> armyList = new ArrayList<>();

        armyList.add(new InfantryUnit("testUnit", 100));

        assertDoesNotThrow(() -> {
            Army army1 = new Army("Test", armyList);
        });
    }

    /**
     * Test for checking if removing a unit from an army works as it should
     *
     * Uses multiple Junit test operations to check for multiple angles
     *
     * uses assertTrue to make sure the army as units in it
     * uses assertEquals to check if the size of the army is the same as what is expected
     *
     * uses assertEquals again after removing a unit from the army to check if the size of the army now is the same
     * as what is expected
     *
     * adds a single unit to a standalone army to check with booleans
     * uses assertTrue to check if the army has units after adding the unit to the army
     * uses assertFalse to check if the army has no units after removing the unit from the army
     */
    @Test
    public void removeUnitFromArmy() {
        Army army1 = new Army("Human");
        Army army2 = new Army("Orc");
        Army army3 = new Army("Test");
        fillArmyWithUnits(army1, army2);

        assertTrue(army1.hasUnits());
        assertEquals(801, army1.getUnitSizeByInt());

        army1.remove(army1.getRandom());
        assertEquals(800,army1.getUnitSizeByInt());

        army3.add(new CommanderUnit("Thrall", 180));
        assertTrue(army3.hasUnits());
        army3.remove(army3.getRandom());
        assertFalse(army3.hasUnits());
    }

    /**
     * Test method for getting the correct amount of infantry units out from arraylist units
     * expects 500 infantry units, and checks that up with the size of arraylist infantry units
     *
     * also check that arraylist is not empty, or doesn't pass through the number of another unit
     */
    @Test
    public void testGetInfantryUnit() {
        Army army1 = new Army("Human");
        Army army2 = new Army("Orc");
        fillArmyWithUnits(army1, army2);

        assertEquals(500, army1.getInfantryUnit().size());
        assertNotEquals(0, army1.getInfantryUnit().size());
        assertNotEquals(200, army1.getInfantryUnit().size());
    }

    /**
     * Test method for getting the correct amount of ranged units out from arraylist units
     * expects 200 ranged units, and checks that up with the size of arraylist ranged units
     *
     * also check that arraylist is not empty, or doesn't pass through the number of another unit
     */
    @Test
    public void testGetRangedUnit() {
        Army army1 = new Army("Human");
        Army army2 = new Army("Orc");
        fillArmyWithUnits(army1, army2);

        assertEquals(200, army1.getRangedUnit().size());
        assertNotEquals(0, army1.getRangedUnit().size());
        assertNotEquals(500, army1.getRangedUnit().size());
    }

    /**
     * Test method for getting the correct amount of cavalry units out from arraylist units
     * expects 100 ranged units, and checks that up with the size of arraylist cavalry units
     *
     * also check that arraylist is not empty, or doesn't pass through the number of another unit
     */
    @Test
    public void testGetCavalryUnit() {
        Army army1 = new Army("Human");
        Army army2 = new Army("Orc");
        fillArmyWithUnits(army1, army2);

        assertEquals(100, army1.getCavalryUnit().size());
        assertNotEquals(0, army1.getCavalryUnit().size());
        assertNotEquals(1, army1.getCavalryUnit().size());
    }

    /**
     * Test method for getting the correct amount of commander units out from arraylist units
     * expects 1 commander unit, and checks that up with the size of arraylist commander units
     *
     * also check that arraylist is not empty, or doesn't pass through the number of another unit
     */
    @Test
    public void testGetCommanderUnit() {
        Army army1 = new Army("Human");
        Army army2 = new Army("Orc");
        fillArmyWithUnits(army1, army2);

        assertEquals(1, army1.getCommanderUnit().size());
        assertNotEquals(0, army1.getCommanderUnit().size());
        assertNotEquals(100, army1.getCommanderUnit().size());
    }
}
