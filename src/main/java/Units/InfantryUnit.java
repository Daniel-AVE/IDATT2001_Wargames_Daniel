package Units;

/**
 * The class InfantryUnit which inherits from super class Unit
 * @Author Daniel Evensen
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
    public int getAttackBonus() {
        return 2;
    }

    /**
     * Override of the abstract method getResistBonus from super class
     * @return returns a value for this method for this specific unit
     */
    @Override
    public int getResistBonus() {
        return 1;
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
