package Units;

import java.util.Objects;

public abstract class Unit {
    protected String name;
    protected int health, attack, armor;

    public Unit(String name, int health, int attack, int armor) {
        this.name = name;
        this.attack = attack;
        this.armor = armor;
        this.health = health;

    }

    public void attack(Unit opponent) {
        if (this.getHealth() <= 0) {
            throw new IllegalArgumentException("You cannot attack with a dead unit!");
        } else if (opponent.getHealth() <= 0) {
            throw new IllegalArgumentException("You cannot attack a dead unit!");
        } else {
            try {
                opponent.setHealth(opponent.getHealth() - (this.getAttack() + this.getAttackBonus()) + (opponent.getArmor() + opponent.getResistBonus()));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getArmor() {
        return armor;
    }

    public void setHealth(int health) {
        this.health = health;
        if (health <= 0) {
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

    public abstract int getAttackBonus();

    public abstract int getResistBonus();

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
