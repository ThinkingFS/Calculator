package cmpt276.ca.as2_servingsizecalculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test the Pot class in isolation.
 */
public class PotTest {

    @Test
    public void creation() {
        Pot pot = new Pot("Big", 100);
        assertEquals("Big", pot.getName());
        assertEquals(100, pot.getWeightInG());
    }

    @Test (expected = IllegalArgumentException.class)
    public void creationFailForNegWeight() {
        Pot pot = new Pot("Big", -1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void creationFailForEmptyName() {
        Pot pot = new Pot("", 100);
    }

    @Test
    public void creationSmallest() {
        Pot pot = new Pot(" ", 0);
        assertEquals(" ", pot.getName());
        assertEquals(0, pot.getWeightInG());
    }


    @Test
    public void setName() {
        Pot pot = new Pot("Some big bad pot name", 987);
        pot.setName("My small fry pan of awesome");
        assertEquals("My small fry pan of awesome", pot.getName());
        assertEquals(987, pot.getWeightInG());
    }

    @Test
    public void setWeight() {
        Pot pot = new Pot("pot", 10050);
        pot.setWeightInG(200);
        assertEquals(200, pot.getWeightInG());
    }

    @Test (expected = IllegalArgumentException.class)
    public void setNameFails() {
        Pot pot = new Pot("Big", 100);
        pot.setName("");
    }

    @Test (expected = IllegalArgumentException.class)
    public void setNameFailsNull() {
        Pot pot = new Pot("Big", 100);
        pot.setName(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setWeightFails() {
        Pot pot = new Pot("2rcg5 290350 9889035#%@#%(*\"", 100);
        pot.setWeightInG(-5550);
    }

}