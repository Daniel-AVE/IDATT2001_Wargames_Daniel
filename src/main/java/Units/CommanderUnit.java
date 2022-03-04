package Units;

/**
 * The class CommanderUnit which inherits from CavalryUnit
 * Meaning this class inherits from CavalryUnit which again inherits from super class Unit
 * @Author Daniel Evensen
 */
public class CommanderUnit extends CavalryUnit {

    /**
     * Instantiates a new Commander unit.
     * Possible to edit attack and armor for this constructor
     *
     * @param name   the name
     * @param health the health
     * @param attack the attack
     * @param armor  the armor
     */
    public CommanderUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     * Instantiates a new Commander unit.
     * This constructor has pre-specified values for attack and armo
     *
     * @param name   the name
     * @param health the health
     */
    public CommanderUnit(String name, int health) {
        super(name, health, 25, 15);
    }

    /**
     * Override of the abstract method copy from super class
     * @return returns a copy object of this specific unit
     */
    @Override
    public CommanderUnit copy() {
        return new CommanderUnit(this.getName(), this.getHealth(), this.getAttack(), this.getArmor());
    }

    /**
     * toString method, adds another value to the toString from the super class for this specific unit
     * @return returns new string
     */
    @Override
    public String toString() {
        return "Commander unit: " + super.toString();
    }
}
