package Battles;
import Army.*;
import Units.*;
import java.util.*;

public class Battle {
    private Army armyOne = new Army("Human");
    private Army armyTwo = new Army("Orc");

    public Battle(Army armyOne, Army armyTwo) {
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
    }

    public Army simulateRandom() {
        while (armyOne.hasUnits() && armyTwo.hasUnits()) {
            Random randomInit = new Random();
            int randomNumber = randomInit.nextInt(2);
            if (randomNumber == 0) {
                Unit randomUnit = armyTwo.getRandom();
                armyOne.getRandom().attack(randomUnit);

                if (randomUnit.getHealth() <= 0) {
                    armyTwo.remove(randomUnit);
                }
            } else {
                Unit randomUnit = armyOne.getRandom();
                armyTwo.getRandom().attack(randomUnit);

                if (randomUnit.getHealth() <= 0) {
                    armyOne.remove(randomUnit);
                }
            }
        }
        if (armyOne.hasUnits()) {
            return armyOne;
        }
        return armyTwo;
    }

    public Army simulateTurnbased() {
        int counter = 0;
        while (armyOne.hasUnits() && armyTwo.hasUnits()) {
            counter++;

            if (counter % 2 == 0) {
                Unit randomUnit = armyTwo.getRandom();
                armyOne.getRandom().attack(randomUnit);

                if (randomUnit.getHealth() >= 0) {
                    armyTwo.remove(randomUnit);
                }
            } else {
                Unit randomUnit = armyOne.getRandom();
                armyTwo.getRandom().attack(randomUnit);

                if (randomUnit.getHealth() >= 0) {
                    armyOne.remove(randomUnit);
                }
            }
        }
        if (armyOne.hasUnits()) {
            return armyOne;
        }
        return armyTwo;
    }

    @Override
    public String toString() {
        return "Battle{" +
                "armyOne=" + armyOne +
                ", armyTwo=" + armyTwo +
                '}';
    }
}
