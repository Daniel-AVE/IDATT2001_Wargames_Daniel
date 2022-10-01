package edu.ntnu.idatt2001.Units;

import edu.ntnu.idatt2001.Terrain.Terrain;
import edu.ntnu.idatt2001.Units.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;


/**
 * The type Units attack test.
 *
 * @author Daniel Evensen
 */
public class UnitsAttackTest {
    /**
     * Attack test for InfantryUnit
     * Also contains test for attack bonus, terrain bonus, resist bonus and armor.
     * Also contains test for terrain bonuses
     * Attack with attack and attack bonus - the armor and resist bonus of the opponent
     *
     * Uses assertEquals to make sure the opponent's remaining health is what it should be, depending on
     * the attackers attack and attack bonus, and depending on opponent's armor and resist bonus
     */
    @Test
    public void infantryAttackTest() {
        InfantryUnit infantry1 = new InfantryUnit("Footman", 100);
        InfantryUnit infantry2 = new InfantryUnit("Footballer", 100);

        infantry1.attack(infantry2, Terrain.forest);
        assertEquals(93, infantry2.getHealth());

        infantry2.attack(infantry1, Terrain.forest);
        assertEquals(93, infantry1.getHealth());

        infantry1.attack(infantry2, Terrain.forest);
        assertEquals(86, infantry2.getHealth());
    }

    /**
     * Attack test for RangedUnit
     * Also contains test for attack bonus, resist bonus and armor.
     * Also contains test for terrain bonuses
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

        r1.attack(r2, Terrain.hill);
        assertEquals(94, r2.getHealth());

        r2.attack(r1, Terrain.forest);
        assertEquals(98, r1.getHealth());

        r3.attack(r4, Terrain.hill);
        assertEquals(94, r4.getHealth());
        r3.attack(r4, Terrain.hill);
        assertEquals(86, r4.getHealth());
        r3.attack(r4, Terrain.hill);
        assertEquals(76, r4.getHealth());

        r4.attack(r3, Terrain.forest);
        assertEquals(98, r3.getHealth());
        r4.attack(r3, Terrain.forest);
        assertEquals(94, r3.getHealth());
        r4.attack(r3, Terrain.forest);
        assertEquals(88, r3.getHealth());
    }

    /**
     * Attack test for CavalryUnit
     * Also contains test for attack bonus, resist bonus and armor.
     * Also contains test for terrain bonuses
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

        c1.attack(c2, Terrain.plains);
        assertEquals(84, c2.getHealth());

        c2.attack(c1, Terrain.plains);
        assertEquals(84, c1.getHealth());
        c2.attack(c1, Terrain.plains);
        assertEquals(72, c1.getHealth());

        c1.attack(c2, Terrain.forest);
        assertEquals(74, c2.getHealth());
    }

    /**
     * Attack test for CommanderUnit
     * Also contains test for attack bonus, resist bonus and armor.
     * Also contains test for terrain bonuses
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

        com1.attack(com2, Terrain.plains);
        assertEquals(82, com2.getHealth());

        com2.attack(com1, Terrain.plains);
        assertEquals(82, com1.getHealth());
        com2.attack(com1, Terrain.plains);
        assertEquals(68, com1.getHealth());

        com1.attack(com2, Terrain.forest);
        assertEquals(70, com2.getHealth());
    }

    /**
     * Wizard attack test.
     * Also contains test for attack bonus, resist bonus and armor.
     * Also contains test for terrain bonuses
     * Attack with attack and attack bonus - the armor and resist bonus of the opponent
     *
     * Uses assertEquals to make sure the opponent's remaining health is what it should be, depending on
     * the attackers attack and attack bonus, and depending on opponent's armor and resist bonus. While also
     * depending on what terrain they're on
     */
    @Test
    public void wizardAttackTest() {
        WizardUnit w1 = new WizardUnit("Merlin", 100);
        WizardUnit w2 = new WizardUnit("Lucas Traumen", 100);

        w1.attack(w2, Terrain.forest);
        assertEquals(86, w2.getHealth());
        w1.attack(w2, Terrain.forest);
        assertEquals(75, w2.getHealth());
        w1.attack(w2, Terrain.forest);
        assertEquals(65, w2.getHealth());

        w2.attack(w1, Terrain.plains);
        assertEquals(83, w1.getHealth());
        w2.attack(w1, Terrain.hill);
        assertEquals(69,w1.getHealth());
        w2.attack(w1, Terrain.plains);
        assertEquals(59,w1.getHealth());
    }

    /**
     * Swordmaster attack test.
     * Also contains test for attack bonus, resist bonus and armor.
     * Also contains test for terrain bonuses
     * Attack with attack and attack bonus - the armor and resist bonus of the opponent
     *
     * Uses assertEquals to make sure the opponent's remaining health is what it should be, depending on
     * the attackers attack and attack bonus, and depending on opponent's armor and resist bonus. While also
     * depending on what terrain they're on
     */
    @Test
    public void swordmasterAttackTest() {
        SwordmasterUnit s1 = new SwordmasterUnit("Kamiizumi Nobunatsa", 100);
        SwordmasterUnit s2 = new SwordmasterUnit("Oda Nobunaga", 100);

        s1.attack(s2, Terrain.hill);
        assertEquals(90, s2.getHealth());
        s1.attack(s2, Terrain.plains);
        assertEquals(75, s2.getHealth());
        s1.attack(s2, Terrain.plains);
        assertEquals(56, s2.getHealth());

        s2.attack(s1, Terrain.forest);
        assertEquals(92, s1.getHealth());
        s2.attack(s1, Terrain.forest);
        assertEquals(79, s1.getHealth());
        s2.attack(s1, Terrain.forest);
        assertEquals(62, s1.getHealth());
    }
}

