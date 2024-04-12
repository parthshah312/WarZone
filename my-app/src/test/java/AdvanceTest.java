import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import model.Advance;
import model.Player;
import model.TerritoryDetails;

import java.util.HashMap;

public class AdvanceTest {

  @Test
  public void testAdvance_ValidMove_EnoughArmies_OwnedTarget() {
    // Create Player and TerritoryDetails objects
    Player player = new Player("TestPlayer");
    TerritoryDetails sourceCountry = new TerritoryDetails("sourceCountryId", "North America");
    TerritoryDetails targetCountry = new TerritoryDetails("targetCountryId", "North America");

    // Add territories to player's owned territories and set initial army count
    HashMap<String, TerritoryDetails> ownedTerritories = new HashMap<>();
    sourceCountry.setNumberOfArmies(10);
    targetCountry.setNumberOfArmies(5);
    ownedTerritories.put(sourceCountry.getTerritoryID(), sourceCountry);
    ownedTerritories.put(targetCountry.getTerritoryID(), targetCountry);

    // Set owned territories individually (based on Player class method signature)
    for (String territoryId : ownedTerritories.keySet()) {
      player.setOwnedTerritories(territoryId, ownedTerritories.get(territoryId));
    }

    // Create the Advance order
    Advance advance = new Advance(player, sourceCountry.getTerritoryID(), targetCountry.getTerritoryID(), 3, null);

    // Execute the order
    boolean result = advance.execute();

    // Assertions
    Assertions.assertTrue(result);
    Assertions.assertEquals(7, sourceCountry.getNumberOfArmies()); // Source loses 3 armies
    Assertions.assertEquals(8, targetCountry.getNumberOfArmies()); // Target gains 3 armies
  }
}
