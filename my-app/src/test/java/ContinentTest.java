
import static org.junit.Assert.*;

import org.junit.Test;

import model.Continent;

public class ContinentTest {

    @Test
    public void testContinent_StandardInput() {
        Continent continent = new Continent("Europe", 5);
        assertEquals("Europe", continent.d_ContinentId);
        assertEquals(5, continent.d_ControlValue);
        assertEquals(0, continent.d_Territory.size());
    }
}