import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import controller.Game;
import controller.TournamentEngine;

public class PlayerStrategyDistinctTest {

    @Test
    void testIsPlayerStrategyDistinct() {
        Game game =new Game();
        TournamentEngine checker = new TournamentEngine(game);

        // Test case 1: Distinct player strategies
        ArrayList<String> distinctStrategies = new ArrayList<>(Arrays.asList("aggressive", "benevolent", "random", "cheater"));
        assertTrue(checker.isPlayerStrategyDistinct(distinctStrategies), "Test Case 1 Failed");

        // Test case 2: Non-distinct player strategies
        ArrayList<String> nonDistinctStrategies = new ArrayList<>(Arrays.asList("aggressive", "benevolent", "random", "aggressive"));
        assertFalse(checker.isPlayerStrategyDistinct(nonDistinctStrategies), "Test Case 2 Failed");

    }
}
