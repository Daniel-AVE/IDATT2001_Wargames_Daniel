package edu.ntnu.idatt2001.Units;

import edu.ntnu.idatt2001.Terrain.Terrain;

public class SwordmasterUnit extends Unit{
    private int attackCounter = 0;
    private int resistCounter = 0;

    /**
     * Instantiates a new Swordmaster unit.
     * Possible to edit attack and armor for this constructor
     *
     * @param name   the name
     * @param health the health
     * @param attack the attack
     * @param armor  the armor
     */
    public SwordmasterUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     * Instantiates a new Swordmaster unit.
     * This constructor has pre-specified values for attack and armor
     *
     * @param name   the name
     * @param health the health
     */
    public SwordmasterUnit(String name, int health) {
        super(name, health, 18, 16);
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
     * Override of the abstract method getResistBonus from super class
     * If counter is 0, the value of attack bonus is zero, due to the swordmaster being too far away to attack nor defend himself properly
     * If counter is 1, the value of attack bonus increases a bit, due swordmaster getting close enough in range to start throwing attacks
     * If counter is 2, the value of attack bonus increases more, due to swordmaster having reached opponent units, being able to both attack and defend himself properly
     *
     * 
     *
     * @return returns a value for this method for this specific unit
     */
    @Override
    public int getAttackBonus(Terrain terrain) {
        int attackBonus = 0;

        if (this.getAttackCounter() == 0) {
            attackBonus = 0;
        } else if (this.getAttackCounter() == 1) {
            attackBonus = 5;
        } else {
            attackBonus = 11;
        }
        this.increaseAttackCounter();

        return attackBonus;
    }


    /**
     * Override of the abstract method getResistBonus from super class
     * If counter is 0, the value of resist bonus is zero, due to the swordmaster being too far away to attack nor defend himself properly
     * If counter is 1, the value of resist bonus increases a bit, due swordmaster closing in on enemies, and being able to more efficiently counter attacks
     * If counter is 2, the value of resist bonus increases more, due to swordmaster having reached opponent units, being able to both attack and defend himself properly
     *
     * Gets +2 in resistBonus in forests, due to the limited field of area.
     * @return returns a value for this method for this specific unit
     */
    @Override
    public int getResistBonus (Terrain terrain){
        int resistBonus = 0;

        if (this.getResistCounter() == 0) {
            resistBonus = 0;
        } else if (this.getResistCounter() == 1) {
            resistBonus = 3;
        } else {
            resistBonus = 5;
        }

        if (terrain == Terrain.forest) {
            if (this.getResistCounter() == 0) {
                resistBonus = 0 + 2;
            } else if (this.getResistCounter() == 1) {
                resistBonus = 3 + 2;
            } else {
                resistBonus = 5 + 2;
            }
        }

        this.increaseResistCounter();

        return resistBonus;
    }

    /**
     * Override of the abstract method copy from super class
     * @return returns a copy object of this specific unit
     */
    @Override
    public SwordmasterUnit copy () {
        return new SwordmasterUnit(this.getName(), this.getHealth(), this.getAttack(), this.getArmor());
    }

    /**
     * toString method, adds another value to the toString from the super class for this specific unit
     * @return returns new string
     */
    @Override
    public String toString () {
        return "Swordmaster unit: " + super.toString();
    }
}
