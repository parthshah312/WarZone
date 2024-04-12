import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controller.Game;
import controller.TournamentEngine;

public class ValidPlayerStrategyTest {

    @Test
    void testIsPlayerStrategyValid() {
        Game game=new Game();
        TournamentEngine checker = new TournamentEngine(game);

        // Test case: Valid player strategy (aggressive)
        assertTrue(checker.isPlayerStrategyValid("aggressive"), "Test Case Failed");
    }
}
