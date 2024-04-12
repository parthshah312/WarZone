import static org.junit.Assert.*;

import org.junit.Test;

import model.GameMap;
import model.Player;
import model.TerritoryDetails;

public class PlayerWinTest {

    @Test
    public void testPlayerWin() {

        Player player1 = new Player("Parth");
        GameMap map= new GameMap("france.map");

        for (TerritoryDetails territory : map.getTerritories().values()) {
            player1.setOwnedTerritories(territory.d_InContinent, territory);
        }

        boolean result=map.getTerritories().size() == player1.getOwnedTerritories().size() ? true:false;

        // Check if player has won the game
        assertEquals(result, true);
        
    }
}