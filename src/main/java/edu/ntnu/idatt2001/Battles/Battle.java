package edu.ntnu.idatt2001.Battles;

import edu.ntnu.idatt2001.Army.*;
import edu.ntnu.idatt2001.Terrain.Terrain;
import edu.ntnu.idatt2001.Units.*;

import java.util.Random;

/**
 * The class Battle
 * Contains methods to simulate a battle between two armies
 * @author Daniel Evensen
 */
public class Battle {
    private Army armyOne = new Army("Human");
    private Army armyTwo = new Army("Orc");
    public Terrain terrain;

    /**
     * Instantiates a new Battle.
     * Takes terrain into account
     *
     * @param armyOne the army one
     * @param armyTwo the army two
     * @param terrain the terrain the battle takes place on
     */
    public Battle(Army armyOne, Army armyTwo, Terrain terrain) {
        if (armyOne == null || armyTwo == null) {
            throw new IllegalArgumentException("Armies cannot be empty or null");
        } if (!armyOne.hasUnits()) {
            throw new IllegalArgumentException(armyOne.getName() + " needs units in order to battle");
        } if (!armyTwo.hasUnits()) {
            throw new IllegalArgumentException(armyTwo.getName() + " needs units in order to battle");
        } if (terrain == null) {
            throw new IllegalArgumentException("Terrain is required in order to battle");
        }
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
        this.terrain = terrain;
    }

    /**
     * Method for simulating a battle between two armies, where the attack order of the armies are luck-bases
     * Uses java util Random to give two different values at random
     * If random number = 0, armyOne attacks armyTwo
     * Only other option is for random number to equal 1, which means armyTwo will attack ArmyOne
     * By having it randomised, and luck based, one army could attack up to multiple times before the other army attacks
     *
     * terrain is incorporated into the battle, making it easier to make the battle swerve in another army's favor
     *
     * Before returning values, it checks if armyOne has units left. If this is true, then it returns armyOne as the winner
     * If armyOne doesn't have units left, that means armyTwo won, and armyTwo is what's returned as the winner.
     *
     * @return the winning army
     */
    public Army simulateRandom() {
        while (armyOne.hasUnits() && armyTwo.hasUnits()) {
            Random randomInt = new Random();
            int randomNumber = randomInt.nextInt(2);
            if (randomNumber == 0) {
                try {
                    Unit randomUnit = armyTwo.getRandom();
                    armyOne.getRandom().attack(randomUnit, this.terrain);

                    if (randomUnit.getHealth() <= 0) {
                        armyTwo.remove(randomUnit);
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Unit randomUnit = armyOne.getRandom();
                    armyTwo.getRandom().attack(randomUnit, this.terrain);

                    if (randomUnit.getHealth() <= 0) {
                        armyOne.remove(randomUnit);
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        }
        if (armyOne.hasUnits()) {
            return armyOne;
        }
        return armyTwo;
    }

    /**
     * Method for simulating a turn-based battle. In case we don't want a luck-based battle, but a turn based battle.
     *
     * Uses an int counter, which increases for each iteration. Whenever the number from counter modulus 2 = 0, it's armyOne's turn to attack
     * Whenever counter modulus 2 does not equal 0, that means it's armyTwo's turn to attack. This will switch up for every other number
     * making this a turn based simulation
     *
     * Takes terrain into account. Winner will depend on numbers, type of units and terrain
     *
     * Same as in the randomized simulation, it checks which armies have units left, and returns the army depending on that.
     *
     * @return the army
     */
    public Army simulateTurnbased() {
        int counter = 0;
        while (armyOne.hasUnits() && armyTwo.hasUnits()) {
            counter++;

            if (counter % 2 == 0) {
                try {
                    Unit randomUnit = armyTwo.getRandom();
                    armyOne.getRandom().attack(randomUnit, this.terrain);

                    if (randomUnit.getHealth() >= 0) {
                        armyTwo.remove(randomUnit);
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Unit randomUnit = armyOne.getRandom();
                    armyTwo.getRandom().attack(randomUnit, this.terrain);

                    if (randomUnit.getHealth() >= 0) {
                        armyOne.remove(randomUnit);
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        }
        if (armyOne.hasUnits()) {
            return armyOne;
        }
        return armyTwo;
    }

    /**
     * toString method for the class Battle
     * @return returns a string with information about the battle taking place. I.e. which armies are fighting each other
     */
    @Override
    public String toString() {
        return "Battle{" +
                "armyOne=" + armyOne +
                ", armyTwo=" + armyTwo +
                '}';
    }
}
