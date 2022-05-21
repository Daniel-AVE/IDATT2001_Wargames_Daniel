package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.Terrain.Terrain;
import edu.ntnu.idatt2001.Units.*;
import org.junit.jupiter.api.Test;
        import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for all Units classes within the Units package
 * Testing all methods which makes sense to test
 * Does not test for actual bonuses gotten from terrain.
 *
 * @Author Daniel Evensen
 */
public class Units_Test {
        /**
         * Attack test for InfantryUnit
         * Also contains test for attack bonus, resist bonus and armor.
         * Attack with attack and attack bonus - the armor and resist bonus of the opponent
         *
         * Uses assertEquals to make sure the opponent's remaining health is what it should be, depending on
         * the attackers attack and attack bonus, and depending on opponent's armor and resist bonus
         */
        @Test
        public void infantryAttackTest() {
                InfantryUnit infantry1 = new InfantryUnit("Footman", 100);
                InfantryUnit infantry2 = new InfantryUnit("Footballer", 100);

                infantry1.attack(infantry2, Terrain.hill);
                assertEquals(94, infantry2.getHealth());

                infantry2.attack(infantry1, Terrain.plains);
                assertEquals(94, infantry1.getHealth());

                infantry1.attack(infantry2, Terrain.plains);
                assertEquals(88, infantry2.getHealth());
        }

        /**
         * Health change test for InfantryUnit
         * Tests that the method setHealth works as it should
         *
         * Uses assertEquals for what is expected to work
         * Uses assertThrows for what is expected to throw an exception
         */
        @Test
        public void infantryHealthTest() {
                InfantryUnit infantry1 = new InfantryUnit("Footman", 100);

                infantry1.setHealth(140);
                assertEquals(140, infantry1.getHealth());

                assertThrows(IllegalArgumentException.class,() -> infantry1.setHealth(0));

                assertThrows(IllegalArgumentException.class, () -> infantry1.setHealth(-2));
        }

        /**
         * Attack test for RangedUnit
         * Also contains test for attack bonus, resist bonus and armor.
         * Attack with attack and attack bonus - the armor and resist bonus of the opponent
         *
         * Also tests that the resist bonus works as it should by having higher resist bonus from first attack, and
         * decreased resist bonus for following attacks
         *
         * Uses assertEquals to make sure the opponent's remaining health is what it should be, depending on
         * the attackers attack and attack bonus, and depending on opponent's armor and resist bonus
         */
        @Test
        public void rangedAttackTest() {
                RangedUnit r1 = new RangedUnit("Archer", 100);
                RangedUnit r2 = new RangedUnit("Archie", 100);
                RangedUnit r3 = new RangedUnit("Arrow", 100);
                RangedUnit r4 = new RangedUnit("Bow", 100);

                r1.attack(r2, Terrain.plains);
                assertEquals(96, r2.getHealth());

                r2.attack(r1, Terrain.plains);
                assertEquals(96, r1.getHealth());

                r3.attack(r4, Terrain.plains);
                assertEquals(96, r4.getHealth());
                r3.attack(r4, Terrain.plains);
                assertEquals(90, r4.getHealth());
                r3.attack(r4, Terrain.plains);
                assertEquals(82, r4.getHealth());
        }

        /**
         * Health change test for RangedUnit
         * Tests that the method setHealth works as it should
         *
         * Uses assertEquals for what is expected to work
         * Uses assertThrows for what is expected to throw an exception
         */
        @Test
        public void rangedHealthTest() {
                RangedUnit r1 = new RangedUnit("Archer", 100);

                r1.setHealth(120);
                assertEquals(120, r1.getHealth());

                assertThrows(IllegalArgumentException.class, () -> r1.setHealth(0));
                assertThrows(IllegalArgumentException.class, () -> r1.setHealth(-2));
        }

        /**
         * Attack test for CavalryUnit
         * Also contains test for attack bonus, resist bonus and armor.
         * Attack with attack and attack bonus - the armor and resist bonus of the opponent
         *
         * Also tests that the attack bonus works as it should by having higher attack bonus for first attack, then
         * a decreased attack bonus for all following attacks
         *
         * Uses assertEquals to make sure the opponent's remaining health is what it should be, depending on
         * the attackers attack and attack bonus, and depending on opponent's armor and resist bonus
         */
        @Test
        public void cavalryAttackTest() {
                CavalryUnit c1 = new CavalryUnit("Knight", 100);
                CavalryUnit c2 = new CavalryUnit("Raider", 100);

                c1.attack(c2, Terrain.hill);
                assertEquals(88, c2.getHealth());

                c2.attack(c1, Terrain.hill);
                assertEquals(88, c1.getHealth());
                c2.attack(c1, Terrain.hill);
                assertEquals(80, c1.getHealth());
        }

        /**
         * Health change test for CavalryUnit
         * Tests that the method setHealth works as it should
         *
         * Uses assertEquals for what is expected to work
         * Uses assertThrows for what is expected to throw an exception
         */
        @Test
        public void cavalryHealthTest() {
                CavalryUnit c1 = new CavalryUnit("Knight", 70);

                c1.setHealth(110);
                assertEquals(110, c1.getHealth());

                assertThrows(IllegalArgumentException.class, () -> c1.setHealth(0));
                assertThrows(IllegalArgumentException.class, () -> c1.setHealth(-2));
        }

        /**
         * Attack test for CommanderUnit
         * Also contains test for attack bonus, resist bonus and armor.
         * Attack with attack and attack bonus - the armor and resist bonus of the opponent
         *
         * Also tests that the attack bonus works as it should by having higher attack bonus for first attack, then
         * a decreased attack bonus for all following attacks
         *
         * Uses assertEquals to make sure the opponent's remaining health is what it should be, depending on
         * the attackers attack and attack bonus, and depending on opponent's armor and resist bonus
         */
        @Test
        public void commanderAttackTest() {
                CommanderUnit com1 = new CommanderUnit("Gul'Dan", 100);
                CommanderUnit com2 = new CommanderUnit("Thrall", 100);

                com1.attack(com2, Terrain.hill);
                assertEquals(86, com2.getHealth());

                com2.attack(com1, Terrain.hill);
                assertEquals(86, com1.getHealth());
                com2.attack(com1, Terrain.hill);
                assertEquals(76, com1.getHealth());
        }

        /**
         * Health change test for CommanderUnit
         * Tests that the method setHealth works as it should
         *
         * Uses assertEquals for what is expected to work
         * Uses assertThrows for what is expected to throw an exception
         */
        @Test
        public void commanderHealthTest() {
                CommanderUnit com1 = new CommanderUnit("Gul'Dan", 100);

                com1.setHealth(80);
                assertEquals(80, com1.getHealth());

                assertThrows(IllegalArgumentException.class, () -> com1.setHealth(0));
                assertThrows(IllegalArgumentException.class, () -> com1.setHealth(-2));
        }
}
