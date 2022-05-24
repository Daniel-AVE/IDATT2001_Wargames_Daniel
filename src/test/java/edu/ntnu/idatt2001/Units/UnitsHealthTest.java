package edu.ntnu.idatt2001.Units;

import edu.ntnu.idatt2001.Terrain.Terrain;
import edu.ntnu.idatt2001.Units.*;
import org.junit.jupiter.api.Test;
        import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for all Units classes within the Units package
 * Testing all methods pertaining to health
 *
 * @author Daniel Evensen
 */
public class UnitsHealthTest {

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

        /**
         * Health change test for WizardUnit
         * Tests that the method setHealth works as it should
         *
         * Uses assertEquals for what is expected to work
         * Uses assertThrows for what is expected to throw an exception
         */
        @Test
        public void wizardHealthTest() {
                WizardUnit w1 = new WizardUnit("Merlin", 100);

                assertEquals(100, w1.getHealth());

                w1.setHealth(80);
                assertEquals(80, w1.getHealth());

                assertThrows(IllegalArgumentException.class, () -> w1.setHealth(0));
                assertThrows(IllegalArgumentException.class, () -> w1.setHealth(-2));
        }

        /**
         * Health change test for SwordmasterUnit
         * Tests that the method setHealth works as it should
         *
         * Uses assertEquals for what is expected to work
         * Uses assertThrows for what is expected to throw an exception
         */
        @Test
        public void swordmasterHealthTest() {
                SwordmasterUnit s1 = new SwordmasterUnit("Sword Hero", 100);

                assertEquals(100, s1.getHealth());

                s1.setHealth(80);
                assertEquals(80, s1.getHealth());

                assertThrows(IllegalArgumentException.class, () -> s1.setHealth(0));
                assertThrows(IllegalArgumentException.class, () -> s1.setHealth(-2));
        }
}
