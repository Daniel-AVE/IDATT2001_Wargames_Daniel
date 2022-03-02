package Units;

/**
 * The type Cavalry unit.
 */
public class CavalryUnit extends Unit {
    private int counter = 0;

    /**
     * Instantiates a new Cavalry unit.
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

    @Override
    public int getResistBonus() {
        return 2;
    }

    @Override
    public CavalryUnit copy() {
        return new CavalryUnit(this.getName(), this.getHealth(), this.getAttack(), this.getArmor());
    }

    @Override
    public String toString() {
        return "Cavalry unit: " + super.toString();
    }
}
