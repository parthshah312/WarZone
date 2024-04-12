import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import controller.Game;
import model.Player;
import model.StartUpPhase;


public class AddPlayerTest {

    @Test
    public void testAddPlayer() {
        // Initialize a StartUpPhase object
        Game game = new Game();
        StartUpPhase startUpPhase = new StartUpPhase(game);

        // Initialize a list of players
        List<Player> playersList = new ArrayList<>();

        // Add 4 players to the list
        startUpPhase.addPlayer(playersList, "Parth");
        startUpPhase.addPlayer(playersList, "Mir");
        startUpPhase.addPlayer(playersList, "Hetul");
        startUpPhase.addPlayer(playersList, "Simran");

        // Try to add a new player to the list
        boolean result = startUpPhase.addPlayer(playersList, "Harsimran");

        // Assert that the player was added successfully
        assertEquals(result, true);
        assertEquals(playersList.size(), 5);

        // Try to add another player to the list
        result = startUpPhase.addPlayer(playersList, "XYZ");

        // Assert that the player was not added successfully
        assertEquals(result, false);
        assertEquals(playersList.size(), 5);
    }
}