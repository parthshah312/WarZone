

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import model.*;

public class BombTest {
    private Player cPlayer;
    private Player player;

    @Before
    public void setUp() {
        // Initialize players
        cPlayer = new Player("John");
        player = new Player("Mike");
    }

    @Test
    public void testExecuteWithoutNegotiation() {
        // Create Bomb object
        Bomb bomb = new Bomb(cPlayer, player, "CountryID");

        // Set up player's owned territory
        TerritoryDetails territory = new TerritoryDetails();
        territory.setNumberOfArmies(10); // Assuming initial number of armies
        player.getOwnedTerritories().put("countryid", territory);

        // Execute bomb
        boolean result = bomb.execute();

        // Verify the result
        assertTrue(result);
        assertEquals(5, territory.getNumberOfArmies()); // Assuming half of the armies are removed
    }

}
