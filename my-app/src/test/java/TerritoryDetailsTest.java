import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.GameMap;
import model.TerritoryDetails;

public class TerritoryDetailsTest {

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
    public void testParameterizedConstructor() {
        String territoryId = "Cher";
        String continent = "Bretagne";

        territory = new TerritoryDetails(territoryId, continent);

        assertEquals(0, territory.d_Index);
        assertEquals(0, territory.d_NumberOfArmies);
        assertEquals(territoryId, territory.d_TerritoryId);
        assertEquals(continent, territory.d_InContinent);
        assertNotNull(territory.d_Neighbours);
        assertTrue(territory.d_Neighbours.isEmpty());
    }


}
