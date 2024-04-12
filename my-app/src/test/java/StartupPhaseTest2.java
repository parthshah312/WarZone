import static org.junit.Assert.*;
import model.*;
import controller.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StartupPhaseTest2 {

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

        // Adding a player with the same name should fail
        assertFalse(startupPhase.addPlayer(playersList, "Harsimran"));
        assertEquals(1, playersList.size()); // Size should remain the same

    }

}

