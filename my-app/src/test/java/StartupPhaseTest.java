import static org.junit.Assert.*;
import model.*;
import controller.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StartupPhaseTest {

    public StartUpPhase startupPhase;
    public Game game;
    private List<Player> playersList;

    @Before
    public void setUp() {
        game = new Game();
        startupPhase = new StartUpPhase(game);
        playersList = new ArrayList<>();
    }

    @Test
    public void testAddPlayer()
    {
        assertTrue(startupPhase.addPlayer(playersList, "Harsimran"));
        assertEquals(1, playersList.size());


        // Adding more players should work until the maximum limit
        assertTrue(startupPhase.addPlayer(playersList, "Simran"));
        assertTrue(startupPhase.addPlayer(playersList, "Het"));
        assertTrue(startupPhase.addPlayer(playersList, "Mir"));
        assertTrue(startupPhase.addPlayer(playersList, "Parth"));
        assertEquals(5, playersList.size()); // Size should be 5 now

        // Trying to add more than 5 players should fail
        assertFalse(startupPhase.addPlayer(playersList, "Navjyot"));
        assertEquals(5, playersList.size()); // Size should remain 5
    }

}
