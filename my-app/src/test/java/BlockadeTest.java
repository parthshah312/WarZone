import static org.junit.Assert.*;
import org.junit.Test;
import model.*;


public class BlockadeTest {

    @Test
    public void testExecute() {
        // Create a player
        Player player = new Player("John");
        
        // Create a territory
        TerritoryDetails territory = new TerritoryDetails("country1", "ContinentA");
        
        // Set initial number of armies in the territory
        territory.setNumberOfArmies(5);
        
        // Add the territory to the player's owned territories
        player.setOwnedTerritories("country1", territory);

        // Create an instance of Blockade
        Blockade blockade = new Blockade(player, "country1");
        
        // Execute the blockade operation
        boolean result = blockade.execute();

        // Verify the result
        assertTrue(result);
        
        // Verify that the number of armies in the territory has been tripled
        assertEquals(15, territory.getNumberOfArmies());
        
        // Verify that the territory is removed from the player's owned territories
        assertFalse(player.getOwnedTerritories().containsKey("country1"));
    }
}
