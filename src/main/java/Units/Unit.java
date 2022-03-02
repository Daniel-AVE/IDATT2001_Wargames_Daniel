package Units;

import java.util.Objects;

/**
 * The type Unit.
 */
public abstract class Unit {
    protected String name;
    protected int health, attack, armor;

    /**
     * Instantiates a new Unit.
     *
     * @param name   the name
     * @param health the health
     * @param attack the attack
     * @param armor  the armor
     */
    public Unit(String name, int health, int attack, int armor) {
        this.name = name;
        this.attack = attack;
        this.armor = armor;
        this.health = health;

    }

    /**
     * Attack.
     *
     * @param opponent the opponent
     */
    public void attack(Unit opponent) {
        if (this.getHealth() < 1) {
            throw new IllegalArgumentException("You cannot attack with a dead unit!");
        } else if (opponent.getHealth() < 1) {
            throw new IllegalArgumentException("You cannot attack a dead unit!");
        } else {
            try {
                opponent.health = opponent.getHealth() - (this.getAttack() + this.getAttackBonus()) + (opponent.getArmor() + opponent.getResistBonus());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets health.
     *
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Gets attack.
     *
     * @return the attack
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Gets armor.
     *
     * @return the armor
     */
    public int getArmor() {
        return armor;
    }

    /**
     * Sets health.
     *
     * @param health the health
     */
    public void setHealth(int health) {
        this.health = health;
        if (this.health <= 0) {
            throw new IllegalArgumentException("Unit health cannot be lower than 1");
        }
    }

    @Override
    public String toString() {
        return "Unit:" +
                "\nName = " + this.getName() + "\"" +
                "\nHealth: " + this.getHealth() + "\"" +
                "\nAttack: " + this.getAttack() + "\"" +
                "\nArmor: " + this.getArmor();
    }

    /**
     * Gets attack bonus.
     *
     * @return the attack bonus
     */
    public abstract int getAttackBonus();

    /**
     * Gets resist bonus.
     *
     * @return the resist bonus
     */
    public abstract int getResistBonus();

    /**
     * Copy unit.
     *
     * @return the unit
     */
    public abstract Unit copy();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return health == unit.health && attack == unit.attack && armor == unit.armor && Objects.equals(name, unit.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, health, attack, armor);
    }
}
