import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import model.Advance;
import model.Player;
import model.TerritoryDetails;

public class Advance4Test {

    @Test
    public void testAdvance_InsufficientArmies() {
        // Player doesn't have enough armies
        Player player = new Player("TestPlayer");
        TerritoryDetails sourceCountry = new TerritoryDetails("sourceCountryId", "North America");
        TerritoryDetails targetCountry = new TerritoryDetails("targetCountryId", "North America");

        // Create the HashMap for owned territories before using it
        HashMap<String, TerritoryDetails> ownedTerritories = new HashMap<>();

        // Set initial army count and add territories to ownedTerritories
        sourceCountry.setNumberOfArmies(2);
        ownedTerritories.put(sourceCountry.getTerritoryID(), sourceCountry);
        ownedTerritories.put(targetCountry.getTerritoryID(), targetCountry);

        // Option 1: Iterate to set territories if Player.setOwnedTerritories takes String, TerritoryDetails
        for (String territoryId : ownedTerritories.keySet()) {
            player.setOwnedTerritories(territoryId, ownedTerritories.get(territoryId));
        }

        // Option 2: Modify Player class to accept HashMap (if applicable)
        // Replace the loop above with:
        // player.setOwnedTerritories(ownedTerritories);

        // Create the Advance order (trying to move 3 armies)
        Advance advance = new Advance(player, sourceCountry.getTerritoryID(), targetCountry.getTerritoryID(), 3, null);

        // Execute the order
        boolean result = advance.execute();

        // Assertions
        Assertions.assertFalse(result, "Advance should fail due to insufficient armies");
        Assertions.assertEquals(2, sourceCountry.getNumberOfArmies(), "Source army count should remain unchanged");
        Assertions.assertEquals(0, targetCountry.getNumberOfArmies(), "Target army count should remain unchanged (assuming no automatic allocation)");

        // Potential additional assertions (modify based on Advance class behavior):
        // - Assertions for error messages or exceptions thrown by Advance.execute()
    }
}
