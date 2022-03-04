package Units;

import java.util.Objects;

/**
 * The super class for units
 * @Author Daniel Evensen
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
     * Method to be used when attacking opponents
     * Throws new illegal argument exceptions whenever either this, or opponent unit is dead
     * Uses try catch to make sure it works properly
     *
     * @param opponent opponent unit, in this case, the unit to be attacked
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

    /**
     * toString method which returns information about a unit
     * @return returns a string of information
     */
    @Override
    public String toString() {
        return "Unit:" +
                "\nName = " + this.getName() + "\"" +
                "\nHealth: " + this.getHealth() + "\"" +
                "\nAttack: " + this.getAttack() + "\"" +
                "\nArmor: " + this.getArmor();
    }

    /**
     * Abstract method for getting attack bonus
     * Method is overrided in all classes which extends this super class
     *
     * @return the attack bonus for the specific unit
     */
    public abstract int getAttackBonus();

    /**
     * Abstract method for getting resist bonus
     * Method is overrided in all classes which extends this super class
     *
     * @return the resist bonus for the specific unit
     */
    public abstract int getResistBonus();

    /**
     * Abstract class for copying a unit
     * Method is ovverided in all classes which extends this super class
     * Is used to create a deep copy of all type of units in the army class
     *
     * @return the copy of a specific unit
     */
    public abstract Unit copy();

    /**
     * equals method
     * @param o object you want to check if is equal
     * @return returns a boolean of wheter this or object o is equal or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return health == unit.health && attack == unit.attack && armor == unit.armor && Objects.equals(name, unit.name);
    }

    /**
     * Hashcode method
     * @return returns hashcode of object
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, health, attack, armor);
    }
}
