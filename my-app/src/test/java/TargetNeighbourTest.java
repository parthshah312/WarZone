import controller.Game;
import enumClass.GamePhase;
import model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class TargetNeighbourTest {
    Player d_Player1;
    Player d_Player2;
    GameMap d_Map;
    ArrayList<Player> d_Players;
    StartUpPhase d_Stup;
    GamePhase d_GamePhase;
    Game d_Ge;
    GameRunnerEngine d_Rge;
    String d_SourceCountryId = null;
    String d_TargetCountryId = null;
    boolean l_checkOwnedCountry;
    @Before
    public void before() {
        d_Player1 = new Player("Hetul");
        d_Player2 = new Player("Simaran");
        d_Map = new GameMap("europe.map");
        d_SourceCountryId = "Germany";
        d_TargetCountryId = "Poland";
        d_Rge = new GameRunnerEngine();
        d_Players = new ArrayList<Player>();
        d_Players.add(d_Player1);
        d_Players.add(d_Player2);
        d_GamePhase = GamePhase.ISSUE_ORDERS;
        l_checkOwnedCountry = true;
    }
    @Test
    public void testAdjacent() {
        d_Ge = new Game();
        d_Stup = new StartUpPhase(d_Ge);
        d_Map = d_Rge.loadMap("europe.map");
        d_Stup.assignTerritories(d_Map, d_Players);

        boolean targetCountryNeighbour = false;

        for (TerritoryDetails cD : d_Player2.getOwnedTerritories().values()) {
            for (TerritoryDetails neighbor : cD.getNeighbours().values()) {
                if (neighbor.getTerritoryID().equalsIgnoreCase(d_TargetCountryId)) {
                    targetCountryNeighbour = true;
                    break;
                }
            }
            if (targetCountryNeighbour) {
                break;
            }
        }
        assertTrue(targetCountryNeighbour);
    }
}