import static org.junit.Assert.*;

import org.junit.Test;

import model.AllotArmies;
import model.Player;
import model.TerritoryDetails;

public class AllotArmiesTest {

    @Test
    public void testAllotArmies() {
       
        Player player = new Player("Parth");

        for(int i=1;i<=12;i++){
            player.setOwnedTerritories("A_"+i,new TerritoryDetails("A_"+i, "Asia"));
        }
        AllotArmies.allotArmies(player);

        assertEquals(player.getOwnedArmies(), 4);
    }
}