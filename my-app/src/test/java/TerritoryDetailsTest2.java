import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.GameMap;
import model.TerritoryDetails;

public class TerritoryDetailsTest2 {

    TerritoryDetails territory;

    @Before
    public void setUp() {
        territory = new TerritoryDetails(
            "1", 
            "TestTerritory", 
            "1", 
            "100", 
            "100", 
            new GameMap("france.map"));
    }


    @Test
    public void testGetTerritoryID() {
        String territoryId = "Cher";
        territory.d_TerritoryId = territoryId;

        assertEquals(territoryId, territory.getTerritoryID());
    }


}
