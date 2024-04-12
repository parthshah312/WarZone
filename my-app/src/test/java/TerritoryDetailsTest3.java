import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import model.GameMap;
import model.TerritoryDetails;

public class TerritoryDetailsTest3 {

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
    public void testGetNeighbours() {
        assertNotNull("Territory is null", territory);

        HashMap<String, TerritoryDetails> neighbours = territory.getNeighbours();
        assertNotNull("Neighbours are null", neighbours);
        System.out.println("Actual Neighbours: " + neighbours);

        TerritoryDetails neighbor1 = new TerritoryDetails("1", "Ain", "10", "343", "230", new GameMap("france.map"));
        TerritoryDetails neighbor2 = new TerritoryDetails("2", "Aisne", "4", "290", "77", new GameMap("france.map"));

        TerritoryDetails neighbor3 = new TerritoryDetails("3", "Allier", "21", "284", "216", new GameMap("france.map"));
        TerritoryDetails neighbor4 = new TerritoryDetails("4", "Alpes-de-Haute-Provence", "11", "381", "323", new GameMap("france.map"));

        territory.d_Neighbours.put("Ain", neighbor1);
        territory.d_Neighbours.put("Aisne", neighbor2);
        territory.d_Neighbours.put("Allier", neighbor3);
        territory.d_Neighbours.put("Alpes-de-Haute-Provence", neighbor4);

        HashMap<String, TerritoryDetails> expectedNeighbours = new HashMap<>();
        expectedNeighbours.put("Ain", neighbor1);
        expectedNeighbours.put("Aisne", neighbor2);
        expectedNeighbours.put("Allier", neighbor3);
        expectedNeighbours.put("Alpes-de-Haute-Provence", neighbor4);

        assertEquals(expectedNeighbours, territory.getNeighbours());
    }

}
