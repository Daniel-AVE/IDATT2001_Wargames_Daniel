package Units;

/**
 * The type Commander unit.
 */
public class CommanderUnit extends CavalryUnit {

    /**
     * Instantiates a new Commander unit.
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
     *
     * @param name   the name
     * @param health the health
     */
    public CommanderUnit(String name, int health) {
        super(name, health, 25, 15);
    }

    @Override
    public CommanderUnit copy() {
        return new CommanderUnit(this.getName(), this.getHealth(), this.getAttack(), this.getArmor());
    }

    @Override
    public String toString() {
        return "Commander unit: " + super.toString();
    }
}
