import model.GameMap;
import model.GameRunnerEngine;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoadMapTest {

    private GameRunnerEngine d_RunGame;

    /*  It just set uo the context.*/
    @Before
    public void before() {
        d_RunGame = new GameRunnerEngine();
    }

   /*  It test to load an existing map */
    @Test
    public void testLoadMap() {
        String mapName = "europe.map";
        GameMap loadedMap = d_RunGame.loadMap(mapName);

        assertNotNull(loadedMap);
        assertEquals(mapName, loadedMap.d_MapName);
    }

}
