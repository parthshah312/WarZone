import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import model.Player;
import model.TerritoryDetails;
import model.Advance;

public class TestAdvance {

    private Player player1, player2;
    private TerritoryDetails sourceCountry, targetCountry;
    private Advance advance;

    @Before
    public void setUp() {
        player1 = new Player("p1");
        player2 = new Player("p2");
        sourceCountry = new TerritoryDetails("England", "5");
        targetCountry = new TerritoryDetails("Denmark", "3");
//        player2.addTerritory("target", targetCountry); // Note: player2 owns the target country
    }

    @Test
    public void testExecuteFailSourceCountryNotOwned() {
        advance = new Advance(player1, "England", "Denmark", 3, player2);
        assertFalse(advance.execute());
        assertEquals(0, sourceCountry.getNumberOfArmies()); // Source country armies should remain unchanged
        assertEquals(0, targetCountry.getNumberOfArmies());
    }
}
