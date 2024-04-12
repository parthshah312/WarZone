import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.LoadMap;

public class InvalidMapFileTest {

    LoadMap d_LoadMap;
    String d_MapName, d_MapType;

    @Before
    public void before(){
        d_LoadMap= new LoadMap();
    }

    @Test
    public void testConquestMap(){
        d_MapName= "USA.map";
        d_MapType= d_LoadMap.readMap("src/main/resources/maps/"+ d_MapName);
        assertEquals("conquest", d_MapType);
    }
}
