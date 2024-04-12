import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import model.Advance;
import model.Player;
import model.TerritoryDetails;

public class Advance2Test {

    @Test
    public void testAdvance_ValidMove_EnoughArmies_NotOwnedTarget() {
        // Player doesn't own the target country initially
        Player player = new Player("TestPlayer");
        TerritoryDetails sourceCountry = new TerritoryDetails("sourceCountryId", "North America");
        TerritoryDetails targetCountry = new TerritoryDetails("targetCountryId", "South America");

        // Set initial army count for source country
        sourceCountry.setNumberOfArmies(10);
        HashMap<String, TerritoryDetails> ownedTerritories = new HashMap<>();
        ownedTerritories.put(sourceCountry.getTerritoryID(), sourceCountry);

        // Option 1: Iterate and set territories individually (assuming Player.setOwnedTerritories takes String, TerritoryDetails)
        for (String territoryId : ownedTerritories.keySet()) {
            player.setOwnedTerritories(territoryId, ownedTerritories.get(territoryId));
        }

        // Option 2: Modify Player class to accept HashMap (if possible)
        // Replace the above loop with:
        // player.setOwnedTerritories(ownedTerritories);

        // Create the Advance order
        Advance advance = new Advance(player, sourceCountry.getTerritoryID(), targetCountry.getTerritoryID(), 3, null);

        // Execute the order
        boolean result = advance.execute();

        // Assertions (modify based on your Advance class logic)
        Assertions.assertTrue(result);
        Assertions.assertEquals(7, sourceCountry.getNumberOfArmies()); // Source loses 3 armies
        // Assertions for target country ownership or army changes (if applicable)
    }
}
