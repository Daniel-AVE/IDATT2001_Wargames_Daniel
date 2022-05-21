package edu.ntnu.idatt2001.Units;

import edu.ntnu.idatt2001.Terrain.*;

/**
 * The class WizardUnit which inherits from super class Unit
 * @Author Daniel Evensen
 */

public class WizardUnit extends Unit {
    private int attackCounter = 0;
    private int resistCounter = 0;

    /**
     * Instantiates a new Wizard unit.
     * Possible to edit attack and armor for this constructor
     *
     * @param name   the name
     * @param health the health
     * @param attack the attack
     * @param armor  the armor
     */
    public WizardUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     * Instantiates a new Wizard unit.
     * This constructor has pre-specified values for attack and armor
     *
     * @param name   the name
     * @param health the health
     */
    public WizardUnit(String name, int health) {
        super(name, health, 18, 13);
    }

    /**
     * Gets attack counter, used in getAttackBonus to keep track of how far away unit is from melee
     *
     * @return the counter
     */
    public int getAttackCounter() {
        return attackCounter;
    }

    /**
     * Increases attack counter, used to differentiate attack bonus according to turn
     */
    public void increaseAttackCounter() {
        attackCounter++;
    }

    /**
     * Gets resist counter, used in getResistBonus to keep track of how far away unit is from melee
     *
     * @return the counter
     */

    public int getResistCounter() {
        return resistCounter;
    }

    /**
     * Increases resist counter, used to differentiate resist bonus according to turn
     */
    public void increaseResistCounter() {
        resistCounter++;
    }


    /**
     * Override of the abstract method getAttackBonus from super class
     * If counter is 0, the value of attack bonus is higher, due to the wizard being far away, giving time to cast spells
     * If counter is 1, the value of attack bonus decreases, due to enemy units closing on wizard, giving less time to cast spells
     * If counter is 2, the value of attack bonus reaches zero, due to enemy units having reached wizard, leaving no time for wizard to cast spells
     * @return returns a value for this method for this specific unit
     */
    @Override
    public int getAttackBonus(Terrain terrain) {
        int attackBonus = 0;

        if (this.getAttackCounter() == 0) {
            attackBonus = 12;
        } else if (this.getAttackCounter() == 1) {
            attackBonus = 6;
        } else {
            attackBonus = 0;
        }

        if (terrain == Terrain.plains) {
            if (this.getAttackCounter() == 0) {
                attackBonus = 12 + 3;
            } else if (this.getAttackCounter() == 1) {
                attackBonus = 6 + 3;
            } else {
                attackBonus = 0;
            }
        }
        this.increaseAttackCounter();

        return attackBonus;
    }


    /**
     * Override of the abstract method getResistBonus from super class
     * If counter is 0, the value of resist bonus is higher, due to the wizard unit being farther away from battle, with enough time to cast spells
     * If counter is 1, the value of resist bonus decreases a bit, due to opponent units closing in on wizard, leaving less time to cast spells
     * If counter is 2, the value of resist bonus decreases to zero, due to opponent units having reached wizard, leaving no time to cast spells
     * @return returns a value for this method for this specific unit
     */
    @Override
    public int getResistBonus (Terrain terrain){
        int resistBonus = 0;

        if (this.getResistCounter() == 0) {
            resistBonus = 8;
        } else if (this.getResistCounter() == 1) {
            resistBonus = 5;
        } else {
            resistBonus = 0;
        }

        this.increaseResistCounter();

        return resistBonus;
    }

    /**
     * Override of the abstract method copy from super class
     * @return returns a copy object of this specific unit
     */
    @Override
    public WizardUnit copy () {
        return new WizardUnit(this.getName(), this.getHealth(), this.getAttack(), this.getArmor());
    }

    /**
     * toString method, adds another value to the toString from the super class for this specific unit
     * @return returns new string
     */
    @Override
    public String toString () {
        return "Wizard unit: " + super.toString();
    }
}


