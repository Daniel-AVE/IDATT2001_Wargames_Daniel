package Units;

/**
 * The class CavalryUnit which inherits from super class Unit
 * @Author Daniel Evensen
 */
public class CavalryUnit extends Unit {
    private int counter = 0;

    /**
     * Instantiates a new Cavalry unit.
     * Possible to edit attack and armor for this constructor
     *
     * @param name   the name
     * @param health the health
     * @param attack the attack
     * @param armor  the armor
     */
    public CavalryUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     * Instantiates a new Cavalry unit.
     * This constructor has pre-specified values for attack and armor
     *
     * @param name   the name
     * @param health the health
     */
    public CavalryUnit(String name, int health) {
        super(name, health, 20, 12);
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
     * If counter is 0, the value of attack bonus is higher, due to the cavalry charging the opponent on a horse
     * If counter is 1, the value of attack bonus decreases, due to cavalry unit no longer being able to charge from a distance
     * @return returns a value for this method for this specific unit
     */
    @Override
    public int getAttackBonus() {
        int attackBonus = 0;

        if (this.getCounter() == 0) {
            attackBonus = 6;
        } else {
            attackBonus = 2;
        }
        this.increaseCounter();

        return attackBonus;
    }

    /**
     * Override of the abstract method getResistBonus from super class
     * @return returns a value for this method for this specific unit
     */
    @Override
    public int getResistBonus() {
        return 2;
    }

    /**
     * Override of the abstract method copy from super class
     * @return returns a copy object of this specific unit
     */
    @Override
    public CavalryUnit copy() {
        return new CavalryUnit(this.getName(), this.getHealth(), this.getAttack(), this.getArmor());
    }

    /**
     * toString method, adds another value to the toString from the super class for this specific unit
     * @return returns new string
     */
    @Override
    public String toString() {
        return "Cavalry unit: " + super.toString();
    }
}
