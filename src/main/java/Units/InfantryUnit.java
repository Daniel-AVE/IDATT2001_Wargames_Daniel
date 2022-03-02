package Units;

/**
 * The type Infantry unit.
 */
public class InfantryUnit extends Unit {

    /**
     * Instantiates a new Infantry unit.
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
     *
     * @param name   the name
     * @param health the health
     */
    public InfantryUnit(String name, int health) {
        super(name, health, 15, 10);
    }

    @Override
    public int getAttackBonus() {
        return 2;
    }

    @Override
    public int getResistBonus() {
        return 1;
    }

    @Override
    public InfantryUnit copy() {
        return new InfantryUnit(this.getName(), this.getHealth(), this.getAttack(), this.getArmor());
    }

    @Override
    public String toString() {
        return "Infantry unit: " + super.toString();
    }
}
