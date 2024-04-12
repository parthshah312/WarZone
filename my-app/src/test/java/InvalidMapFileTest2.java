import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.LoadMap;

public class InvalidMapFileTest2 {

    LoadMap d_LoadMap;
    String d_MapName, d_MapType;

    @Before
    public void before(){
        d_LoadMap= new LoadMap();
    }

    @Test
    public void testDominationMap(){
        d_MapName= "europe.map";
        d_MapType= d_LoadMap.readMap("src/main/resources/maps/"+ d_MapName);
        assertEquals("domination", d_MapType);
    }
}
