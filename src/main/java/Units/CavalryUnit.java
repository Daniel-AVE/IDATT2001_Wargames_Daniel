package Units;

public class CavalryUnit extends Unit {
    private int counter = 0;

    public CavalryUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    public CavalryUnit(String name, int health) {
        super(name, health, 20, 12);
    }

    public int getCounter() {
        return counter;
    }

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
