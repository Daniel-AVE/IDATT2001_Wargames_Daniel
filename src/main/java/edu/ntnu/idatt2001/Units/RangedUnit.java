package edu.ntnu.idatt2001.Units;

/**
 * The class RangedUnit which inherits from super class Unit
 * @Author Daniel Evensen
 */
public class RangedUnit extends Unit {
    private int counter = 0; // used for creating different values for resist bonus depending on attacking turn

    /**
     * Instantiates a new Ranged unit.
     * Possible to edit attack and armor for this constructor
     *
     * @param name   the name
     * @param health the health
     * @param attack the attack
     * @param armor  the armor
     */
    public RangedUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     * Instantiates a new Ranged unit.
     * This constructor has pre-specified values for attack and armor
     *
     * @param name   the name
     * @param health the health
     */
    public RangedUnit(String name, int health) {
        super(name, health, 15, 8);
    }

    /**
     * Gets counter.
     *
     * @return the counter
     */
    public int getCounter() {
        return counter;
    }

    /**
     * Increase counter.
     */
    public void increaseCounter() {
        counter++;
    }

    /**
     * Override of the abstract method getAttackBonus from super class
     * @return returns a value for this method for this specific unit
     */
    @Override
    public int getAttackBonus () {
        return 3;
    }

    /**
     * Override of the abstract method getResistBonus from super class
     * If counter is 0, the value of resist bonus is higher, due to the ranged unit being farther away from battle
     * If counter is 1, the value of resist bonus decreases a bit, due to opponent units closing in on ranged unit
     * If counter is 2, the value of resist bonus decreases a bit more, due to opponent units having closed in fully on the ranged unit
     * @return returns a value for this method for this specific unit
     */
    @Override
    public int getResistBonus () {
        int resistBonus = 0;

        if (this.getCounter() == 0) {
            resistBonus = 6;
        } else if (this.getCounter() == 1) {
            resistBonus = 4;
        } else {
            resistBonus = 2;
        }
        this.increaseCounter();

        return resistBonus;
    }

    /**
     * Override of the abstract method copy from super class
     * @return returns a copy object of this specific unit
     */
    @Override
    public RangedUnit copy () {
        return new RangedUnit(this.getName(), this.getHealth(), this.getAttack(), this.getArmor());
    }

    /**
     * toString method, adds another value to the toString from the super class for this specific unit
     * @return returns new string
     */
    @Override
    public String toString () {
        return "Ranged unit: " + super.toString();
    }
}

