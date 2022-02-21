package Units;

public class RangedUnit extends Unit {
    private int counter = 0;

    public RangedUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    public RangedUnit(String name, int health) {
        super(name, health, 15, 8);
    }

    public int getCounter() {
        return counter;
    }

    public void increaseCounter() {
        counter++;
    }

    @Override
    public int getAttackBonus () {
        return 3;
    }

    @Override
    public int getResistBonus () {
        int resistBonus = 0;

        if (this.getCounter() == 0) {
            resistBonus = 6;
        } else if (this.getCounter() == 1) {
            resistBonus = 4;
        } else {
            resistBonus = 2;
        }
        this.increaseCounter();

        return resistBonus;
    }

    @Override
    public RangedUnit copy () {
        return new RangedUnit(this.getName(), this.getHealth(), this.getAttack(), this.getArmor());
    }

    @Override
    public String toString () {
        return "Ranged unit: " + super.toString();
    }
}

