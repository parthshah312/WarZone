import static org.junit.Assert.*;

import org.junit.Test;

import model.AllotArmies;
import model.Player;
import model.TerritoryDetails;

public class AllotArmiesTest2 {

    @Test
    public void testAllotArmies() {
       
        Player player = new Player("Hetul");

        for(int i=1;i<=7;i++){
            player.setOwnedTerritories("A_"+i,new TerritoryDetails("A_"+i, "Asia"));
        }
        AllotArmies.allotArmies(player);

        assertEquals(player.getOwnedArmies(), 3);
    }
}