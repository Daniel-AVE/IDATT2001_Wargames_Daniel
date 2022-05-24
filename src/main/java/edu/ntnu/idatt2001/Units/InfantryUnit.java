package edu.ntnu.idatt2001.Units;

import edu.ntnu.idatt2001.Terrain.Terrain;

/**
 * The class InfantryUnit which inherits from super class Unit
 * @author Daniel Evensen
 */
public class InfantryUnit extends Unit {

    /**
     * Instantiates a new Infantry unit.
     * Possible to edit attack and armor for this constructor
     *
     * @param name   the name
     * @param health the health
     * @param attack the attack
     * @param armor  the armor
     */
    public InfantryUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     * Instantiates a new Infantry unit.
     * This constructor has pre-specified values for attack and armor
     *
     * @param name   the name
     * @param health the health
     */
    public InfantryUnit(String name, int health) {
        super(name, health, 15, 10);
    }

    /**
     * Override of the abstract method getAttackBonus from super class
     * @return returns a value for this method for this specific unit
     */
    @Override
    public int getAttackBonus(Terrain terrain) {
        if (terrain == Terrain.forest) {
            return 2 + 2;
        }
        return 2;
    }

    /**
     * Override of the abstract method getResistBonus from super class
     *
     * If this unit battles in a forest, it has added resist bonus
     *
     * @return returns a value for this method for this specific unit
     */
    @Override
    public int getResistBonus(Terrain terrain) {
        if (terrain == Terrain.forest) {
            return 1 + 1;
        } else {
            return 1;
        }
    }

    /**
     * Override of the abstract method copy from super class
     * @return returns a copy object of this specific unit
     */
    @Override
    public InfantryUnit copy() {
        return new InfantryUnit(this.getName(), this.getHealth(), this.getAttack(), this.getArmor());
    }

    /**
     * toString method, adds another value to the toString from the super class for this specific unit
     * @return returns new string
     */
    @Override
    public String toString() {
        return "Infantry unit: " + super.toString();
    }
}
