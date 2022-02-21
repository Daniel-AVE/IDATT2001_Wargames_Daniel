package Units;

import java.util.Objects;

public class Unit {
    protected String name;
    protected int health, attack, armor;

    public Unit(String name, int health, int attack, int armor) {
        this.name = name;
        this.attack = attack;
        this.armor = armor;
        this.health = health;
    }
}
