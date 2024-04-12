import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.GameMap;
import model.Player;
import model.RandomPlayer;
import model.TerritoryDetails;

public class FindRandomTerrirtoryTest {
    @Test
    void testFindRandomTerritory_WithEmptyOwnedTerritories() {
        // Create a RandomPlayer instance
        RandomPlayer randomPlayer = new RandomPlayer(new Player("Parth"), new GameMap());

        // Call the method
        TerritoryDetails result = randomPlayer.findRandomTerritory();

        // Verify that the result is null when owned territories are empty
        assertNull(result, "The result should be null for empty owned territories");
    }
}
