package Unit_tests;
import Units.*;
import org.junit.Test;
        import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.junit.jupiter.api.Assertions.*;

public class Units_test {

        @Test
        public void infantryAttackTest() {
                InfantryUnit infantry1 = new InfantryUnit("Footman", 100);
                InfantryUnit infantry2 = new InfantryUnit("Footballer", 100);

                infantry1.attack(infantry2);
                assertEquals(94, infantry2.getHealth());

                infantry2.attack(infantry1);
                assertEquals(94, infantry1.getHealth());

                infantry1.attack(infantry2);
                assertEquals(88, infantry2.getHealth());
        }

        @Test
        public void infantryHealthTest() {
                InfantryUnit infantry1 = new InfantryUnit("Footman", 100);

                infantry1.setHealth(140);
                assertEquals(140, infantry1.getHealth());

                assertThrows(IllegalArgumentException.class,() -> infantry1.setHealth(0));

                assertThrows(IllegalArgumentException.class, () -> infantry1.setHealth(-2));
        }

        @Test
        public void rangedAttackTest() {
                RangedUnit r1 = new RangedUnit("Archer", 100);
                RangedUnit r2 = new RangedUnit("Archie", 100);
                RangedUnit r3 = new RangedUnit("Arrow", 100);
                RangedUnit r4 = new RangedUnit("Bow", 100);

                r1.attack(r2);
                assertEquals(96, r2.getHealth());

                r2.attack(r1);
                assertEquals(96, r1.getHealth());

                r3.attack(r4);
                assertEquals(96, r4.getHealth());
                r3.attack(r4);
                assertEquals(90, r4.getHealth());
                r3.attack(r4);
                assertEquals(82, r4.getHealth());
        }

        @Test
        public void rangedHealthTest() {
                RangedUnit r1 = new RangedUnit("Archer", 100);

                r1.setHealth(120);
                assertEquals(120, r1.getHealth());

                assertThrows(IllegalArgumentException.class, () -> r1.setHealth(0));
                assertThrows(IllegalArgumentException.class, () -> r1.setHealth(-2));
        }

        @Test
        public void cavalryAttackTest() {
                CavalryUnit c1 = new CavalryUnit("Knight", 100);
                CavalryUnit c2 = new CavalryUnit("Raider", 100);

                c1.attack(c2);
                assertEquals(88, c2.getHealth());

                c2.attack(c1);
                assertEquals(88, c1.getHealth());
                c2.attack(c1);
                assertEquals(80, c1.getHealth());
        }

        @Test
        public void cavalryHealthTest() {
                CavalryUnit c1 = new CavalryUnit("Knight", 70);

                c1.setHealth(110);
                assertEquals(110, c1.getHealth());

                assertThrows(IllegalArgumentException.class, () -> c1.setHealth(0));
                assertThrows(IllegalArgumentException.class, () -> c1.setHealth(-2));
        }

        @Test
        public void commanderAttackTest() {
                CommanderUnit com1 = new CommanderUnit("Gul'Dan", 100);
                CommanderUnit com2 = new CommanderUnit("Thrall", 100);

                com1.attack(com2);
                assertEquals(86, com2.getHealth());

                com2.attack(com1);
                assertEquals(86, com1.getHealth());
                com2.attack(com1);
                assertEquals(76, com1.getHealth());
        }

        @Test
        public void commanderHealthTest() {
                CommanderUnit com1 = new CommanderUnit("Gul'Dan", 100);

                com1.setHealth(80);
                assertEquals(80, com1.getHealth());

                assertThrows(IllegalArgumentException.class, () -> com1.setHealth(0));
                assertThrows(IllegalArgumentException.class, () -> com1.setHealth(-2));
        }
}
