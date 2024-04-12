import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import controller.Game;
import model.GameMap;
import model.Player;
import model.StartUpPhase;
import model.TerritoryDetails;

public class AssignTerritoryDetailsTest {

    StartUpPhase startUpPhase;
    ArrayList<Player> players;
    GameMap gameMap;
    Game g;

    @Before
    public void setUp() {
        startUpPhase = new StartUpPhase(g);
        players = new ArrayList<>();
        gameMap = new GameMap("france.map");
    }

    @Test
    public void testAssignTerritories() {
        /* it is add the player name*/
        startUpPhase.addPlayer(players, "Hetul");
        startUpPhase.addPlayer(players, "Simran");

        gameMap.getTerritories().put("Ain", new TerritoryDetails("Ain","Rhone-Alpes"));
        gameMap.getTerritories().put("Aisne", new TerritoryDetails("Aisne","Picardie"));

        /* it is assigning territories*/

        assertTrue(startUpPhase.assignTerritories(gameMap, players));

        for (Player player : players) {
            HashMap<String, TerritoryDetails> ownedTerritories = player.getOwnedTerritories();
            assertNotNull(ownedTerritories);
            assertTrue(ownedTerritories.size() > 0);

            /*  it is just Printing territory names for each player*/

            System.out.println("Player " + player.getPlayerName() + " territories:");
            for (TerritoryDetails territory : ownedTerritories.values()) {
                System.out.println("  Territory: " + territory.getTerritoryID());
            }
        }
    }

}
