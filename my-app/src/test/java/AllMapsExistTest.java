import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controller.Game;
import controller.TournamentEngine;

public class AllMapsExistTest {

    Game game=new Game();
    
    @Test
    public void testAllMapExists() {
        ArrayList<String> mapList1 = new ArrayList<>();
        mapList1.add("europe.map");
        mapList1.add("france.map");

        ArrayList<String> mapList2 = new ArrayList<>();
        mapList2.add("nonexistentMap.map");

        TournamentEngine checker = new TournamentEngine(game);

        // Test case 1: All maps exist in the list
        assertEquals(true, checker.allMapExists(mapList1), "Test Case 1 Failed");

        // Test case 2: Not all maps exist in the list
        assertEquals(false, checker.allMapExists(mapList2), "Test Case 2 Failed");
    }
}
