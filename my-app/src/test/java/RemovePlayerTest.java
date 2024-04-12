import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import controller.Game;
import model.GameMap;
import model.Player;
import model.StartUpPhase;

public class RemovePlayerTest {

    StartUpPhase startUpPhase;
    ArrayList<Player> players;
    GameMap gameMap;
    Game g;

    @Before
    public void setUp() {
        startUpPhase = new StartUpPhase(g);
        players = new ArrayList<>();
        gameMap = new GameMap("france.map");  // Assuming a map is provided for testing
    }

    @Test
    public void testRemovePlayer() {
        startUpPhase.addPlayer(players, "Hetul");
        startUpPhase.addPlayer(players, "Simaran");
        startUpPhase.addPlayer(players, "Mir");

         /*  it test removing players */
        assertTrue(startUpPhase.removePlayer(players, "Simaran"));
        assertEquals(2, players.size());
        /* it checks */
        assertFalse(startUpPhase.removePlayer(players, "NonExistingPlayer"));
        assertEquals(2, players.size());
    }

}
