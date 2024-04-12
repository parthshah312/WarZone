import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controller.Game;
import controller.TournamentEngine;

public class ValidMapNameTest {

    @Test
    void testIsMapNameValid() {
        Game game = new Game();
        TournamentEngine checker = new TournamentEngine(game);

        // Test case 1: Valid map name with alphabetic characters
        assertTrue(checker.isMapNameValid("abc.map"), "Test Case 1 Failed");

        // Test case 2: Invalid map name with numeric characters
        assertFalse(checker.isMapNameValid("map123"), "Test Case 3 Failed");

        // Test case 3: Invalid map name with special characters
        assertFalse(checker.isMapNameValid("map!name"), "Test Case 4 Failed");

        // Test case 4: Invalid map name with null input
        assertFalse(checker.isMapNameValid(null), "Test Case 5 Failed");
    }
}
