package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * Class read the map and determine its type
 *
 */
public class LoadMap {
    /**
     *  Tracks the index value of continents, new or existing, to later facilitate writing them to
     * map files following domination's conventions.
     */
    private GameMap d_Map;
    String d_MapType;

    /**
     * method to read map
     * @param p_mapName name of map
     * @return type of map
     */
    public String readMap(String p_mapName) {
        d_Map = new GameMap(p_mapName);
        try {
            BufferedReader l_reader = new BufferedReader(new FileReader(p_mapName));
            String l_s;
            while ((l_s = l_reader.readLine()) != null) {
                if (l_s.equals("[territories]")) {
                    setMapType("domination");
                }
                if (l_s.equals("[Territories]")) {
                    setMapType("conquest");
                }
            }
            l_reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException");
            System.out.println(e.getMessage());
        }
        return d_MapType;
    }

    /**
     * setter method for map type
     * @param p_mapType type of map
     */
    public void setMapType(String p_mapType) {
        d_MapType = p_mapType;
    }

    /**
     * getter method for map type
     * @return type of map
     */
    public String getMapType() {
        return d_MapType;
    }
    
}