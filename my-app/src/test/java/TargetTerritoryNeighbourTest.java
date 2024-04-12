import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.GameMap;
import model.Player;
import model.RandomPlayer;
import model.TerritoryDetails;

public class TargetTerritoryNeighbourTest {

    @Test
    void testTargetTerritoryNeighbour_WithNullTerritory() {
        // Create a RandomPlayer instance
        RandomPlayer randomPlayer = new RandomPlayer(new Player("Test"), new GameMap());

        // Call the method with null territory and assert the result
        TerritoryDetails result = randomPlayer.targetTerritoryNeighbour(null);
        assertNull(result, "The result should be null for null input");
    }
}
