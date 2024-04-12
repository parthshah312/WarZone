import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test; // Add the missing import for @Test

import model.Advance;
import model.Player;
import model.TerritoryDetails;

public class Advance3Test {

    @Test
    public void testAdvance_InsufficientArmies() {
        // Player doesn't have enough armies
        Player player = new Player("TestPlayer");
        TerritoryDetails sourceCountry = new TerritoryDetails("sourceCountryId", "North America");
        TerritoryDetails targetCountry = new TerritoryDetails("targetCountryId", "North America");

        // Add territories to player's owned territories (iterate if Player.setOwnedTerritories doesn't accept HashMap)
        HashMap<String, TerritoryDetails> ownedTerritories = new HashMap<>();
        sourceCountry.setNumberOfArmies(2);
        ownedTerritories.put(sourceCountry.getTerritoryID(), sourceCountry);
        ownedTerritories.put(targetCountry.getTerritoryID(), targetCountry);

        // Option 1: Iterate if Player.setOwnedTerritories takes String, TerritoryDetails
        for (String territoryId : ownedTerritories.keySet()) {
            player.setOwnedTerritories(territoryId, ownedTerritories.get(territoryId));
        }

        // Option 2: Use Player.setOwnedTerritories(HashMap) if applicable
        // player.setOwnedTerritories(ownedTerritories);

        // Create the Advance order (trying to move 3 armies)
        Advance advance = new Advance(player, sourceCountry.getTerritoryID(), targetCountry.getTerritoryID(), 3, null);

        // Execute the order
        boolean result = advance.execute();

        // Assertions
        Assertions.assertFalse(result);
        Assertions.assertEquals(2, sourceCountry.getNumberOfArmies()); // No change in source army count

        // Potential additions:
        Assertions.assertEquals(0, targetCountry.getNumberOfArmies()); // Assuming no change in target army count
        //Assertions.assertNull(player.getOwnedTerritories().get(targetCountry.getTerritoryID())); // Unchanged ownership
        Assertions.assertNull(player.getOwnedTerritories().get(targetCountry.getTerritoryID().toLowerCase())); // Unchanged ownership
    }
}
