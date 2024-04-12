
import static org.junit.Assert.*;

import org.junit.Test;

import model.Order;
import model.Player;
import model.TerritoryDetails;

public class OrderTest {

    @Test
    public void testExecute() {
        Player player = new Player("Simran");
        TerritoryDetails territory = new TerritoryDetails("TestTerritory", "10");
        player.getOwnedTerritories().put(territory.getTerritoryID().toLowerCase(), territory);

        Order order = new Order(player, "testterritory", 5);
        boolean result = order.execute();
        assertEquals(true, result);
        
    }

   
}